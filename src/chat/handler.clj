(ns chat.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :as ohs]
            [hiccup.core :as hc]
            [garden.core :as gc]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [chat.chat :as cc]))

(def main-style
  (gc/css [[:body {:padding 30}]
           [:h2 {:color "blue"}]
           [:div {:border "solid 1px black"
                  :float "left"
                  :width "480"
                  :height "240"
                  :overflow-x "hidden"
                  :overflow-y "auto"}]
           [:textarea {:border "solid 1px black"
                       :height "240"
                       :position "relative"}]]))

(defn js [pth]
  [:script {:src pth}])

(defn layout [title & cnt]
  (hc/html [:html
            [:head
             [:title title]
             [:style main-style]]
            [:body cnt]
            (js "assets/app.js")]))

(defn index [req]
  {:headers {"Content-Type" "text/html"}
   :body (layout "index"
                 [:h2 "Clojure's Chat"]
                 [:div#out]
                 [:textarea#inp])
   :status 200})

(defn chat [{params :params :as req}]
  (ohs/with-channel req ch
    (cc/add-usr ch params)
    (ohs/on-receive ch (fn [msg] (cc/on-msg ch msg)))
    (ohs/on-close ch (fn [_] (cc/rm-usr ch)))))

(defroutes app-routes
  (GET "/:name" [] #'index)
  (GET "/chat/:name" [] #'chat)
  (route/resources "/assets/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(def stop
  (ohs/run-server #'app {:port 9999}))


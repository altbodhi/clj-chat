(ns chat.chat
  (:require
   [org.httpkit.server :as ohs]
   [hiccup.core :as hc]))

(def usrs (atom {}))

(defn add-usr [ch us]
  (println (str "add user : " us))
  (swap! usrs assoc ch us))

(defn rm-usr [ch]
  (swap! usrs dissoc ch))

(defn fmt-msg [u msg]
  (hc/html
   [:span
    [:b (:name u)]
    [:pre msg]]))


(defn b-cast [msg]
  (doseq [[ch u] @usrs]
    (ohs/send! ch msg)))


(defn on-msg [ch msg]
  (println msg)
  (let [u (get @usrs ch)]
    (b-cast (fmt-msg u msg))))
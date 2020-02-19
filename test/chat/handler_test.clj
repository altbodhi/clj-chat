(ns chat.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [chat.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))


(defn get-port
  [& args]
  (count args))
  ;(if (empty? args) 9999
   ;   (java.lang.Integer/parseInt (first args))))

(get-port #{ 1  2 3  4})



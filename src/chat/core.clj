(ns chat.core
  (:gen-class)
  (:require [chat.handler :as ch]))



(def host (java.net.InetAddress/getLocalHost))

(defn -main
  "Start simple chat on port"
  [& args]
  (let [port (if (nil? args) 9999 (java.lang.Integer/parseInt (first args)))]
    (let [stop  (ch/start port)]
      (println (format "Server started on %d port.\r\nGo to url http://%s:%d/YouName for chat!\r\nFor stop press [Enter]." port (.getHostName host) port))
      (read-line)
      (stop))))



(defproject chat "0.1.0-SNAPSHOT"
  :description "chat power by clojure"
  :url "http://localhost"
  :min-lein-version "2.0.0"
  :main ^:skip-aot chat.core
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [http-kit "2.4.0-alpha6"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "2.0.0-alpha2"]
                 [garden "1.3.9"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler chat.handler/app}
  :profiles
  {:uberjar {:aot :all}
   :dev {:dependencies [[im.chit/vinyasa "0.4.7"]
                        [javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})

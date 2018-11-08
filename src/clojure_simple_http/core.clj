(ns clojure-simple-http.core
  (:require [org.httpkit.server :refer [run-server]]
            [clojure-simple-http.server :refer :all]
            [clj-time.core :as t]))

(defn -main [& args]
  (run-server app {:port 11080})
  (println "Server started on port 11080"))
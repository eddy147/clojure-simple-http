(ns clojure-simple-http.server
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t]))
(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "HelloWorld")})
(ns clojure-simple-http.core
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t])
  (:gen-class))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "HelloWorld")})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)
    (println "Server stopped.")))

(defn start-server []
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (do
    (println "Web server started at http://127.0.0.1:8080")
    (reset! server (run-server #'app {:port 8080}))))
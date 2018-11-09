(ns clojure-simple-http.core
  (:use [org.httpkit.server :refer [run-server]]
        [clj-time.core :as t]
        [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET PUT POST DELETE ANY context]])
  (:gen-class))

(defroutes all-routes
  (GET "/" [] app)
  (context "/user/:id" []
           (GET / [] get-user)
           (PUT / [] update-user))
  (not-found "<h1>404</h2>"))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "<h1>Hello World</h1>")})

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
    (reset! server (run-server #'all-routes {:port 8080}))))

(defn get-user [req]
  {:status   200
   :headers  {"Content-Type" "text-html"}
   :body     (str "<h1>Info User</h1><p>Naam: Jaap Knasterhuis</p>")})

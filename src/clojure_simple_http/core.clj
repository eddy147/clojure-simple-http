(ns clojure-simple-http.core
  (:use [org.httpkit.server :as server]
        [compojure.core :refer :all]
        [compojure.route :as route]
        (:gen-class)))

(defonce server-is-running (atom nil))

(defn fps-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Pew pew!"})

(defn mail-man [] 
  "{\"Spongebob Narrator\": \"5 years later...\"}")

(defn mail-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (mail-man)}) 

(defn general-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "All hail General Zod!"})

(defroutes app-routes 
  (GET "/" [] fps-handler)
  (POST "/postoffice" [] mail-handler)
  (ANY "/anything-goes" [] general-handler)
  (route/not-found "You Must Be New Here")) 

(defn start-server []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))] 
    (reset! server-is-running (server/run-server #'app-routes {:port port}))
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(defn stop-server []
  (when-not (nil? @server-is-running )
    (@server-is-running :timeout 100)
    (reset! server-is-running nil)))

(defn -main
  "This is our app's entry point"
  [& args]
  (start-server))

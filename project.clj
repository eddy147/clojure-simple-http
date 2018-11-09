(defproject clojure-simple-http "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [clj-time "0.15.0"]
                 [compojure "1.6.1"]
                 [javax.servlet/servlet-api "2.5"]]
  :main clojure-simple-http.core
  :aot [clojure-simple-http.core])

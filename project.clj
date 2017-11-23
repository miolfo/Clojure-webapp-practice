(defproject todo-list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT "}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [ring "1.6.3"]
    [compojure "1.3.4"]]
  :min-lein-version "2.0.0"
  :uberjar-name "todo-list.jar"
  :main todo-list.core
  :profiles {:dev
                {:main todo-list.core/-dev-main}})
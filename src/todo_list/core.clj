(ns todo-list.core
  (require [ring.adapter.jetty :as jetty]
           [ring.middleware.reload :refer [wrap-reload]]
           [compojure.core :refer [defroutes GET]]
           [compojure.route :refer [not-found]]
           [ring.handler.dump :refer [handle-dump]]))

(def operands {"+" + "-" - "*" * ":" /})

(defn hello
  "Sample for parsing input parameters in url"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Hello " name)
     :headers{}}))

(defn calculator
"A very simple calculator that can add, divide, subtract and multiply.  This is done through the magic of variable path elements."
[request]
(let [a  (Integer. (get-in request [:route-params :a]))
      b  (Integer. (get-in request [:route-params :b]))
      op (get-in request [:route-params :op])
      f  (get operands op)]
  (if f
    {:status 200
      :body (str "Calculated result: " (f a b))
      :headers {}}
    {:status 404
      :body "Invalid operation"
      :headers {}})))

(defn welcome
"Handler function for all requests"
[request]
{:status 200
  :body "Hello Clojure and Ring asd"
  :headers{}})

(defn goodbye
  "Another route"
  [request]
  {:status 200
   :body "Thx for visit"
   :headers{}})

(defn request-info
  "Print info about the request"
  [request]
  {:status 200
   :body (pr-str request)
   :headers{}})

(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/request-info" [] handle-dump)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:a/:op/:b" [] calculator)
  (not-found "404 this is not the page you are looking for"))

(defn -dev-main
  "Ring & Jetty web server with hot reload!"
  [port]
  (jetty/run-jetty (wrap-reload #'app)
  {:port (Integer. port)}))

(defn -main
  "Ring & Jetty web server"
  [port]
  (jetty/run-jetty app {:port (Integer. port)}))

(ns todo-list.core
  (require [ring.adapter.jetty :as jetty]
           [ring.middleware.reload :refer [wrap-reload]]
           [compojure.core :refer [defroutes GET]]
           [compojure.route :refer [not-found]]))

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
  (GET "/request-info" [] request-info)
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
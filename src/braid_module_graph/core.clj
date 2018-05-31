(ns braid-module-graph.core
  (:gen-class)
  (:require
    [bloom.omni.core :as omni]))

(def config
  {:omni/http-port 6123
   :omni/cljs {:main "braid-module-graph.client.core"}})

(defn start! []
  (omni/start! omni/system config))

(defn stop! []
  (omni/stop!))

(defn -main []
  (start!))

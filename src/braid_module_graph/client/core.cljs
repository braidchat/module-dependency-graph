(ns braid-module-graph.client.core
  (:require
    [reagent.core :as r]
    [braid-module-graph.client.ui.app :refer [app-view]]))

(enable-console-print!)

(defn render
  []
  (r/render
    [app-view]
    (js/document.getElementById "app")))

(defn ^:export init
  []
  (render))

(defn ^:export reload
  []
  (render))

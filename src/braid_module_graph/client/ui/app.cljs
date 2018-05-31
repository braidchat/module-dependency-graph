(ns braid-module-graph.client.ui.app
  (:require
    [braid-module-graph.modules :refer [modules]]
    [braid-module-graph.client.ui.graph :as graph]))

(defn app-view []
  (let [text (->> {:nodes modules
                   :edges (->> modules
                               (mapcat (fn [m]
                                         (for [d (m :deps)]
                                           [(m :id) d]))))}
                  graph/graphviz-text)]
    [:div
     #_[:code {:style {:white-space "pre"}} text]
     [graph/graphviz-svg text]]))

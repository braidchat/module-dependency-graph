(ns braid-module-graph.client.ui.graph
  (:require
    [clojure.string :as string]
    [viz.core :as viz]))

(defn graphviz-svg [text]
  [:div 
   {:dangerously-set-inner-HTML 
    {:__html
     (viz/image text)}}])

; Useful reference:
; http://graphviz.org/doc/info/attrs.html

(defn graphviz-text 
  [{:keys [nodes edges] :as graph}]
  (let [node-str (fn [node]
                   (let [fill (if (node :new?)
                                 "solid"
                                 "filled")
                         shape "box"
                         color "#4cafef"
                         rounded (if (node :provides?)
                                   true
                                   false)
                         styles [fill
                                 (if rounded "rounded" "")]
                         ]
                     (str (pr-str (node :id)) 
                          " [" "style=" "\"" (string/join "," styles) "," fill "\"" ", " 
                               "color=" (pr-str color) ", "
                               "shape=" shape ", "
                               "penwidth=3"
                               "];")))]
    (->> ["digraph {"
          "rankdir=RL;"
          (->> nodes
               (map (fn [node]
                      (if (node :modules)
                        (->> [(str "subgraph " (gensym "cluster") " {")
                              (str "penwidth=3;")
                              (str "style=filled;")
                              (str "color=\"#cccccc\";")
                              (str "label = " (pr-str (node :id)) ";")
                              (for [node (node :modules)]
                                (node-str node))
                              "}"]
                             flatten
                             (string/join "\n"))
                        (node-str node)))))
          (->> edges
               (map (fn [[id1 id2]]
                      (str (pr-str id1) " -> " (pr-str id2) ";"))))
          "}"]
         flatten
         (string/join "\n"))))

(defproject braid-module-graph "0.0.1"
  :dependencies [; server
                 [org.clojure/clojure "1.9.0"]

                 ; both
                 [io.bloomventures/omni "0.14.8"]

                 ; client
                 [org.clojure/clojurescript "1.10.238"]
                 [viz-cljc "0.1.3"]
                 [re-frame "0.10.5"]
                 [reagent "0.8.1"]
                 [garden "1.3.5"]]

  :main braid-module-graph.core

  :plugins [[io.bloomventures/omni "0.14.8"]]

  :omni-config braid-module-graph.core/config

  :profiles {:uberjar
             {:aot :all
              :prep-tasks [["omni" "compile"]
                           "compile"]}})

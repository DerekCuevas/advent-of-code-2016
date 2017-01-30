(ns day-one.core
  (:gen-class))

(def ^:private directions
  (array-map :north [0 1] :east [1 0] :south [0 -1] :west [-1 0]))

(defn find-block-distance [input] 0)

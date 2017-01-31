(ns day-one.core
  (:require [clojure.math.numeric-tower :refer [abs]])
  (:gen-class))

(def ^:private directions
  [[0 1] [1 0] [0 -1] [-1 0]])

(defn- distance [[x y]]
  (+ (abs x) (abs y)))

(defn- move [direction steps [px py]]
  (let [[sx sy] (directions direction)]
    [(+ px (* sx steps)) (+ py (* sy steps))]))

(defn- next-direction [turn direction]
  (-> (case turn \L -1 \R 1)
      (+ direction)
      (mod (count directions))))

(defn- turn [{:keys [position direction]} [turn steps]]
  (let [next (next-direction turn direction)]
    {:position (move next steps position)
     :direction next}))

(defn- travel [path]
  (reduce turn {:position [0 0] :direction 0} path))

(defn find-block-distance [input]
  (-> input (comp travel :position distance)))

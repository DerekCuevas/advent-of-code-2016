(ns day-one.core
  (:require [clojure.math.numeric-tower :refer [abs]])
  (:gen-class))

(def ^:private directions
  [[0 1] [1 0] [0 -1] [-1 0]])

(defn- distance [[x y]]
  (+ (abs x) (abs y)))

(defn- move [direction steps [x y]]
  (let [[sx sy] (directions direction)]
    [(+ x (* sx steps)) (+ y (* sy steps))]))

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

(def find-block-distance
  (comp distance :position travel))

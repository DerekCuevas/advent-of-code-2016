(ns day-three.core
  (:gen-class))

(defn- triangle? [dims]
  (let [[a b c] (sort dims)]
    (> (+ a b) c)))

(defn count-triangles [input]
  (->> input
       (filter triangle?)
       (count)))

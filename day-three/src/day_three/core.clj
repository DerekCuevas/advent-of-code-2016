(ns day-three.core
  (:gen-class))

(defn- triangle? [dims]
  (let [[a b c] (sort dims)]
    (> (+ a b) c)))

(defn- transpose [input]
  (->> input
       (apply mapv vector)
       (flatten)
       (partition 3)))

(defn count-triangles [input]
  (->> input
       (filter triangle?)
       (count)))

(def count-transposed-triangles
  (comp count-triangles transpose))

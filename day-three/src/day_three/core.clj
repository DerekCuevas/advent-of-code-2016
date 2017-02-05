(ns day-three.core
  (:gen-class))

(defn- triangle? [dims]
  (let [[a b c] (sort dims)]
    (> (+ a b) c)))

(defn- transpose [v]
  (apply mapv vector v))

(defn- column-dims [input]
  (->> input
       (transpose)
       (flatten)
       (partition 3)))

(defn count-triangles [input]
  (->> input
       (filter triangle?)
       (count)))

(def count-column-triangles
  (comp count-triangles column-dims))

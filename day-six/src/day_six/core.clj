(ns day-six.core
  (:gen-class))

(defn- transpose [v]
  (apply mapv vector v))

(defn- most-frequent [coll]
  (->> coll
       (frequencies)
       (sort-by val)
       (last)
       (first)))

(defn message [input]
  (->> input
       (transpose)
       (map most-frequent)
       (apply str)))

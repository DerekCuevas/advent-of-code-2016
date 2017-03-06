(ns day-six.core
  (:gen-class))

(defn- transpose [v]
  (apply mapv vector v))

(defn- frequent [pred coll]
  (->> coll
       (frequencies)
       (sort-by val)
       (pred)
       (first)))

(defn- message [pred input]
  (->> input
       (transpose)
       (map (partial frequent pred))
       (apply str)))

(def part-one-message
  (partial message last))

(def part-two-message
  (partial message first))

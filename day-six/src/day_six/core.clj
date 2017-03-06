(ns day-six.core
  (:gen-class))

(defn- transpose [v]
  (apply mapv vector v))

(defn- most-frequent [selector coll]
  (->> coll
       (frequencies)
       (sort-by val)
       (selector)
       (first)))

(defn- message [selector input]
  (->> input
       (transpose)
       (map (partial most-frequent selector))
       (apply str)))

(def part-one-message
  (partial message last))

(def part-two-message
  (partial message first))

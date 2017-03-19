(ns day-eight.core
  (:gen-class))

(defn- grid [[width height] init]
  (->> (repeat width init)
       (vec)
       (repeat height)
       (vec)))

(defn- transpose [coll]
  (apply mapv vector coll))

(defn- rotate [n coll]
  (let [offset (- (count coll) (mod n (count coll)))]
    (vec (concat (drop offset coll)
                 (take offset coll)))))

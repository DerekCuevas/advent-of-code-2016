(ns day-seven.core
  (:gen-class))

(defn- mirror? [coll]
  (and (apply not= coll)
       (= (apply str (take 2 coll))
          (apply str (reverse (drop 2 coll))))))

(defn- abba? [s]
  (->> (partition 4 1 s)
       (map mirror?)
       (some true?)))

(defn- ip? [{:keys [hypernet abba]}]
  (and (not-any? true? (map abba? hypernet))
       (some true? (map abba? abba))))

(defn count-valid-ips [input]
  (->> input
       (filter ip?)
       (count)))

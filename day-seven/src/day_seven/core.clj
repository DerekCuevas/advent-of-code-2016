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

(defn- any-abba? [seqs]
  (some true? (map abba? seqs)))

(defn- ip? [{:keys [hypernet-seqs supernet-seqs]}]
  (and (not (any-abba? hypernet-seqs))
       (any-abba? supernet-seqs)))

(defn count-valid-ips [input]
  (->> input
       (filter ip?)
       (count)))

(ns day-seven.core
  (:gen-class))

(defn- mirror? [coll]
  (and (apply not= coll)
       (= (apply str (take 2 coll))
          (apply str (reverse (drop 2 coll))))))

(defn- abba? [s]
  (->> (partition 4 1 s)
       (some mirror?)))

(defn- any-abba? [seqs]
  (some true? (map abba? seqs)))

(defn- supports-tsl? [ip]
  (and (not (any-abba? (:hypernet-seqs ip)))
       (any-abba? (:supernet-seqs ip))))

(defn count-supporting-tsl? [ips]
  (count (filter supports-tsl? ips)))

;; part two

(defn- aba->bab [[a b _]]
  (seq [b a b]))

(defn- aba? [s]
  (and (apply not= s) (= (first s) (last s))))

(defn- find-abas [seqs]
  (mapcat #(filter aba? (partition 3 1 %)) seqs))

(defn- supports-ssl? [ip]
  (let [abas (find-abas (:supernet-seqs ip))]
    (some (into #{} (map aba->bab abas))
          (mapcat #(partition 3 1 %) (:hypernet-seqs ip)))))

(defn count-supporting-ssl? [ips]
  (count (filter supports-ssl? ips)))

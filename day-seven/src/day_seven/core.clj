(ns day-seven.core
  (:gen-class))

(defn- palindrome? [coll]
  (and (apply not= coll)
       (= (seq coll) (reverse coll))))

(defn- abba? [s]
  (some palindrome? (partition 4 1 s)))

(defn- some-abba? [seqs]
  (some identity (map abba? seqs)))

(defn- supports-tsl? [ip]
  (and (some-abba? (:supernet-seqs ip))
       (not (some-abba? (:hypernet-seqs ip)))))

(defn count-supporting-tsl? [ips]
  (->> ips
       (filter supports-tsl?)
       (count)))

;; part two

(defn- aba->bab [[a b _]]
  (seq [b a b]))

(defn- find-abas [seqs]
  (mapcat #(filter palindrome? (partition 3 1 %)) seqs))

(defn- supports-ssl? [ip]
  (let [abas (find-abas (:supernet-seqs ip))]
    (some (into #{} (map aba->bab abas))
          (mapcat #(partition 3 1 %) (:hypernet-seqs ip)))))

(defn count-supporting-ssl? [ips]
  (->> ips
       (filter supports-ssl?)
       (count)))

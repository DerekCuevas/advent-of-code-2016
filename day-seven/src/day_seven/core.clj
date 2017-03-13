(ns day-seven.core
  (:gen-class))

(defn- palindrome? [coll]
  (and (apply not= coll)
       (= (seq coll) (reverse coll))))

(defn- palindromic-subseqs [n coll]
  (filter palindrome? (partition n 1 coll)))

(defn- combine-palindromic-subseqs [n colls]
  (mapcat (partial palindromic-subseqs n) colls))

(defn- some-abba? [colls]
  (not (empty? (combine-palindromic-subseqs 4 colls))))

(defn- supports-tsl? [ip]
  (and (some-abba? (:supernet-seqs ip))
       (not (some-abba? (:hypernet-seqs ip)))))

(defn count-supporting-tsl [ips]
  (count (filter supports-tsl? ips)))

;; part two

(defn- aba->bab [[a b _]]
  (seq [b a b]))

(defn- supports-ssl? [ip]
  (let [abas (combine-palindromic-subseqs 3 (:supernet-seqs ip))
        babs (combine-palindromic-subseqs 3 (:hypernet-seqs ip))]
    (some (into #{} (map aba->bab abas)) babs)))

(defn count-supporting-ssl [ips]
  (count (filter supports-ssl? ips)))

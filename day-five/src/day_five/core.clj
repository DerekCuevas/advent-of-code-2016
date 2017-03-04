(ns day-five.core
  (:require [clojure.string :refer [starts-with? includes?]]
            [digest :refer [md5]])
  (:gen-class))

(defn- hash [key n]
  (md5 (str key n)))

(defn- hashes
  ([key]
   (hashes key 0))
  ([key n]
   (lazy-seq (cons (hash key n) (hashes key (inc n))))))

(defn- starts-with-five-zeros? [s]
  (starts-with? s "00000"))

(defn part-one-password [key]
  (->> (hashes key)
       (filter starts-with-five-zeros?)
       (take 8)
       (map #(nth % 5))
       (apply str)))

(defn- assoc-str [s index value]
  (apply str (assoc (vec s) index value)))

(defn- next-password [password hash]
  (let [position (read-string (str "0x" (nth hash 5)))
        value (nth hash 6)]
    (if (or (>= position 8) (not= (get password position) \_))
      password
      (assoc-str password position value))))

(defn- add [password hash]
  (if-not (includes? password "_")
    (reduced password)
    (next-password password hash)))

(defn part-two-password [key]
  (->> (hashes key)
       (filter starts-with-five-zeros?)
       (reduce add "________")))

(ns day-five.core
  (:require [clojure.string :refer [starts-with?]]
            [digest :refer [md5]])
  (:gen-class))

(defn- starts-with-five-zeros? [s]
  (->> (repeat 5 "0")
       (apply str)
       (starts-with? s)))

(defn- hashes
  ([key]
    (hashes key 0))
  ([key n]
    (lazy-seq (cons (md5 (str key n))
              (hashes key (inc n))))))

(defn part-one-password [key]
  (->> (hashes key)
       (filter starts-with-five-zeros?)
       (take 8)
       (map #(nth % 5))
       (apply str)))

(defn- complete? [password]
  (not-any? #{\_} password))

(defn- assoc-str [s index value]
  (apply str (assoc (vec s) index value)))

(defn- next-password [password hash]
  (let [position (read-string (str "0x" (nth hash 5)))
        value (nth hash 6)]
    (if (and (< position 8) (= (get password position) \_))
      (assoc-str password position value)
      password)))

(defn- add [password hash]
  (if (complete? password)
    (reduced password)
    (next-password password hash)))

(defn part-two-password [key]
  (->> (hashes key)
       (filter starts-with-five-zeros?)
       (reduce add "________")))

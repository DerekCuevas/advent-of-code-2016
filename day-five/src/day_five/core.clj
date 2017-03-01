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

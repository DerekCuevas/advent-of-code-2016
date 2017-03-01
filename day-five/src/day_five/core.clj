(ns day-five.core
  (:require [clojure.string :refer [starts-with?]]
            [digest :refer [md5]])
  (:gen-class))

(defn- starts-with-n-zeros? [n s]
  (starts-with? s (apply str (repeat n "0"))))

(defn generate-password [key]
  (loop [password ""
         index 0]
    (let [hash (md5 (str key index))]
      (cond
        (= (count password) 8)
          password
        (starts-with-n-zeros? 5 hash)
          (recur (str password (nth hash 5)) (inc index))
        :else
          (recur password (inc index))))))

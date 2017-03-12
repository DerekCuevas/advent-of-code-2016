(ns day-seven.core-test
  (:require [clojure.test :refer :all]
            [day-seven.core :refer :all]
            [clojure.string :refer [split]]))

(def ip-format #"\[[^\]]*\]|(\b\w+\b)")

(defn- hypernet-seq [matches]
  (->> matches
      (map #(if-not (second %) (first %) false))
      (filter identity)))

(defn- abba-seq [matches]
  (->> matches
       (map second)
       (filter identity)))

(defn- parse-ip [s]
  (let [matches (re-seq ip-format s)]
    {:hypernet (hypernet-seq matches)
     :abba (abba-seq matches)}))

(defn- parse [input]
  (map parse-ip input))

(def input
  (-> (slurp "resources/input.txt")
      (split #"\n")
      (parse)))

(deftest count-valid-ips-test
  (testing "count valid ips"
    (is (= (count-valid-ips input) 105))))

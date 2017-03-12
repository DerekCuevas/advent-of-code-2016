(ns day-seven.core-test
  (:require [clojure.test :refer :all]
            [day-seven.core :refer :all]
            [clojure.string :refer [split]]))

(def ^:private ip-format #"\[[^\]]*\]|(\b\w+\b)")

(defn- hypernet-seqs [matches]
  (->> matches
      (map #(if-not (second %) (first %) false))
      (filter identity)))

(defn- supernet-seqs [matches]
  (->> matches
       (map second)
       (filter identity)))

(defn- parse-ip [s]
  (let [matches (re-seq ip-format s)]
    {:hypernet-seqs (hypernet-seqs matches)
     :supernet-seqs (supernet-seqs matches)}))

(defn- parse [input]
  (map parse-ip input))

(def input
  (-> (slurp "resources/input.txt")
      (split #"\n")
      (parse)))

(deftest count-valid-ips-test
  (testing "count valid ips"
    (is (= (count-valid-ips input) 105))))

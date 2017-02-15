(ns day-four.core-test
  (:require [clojure.test :refer :all]
            [day-four.core :refer :all]
            [clojure.string :refer [split]]))

(def data-format #"(\S+)-(\d+)\[(\S+)\]")

(defn parse-data [s]
  (let [[_ encrypted-name sector-id checksum] (re-find data-format s)]
    {:encrypted-name encrypted-name
     :sector-id (read-string sector-id)
     :checksum checksum}))

(defn parse [input]
  (map parse-data input))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")
      (parse)))

(deftest sum-sector-ids-test
  (testing "sum sector ids"
    (is (= (sum-sector-ids input) 173787))))

(deftest find-sector-id-test
  (testing "find sector id"
    (is (= (find-sector-id "northpole object storage" input) 548))))

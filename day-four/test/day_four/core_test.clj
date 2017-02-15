(ns day-four.core-test
  (:require [clojure.test :refer :all]
            [day-four.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")))

(deftest sum-sector-ids-test
  (testing "sum sector ids"
    (is (= (sum-sector-ids input) 173787))))

(deftest find-sector-id-test
  (testing "find sector id"
    (is (= (find-sector-id input) 548))))

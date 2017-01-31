(ns day-one.core-test
  (:require [clojure.test :refer :all]
            [day-one.core :refer :all]
            [clojure.string :refer [split]]))

(defn parse-turn [[turn & distance]]
  [turn (read-string (apply str distance))])

(defn parse [input]
  (map parse-turn input))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #", ")
      (parse)))

(deftest find-block-distance-test
  (testing "find block distance"
    (is (= (find-block-distance input) 252))))

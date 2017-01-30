(ns day-one.core-test
  (:require [clojure.test :refer :all]
            [day-one.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")))

(deftest find-block-distance-test
  (testing "find block distance"
    (is (= (find-block-distance input) 252))))

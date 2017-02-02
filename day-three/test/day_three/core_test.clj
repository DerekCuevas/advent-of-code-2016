(ns day-three.core-test
  (:require [clojure.test :refer :all]
            [day-three.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= (count-triangles input) 1))))

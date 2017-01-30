(ns day-two.core-test
  (:require [clojure.test :refer :all]
            [day-two.core :refer :all]
            [clojure.string :refer [split]]))

(def part-one-lock
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

(def part-two-lock
  [[nil nil 1 nil nil]
   [nil 2 3 4 nil]
   [5 6 7 8 9]
   [nil "A" "B" "C" nil]
   [nil nil "D" nil nil]])

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")))

(deftest unlock-test
  (testing "unlock"
    (is (= (unlock part-one-lock [1 1] input) "53255"))
    (is (= (unlock part-two-lock [2 0] input) "7423A"))))

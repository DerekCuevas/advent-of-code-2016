(ns day-seven.core-test
  (:require [clojure.test :refer :all]
            [day-seven.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> (slurp "resources/input.txt")
      (split #"\n")))

(deftest a-test
  (testing "FIXME, I fail."
    (is (not= 1 1))))

(ns day-five.core-test
  (:require [clojure.test :refer :all]
            [day-five.core :refer :all]))

(deftest password-test
  (testing "find password"
    (is (= (part-one-password "reyedfim") "f97c354d"))))

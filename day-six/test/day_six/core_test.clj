(ns day-six.core-test
  (:require [clojure.test :refer :all]
            [day-six.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")))

(deftest message-test
  (testing "find message"
    (is (= (part-one-message input) "xhnqpqql"))
    (is (= (part-two-message input) "brhailro"))))

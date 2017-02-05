(ns day-three.core-test
  (:require [clojure.test :refer :all]
            [day-three.core :refer :all]
            [clojure.string :refer [split]]))

(defn parse [lines]
  (map #(read-string (str "[" % "]")) lines))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")
      (parse)))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= (count-triangles input) 1032))))

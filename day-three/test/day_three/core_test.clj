(ns day-three.core-test
  (:require [clojure.test :refer :all]
            [day-three.core :refer :all]
            [clojure.string :refer [split]]))

(defn parse-dims [dims]
  (read-string (str "[" dims "]")))

(defn parse [input]
  (map parse-dims input))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")
      (parse)))

(deftest count-triangles-test
  (testing "valid triangles"
    (is (= (count-triangles input) 1032))))

(deftest count-column-triangles-test
  (testing "count valid column triangles"
    (is (= (count-column-triangles input) 1838))))

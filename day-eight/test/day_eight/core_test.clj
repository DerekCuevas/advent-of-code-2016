(ns day-eight.core-test
  (:require [clojure.test :refer :all]
            [day-eight.core :refer :all]
            [clojure.string :refer [split]]))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"/n")))

(defn grid [[width height] init]
  (->> (repeat width init)
       (vec)
       (repeat height)
       (vec)))

(deftest pixels-test
  (testing "count of lit pixels"
    (is (= (pixels (grid [50 6] 0) input) 0))))

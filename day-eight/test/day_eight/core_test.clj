(ns day-eight.core-test
  (:require [clojure.test :refer :all]
            [day-eight.core :refer :all]
            [clojure.string :refer [split]]))

(def rect-regex #"rect (\d+)x(\d+)")
(def rotate-row-regex #"rotate row y=(\d+) by (\d+)")
(def rotate-column-regex #"rotate column x=(\d+) by (\d+)")

(defn instruction [type value]
  {:type type
   :value (mapv read-string (rest value))})

(defn parse-instruction [input]
  (let [rect-matches (re-matches rect-regex input)
        rotate-row-matches (re-matches rotate-row-regex input)
        rotate-column-matches (re-matches rotate-column-regex input)]
    (cond
      rect-matches
        (instruction :rect rect-matches)
      rotate-row-matches
        (instruction :rotate-row rotate-row-matches)
      rotate-column-matches
        (instruction :rotate-column rotate-column-matches))))

(defn parse [input]
  (map parse-instruction input))

(def input
  (-> "resources/input.txt"
      (slurp)
      (split #"\n")
      (parse)))

(defn grid [[width height] init]
  (->> (repeat width init)
       (vec)
       (repeat height)
       (vec)))

(deftest pixels-test
  (testing "count of lit pixels"
    (is (= (pixels (grid [50 6] 0) input) 116))))

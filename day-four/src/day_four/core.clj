(ns day-four.core
  (:require [clojure.string :refer [replace]])
  (:gen-class))

(def ^:private encrypted-format #"(\S+)-(\d+)\[(\S+)\]")

(defn- parse [s]
  (let [[_ encrypted-name sector-id checksum] (re-find encrypted-format s)]
    {:encrypted-name encrypted-name
     :sector-id (read-string sector-id)
     :checksum checksum}))

(defn- comparitor [[k1 v1] [k2 v2]]
  (if (= v1 v2)
    (compare k1 k2)
    (compare v2 v1)))

(defn- valid-checksum? [{:keys [encrypted-name checksum]}]
  (->> (replace encrypted-name #"-" "")
       (frequencies)
       (sort comparitor)
       (map first)
       (take 5)
       (apply str)
       (= checksum)))

(defn- filter-decoy [input]
  (->> input
       (map parse)
       (filter valid-checksum?)))

(defn- incc [c]
 (case c
   \z \a
   \space \space
   (char (inc (int c)))))

(defn- rotate [n c]
 (reduce (fn [a _] (incc a)) c (range n)))

(defn- shift [n s]
  (->> (replace s #"-" " ")
       (map (partial rotate n))
       (apply str)))

(defn- decrypt-name [{:keys [encrypted-name sector-id] :as data}]
  (assoc data :name (shift sector-id encrypted-name)))

;; part one
(defn sum-sector-ids [input]
  (->> input
       (filter-decoy)
       (map :sector-id)
       (apply +)))

;; part two
(defn find-sector-id [input]
  (->> input
       (filter-decoy)
       (map decrypt-name)
       (filter #(= (:name %) "northpole object storage"))
       (first)
       (:sector-id)))

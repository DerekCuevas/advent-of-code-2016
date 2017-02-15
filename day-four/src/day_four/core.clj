(ns day-four.core
  (:require [clojure.string :refer [split join replace]])
  (:gen-class))

(def ^:private encrypted-format #"(\S+)-(\d+)\[(\S+)\]")

(defn- parse [s]
  (let [[_ encrypted-name sector-id checksum] (re-find encrypted-format s)]
    {:encrypted-name encrypted-name
     :sector-id (read-string sector-id)
     :checksum checksum}))

(defn- compare-frequencies [[k1 v1] [k2 v2]]
  (if (= v1 v2)
    (compare k1 k2)
    (compare v2 v1)))

(defn- valid-checksum? [{:keys [encrypted-name checksum]}]
  (->> (replace encrypted-name #"-" "")
       (frequencies)
       (sort compare-frequencies)
       (map first)
       (take 5)
       (apply str)
       (= checksum)))

(defn- filter-decoy [input]
  (->> input
       (map parse)
       (filter valid-checksum?)))

(defn- rotate [n c]
  (-> (int c)
      (+ n)
      (- (int \a))
      (mod 26)
      (+ (int \a))
      (char)))

(defn- shift [n s]
  (apply str (map (partial rotate n) s)))

(defn- decrypt-name [{:keys [encrypted-name sector-id] :as data}]
  (->> (split encrypted-name #"-")
       (map (partial shift sector-id))
       (join " ")
       (assoc data :name)))

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

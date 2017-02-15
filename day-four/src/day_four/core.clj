(ns day-four.core
  (:require [clojure.string :refer [replace]])
  (:gen-class))

(def ^:private encrypted-format #"(\S+)-(\d+)\[(\S+)\]")

(defn- parse [s]
  (let [[_ encrypted-name sector-id checksum] (re-find encrypted-format s)]
    {:encrypted-name (replace encrypted-name #"-" "")
     :sector-id (read-string sector-id)
     :checksum checksum}))

(defn- comparitor [[k1 v1] [k2 v2]]
  (if (= v1 v2)
    (compare k1 k2)
    (compare v2 v1)))

(defn- valid-checksum? [{:keys [encrypted-name checksum]}]
  (->> (frequencies encrypted-name)
       (sort comparitor)
       (map first)
       (take 5)
       (apply str)
       (= checksum)))

(defn sum-sector-ids [input]
  (->> input
       (map parse)
       (filter valid-checksum?)
       (map :sector-id)
       (apply +)))

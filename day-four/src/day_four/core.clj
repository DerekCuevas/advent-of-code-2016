(ns day-four.core
  (:require [clojure.string :refer [replace]])
  (:gen-class))

(def format #"(\S+)-(\d+)\[(\S+)\]")

(defn parse [s]
  (let [[_ encrypted-name sector-id checksum] (re-find format s)]
    {:encrypted-name (replace encrypted-name #"-" "")
     :sector-id (read-string sector-id)
     :checksum checksum}))

(defn sum-sector-ids [input]
  (map parse input))

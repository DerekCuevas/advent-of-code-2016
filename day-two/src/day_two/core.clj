(ns day-two.core
  (:gen-class))

(defn move [direction [row column]]
  (case direction
    \U [(dec row) column]
    \R [row (inc column)]
    \D [(inc row) column]
    \L [row (dec column)]))

(defn move-inbounds [lock position direction]
  (let [next (move direction position)]
    (if (get-in lock next)
      next
      position)))

(defn travel [lock position line]
  (reduce (partial move-inbounds lock) position line))

(defn decode [lock {:keys [position code]} line]
  (let [next (travel lock position line)]
    {:position next
     :code (str code (get-in lock next))}))

(defn unlock [lock position input]
  (->> input
       (reduce (partial decode lock) {:position position :code ""})
       (:code)))

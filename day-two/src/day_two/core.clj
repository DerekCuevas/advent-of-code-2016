(ns day-two.core
  (:gen-class))

(defn move [direction [row column]]
  (case direction
    \U [(dec row) column]
    \R [row (inc column)]
    \D [(inc row) column]
    \L [row (dec column)]))

(defn inbounds? [lock position]
  (not (nil? (get-in lock position))))

(defn move-inbounds [lock position direction]
  (let [next (move direction position)]
    (if (inbounds? lock next)
      next
      position)))

(defn travel [lock position line]
  (reduce (partial move-inbounds lock) position line))

(defn decode [lock {:keys [position code]} line]
  (let [end-position (travel lock position line)]
    {:position end-position
     :code (str code (get-in lock end-position))}))

(defn unlock [lock position input]
  (->> input
       (reduce (partial decode lock) {:position position :code ""})
       (:code)))

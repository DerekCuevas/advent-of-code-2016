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

(defn travel [lock position instructions]
  (reduce (partial move-inbounds lock) position instructions))

(defn unlock [lock initial-position input]
  (loop [position initial-position
         [instructions & instructions-left] input
         code ""]
    (if (empty? instructions)
      code
      (let [next-position (travel lock position instructions)]
        (recur next-position
               instructions-left
               (str code (get-in lock next-position)))))))

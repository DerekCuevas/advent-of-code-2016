(ns day-eight.core
  (:gen-class))

(defn- transpose [coll]
  (apply mapv vector coll))

(defn- rotate [n coll]
  (let [offset (- (count coll) (mod n (count coll)))]
    (vec (concat (drop offset coll)
                 (take offset coll)))))

(defn- coordinates [x y]
  (for [x (range x)
        y (range y)]
    [y x]))

(defn- rect [[x y] value grid]
  (reduce #(assoc-in %1 %2 value) grid (coordinates x y)))

(defn- rotate-row [[row n] grid]
  (->> (nth grid row)
       (rotate n)
       (assoc grid row)))

(defn- rotate-column [[column n] grid]
  (->> (transpose grid)
       (rotate-row [column n])
       (transpose)))

(defn- lit [grid]
  (apply + (flatten grid)))

(defmulti operation
  (fn [grid instruction] (:type instruction)))

(defmethod operation :rect [grid instruction]
  (rect (:value instruction) 1 grid))

(defmethod operation :rotate-row [grid instruction]
  (rotate-row (:value instruction) grid))

(defmethod operation :rotate-column [grid instruction]
  (rotate-column (:value instruction) grid))

(defn pixels [grid input]
  (->> input
       (reduce operation grid)
       (lit)))

(ns aoc-2020.day03
  (:require
    [clojure.string :refer [index-of]]))

(defn map-explorer [map]
  (let [line  (index-of map \newline)
        coord (fn [a b] (+ (* a (inc line)) (mod b line)))]
    (fn [[row col]] (get map (coord row col)))))

(defn count-trees [input r d]
  (->> (range)
       (map (juxt (partial * d) (partial * r)))
       (map (map-explorer input))
       (take-while some?)
       (filter #{\#})
       (count)))

(defn all-slopes [input]
  (let [counter (partial count-trees input)]
    (->> (list [1 1] [3 1] [5 1] [7 1] [1 2])
         (map (partial apply counter))
         (reduce *))))

(defn part-1 [input]
  (count-trees input 3 1))

(defn part-2 [input]
  (all-slopes input))

(ns aoc-2019.day19
  (:require [aoc-2019.intcode :as intcode]))

(defn coordinates [n]
  (fn [x] (list (quot x n) (mod x n))))

(defn beam [input]
  (let [program (intcode/parse-program input)]
    (comp first (partial intcode/run-program program))))

(defn find-100-square [input]
  (let [stationary? (comp zero? (beam input))]
    (loop [[x y] (repeat 500)]
      (cond
        (stationary? [x y])        (recur [x (inc y)])
        (stationary? [x (+ y 99)]) (recur [(inc x) y])
        (stationary? [(+ x 99) y]) (recur [x (inc y)])
        :else                      [x y]))))

(defn part-1 [input]
  (->> (range (* 50 50))
       (map (comp (beam input) (coordinates 50)))
       (filter (complement zero?))
       (count)))

(defn part-2 [input]
  (let [[x y] (find-100-square input)]
    (+ (* x 10000) y)))

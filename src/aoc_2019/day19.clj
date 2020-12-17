(ns aoc-2019.day19
  (:require
    [aoc-2019.intcode :refer [parse-program run-program]]))

(defn coordinates [n]
  (fn [x] (list (quot x n) (mod x n))))

(defn part-1 [input]
  (let [beam (partial run-program (parse-program input))]
    (->> (range (* 50 50))
         (map (comp first beam (coordinates 50)))
         (filter (complement zero?))
         (count))))

(defn part-2 [input] 0)

(ns aoc-2019.day05
  (:require
    [aoc-2019.intcode :refer [parse-program run-program]]))

(defn part-1 [input]
  (peek (run-program (parse-program input) [1])))

(defn part-2 [input]
  (peek (run-program (parse-program input) [5])))

(ns aoc-2019.day09
  (:require
    [aoc-2019.intcode :refer [parse-program run-program]]
    [clojure.string :refer [split-lines trim]]))

(defn part-1 [input]
  (peek (run-program (parse-program input) [1])))

(defn part-2 [input]
  (peek (run-program (parse-program input) [2])))

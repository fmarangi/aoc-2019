(ns aoc-2019.day11-test
  (:require [aoc-2019.day11 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day11.txt")]
    (is (= (part-1 input) 1932))
    (is (= (part-2 input) "EGHKGJER"))))

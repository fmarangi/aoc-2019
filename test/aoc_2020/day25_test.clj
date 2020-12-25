(ns aoc-2020.day25-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day25 :refer :all]
    [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day25.txt")]
    (is (= (part-1 input) 296776))))

(ns aoc-2020.day13-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day13 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-earliest-bus
  (is (= (find-earliest-bus 939 [7 13 59 31 19]) 295))
  (is (= (chinese-remainder [7 13 59 31 19] [0 12 55 25 12]) 1068781)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day13.txt")]
    (is (= (part-1 input) 119))
    (is (= (part-2 input) 1106724616194525))))

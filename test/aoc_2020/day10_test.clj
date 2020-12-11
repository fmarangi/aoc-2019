(ns aoc-2020.day10-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day10 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-differences
  (is (= (find-differences [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3]) 220))
  (is (= (find-arrangements [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3]) 19208)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day10.txt")]
    (is (= (part-1 input) 2400))
    (is (= (part-2 input) 338510590509056))))

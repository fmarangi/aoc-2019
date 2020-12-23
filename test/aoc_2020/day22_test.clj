(ns aoc-2020.day22-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day22 :refer :all]
    [clojure.test :refer :all]))

(deftest test-calc-score
  (is (= (score [3 2 10 6 8 5 9 4 7 1]) 306)))

(deftest test-play-game
  (is (= (score (second (play-game [9 2 6 3 1] [5 8 4 7 10]))) 306))
  (is (= (score (second (play-game-recursive [9 2 6 3 1] [5 8 4 7 10]))) 291)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day22.txt")]
    ;    (is (= (part-2 input) 29177))
    (is (= (part-1 input) 33403))))

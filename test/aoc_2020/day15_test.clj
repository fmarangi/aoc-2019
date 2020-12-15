(ns aoc-2020.day15-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day15 :refer :all]
    [clojure.test :refer :all]))

(deftest test-memory-game
  (is (= (memory-game [0 3 6] 2020) 436))
  (is (= (memory-game [1 3 2] 2020) 1))
  (is (= (memory-game [2 1 3] 2020) 10))
  (is (= (memory-game [1 2 3] 2020) 27))
  (is (= (memory-game [2 3 1] 2020) 78))
  (is (= (memory-game [3 2 1] 2020) 438))
  (is (= (memory-game [3 1 2] 2020) 1836)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day15.txt")]
    (is (= (part-1 input) 620))
;    (is (= (part-2 input) 110871))
    ))

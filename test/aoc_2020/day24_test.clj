(ns aoc-2020.day24-test
  (:require [aoc-2019.utils :refer [puzzle-input]]
            [aoc-2020.day24 :refer :all]
            [clojure.test :refer :all]))

(deftest test-flip-tiles
  (are [input days expected]
       (= (count (filter (comp true? second) (flip-n-times (parse-input input) days))) expected)
       (puzzle-input "2020/examples/day24a.txt") 1 15
       (puzzle-input "2020/examples/day24a.txt") 2 12
       (puzzle-input "2020/examples/day24a.txt") 10 37
       (puzzle-input "2020/examples/day24a.txt") 20 132))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day24.txt")]
    (are [actual expected] (= actual expected)
         ; (part-2 input) 3608
         (part-1 input) 354)))

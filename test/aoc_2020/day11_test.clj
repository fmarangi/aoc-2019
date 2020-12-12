(ns aoc-2020.day11-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day11 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-stable-state
  (is (= (find-stable-state "#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##" (next-state seat-at 4)) 37))
  (is (= (find-stable-state "#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##" (next-state visible-seat 5)) 26)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day11.txt")]
    (is (= (part-1 input) 2476))
    (is (= (part-2 input) 2257))))

(ns aoc-2020.day12-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day12 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-distance
  (is (= (find-distance (parse-input "F10
N3
F7
R90
F11")) 25)))

(deftest test-find-distance-with-waypoint
  (is (= (find-distance-with-waypoint (parse-input "F10
N3
F7
R90
F11")) 286)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day12.txt")]
    (is (= (part-1 input) 1496))
    (is (= (part-2 input) 63843))))

(ns aoc-2019.intcode-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.intcode :refer :all]))

(deftest test-modes
  (is (= (modes 1002) [0 1 0]))
  (is (= (modes 21202) [2 1 2])))

(deftest test-run-program
  (is (= (run-program [109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99]) [109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99])))

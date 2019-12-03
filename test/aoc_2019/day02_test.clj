(ns aoc-2019.day02-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day02 :refer :all]))

(deftest run
  (is (= (run-program [1 0 0 0 99]) [2 0 0 0 99]))
  (is (= (run-program [1 1 1 4 99 5 6 0 99]) [30 1 1 4 2 5 6 0 99])))

(deftest solve-puzzle
  (let [input (slurp "resources/day02.txt")]
    (is (= (part-1 input) 3409710))
    (is (= (part-2 input) 7912))))

(ns aoc-2019.day03-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day03 :refer :all]))

(deftest calculate-manhattan-distance
  (is (= (manhattan [0 0] [3 3]) 6))
  (is (= (manhattan [0 0] [-5 3]) 8))
  (is (= (manhattan [0 0] [-4 -3]) 7)))

;(deftest solve-puzzle
;  (let [input (slurp "resources/day02.txt")]
;    (is (= (part-1 input) 3409710))
;    (is (= (part-2 input) 7912))))

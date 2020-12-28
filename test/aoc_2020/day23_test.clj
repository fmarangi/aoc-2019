(ns aoc-2020.day23-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day23 :refer :all]
    [clojure.test :refer :all]))

(deftest test-crab-cups
  (are [actual expected] (= (apply str (take 8 actual)) expected)
       (crab-cups (parse-input "389125467") 10) "92658374"
       (crab-cups (parse-input "389125467") 100) "67384529"))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day23.txt")]
    ; (is (= (part-2 input) 562136730660))
    (is (= (part-1 input) "36542897"))))

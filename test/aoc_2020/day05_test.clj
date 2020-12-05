(ns aoc-2020.day05-test
  (:require
    [clojure.test :refer :all]
    [aoc-2020.day05 :refer :all]))

(deftest calculate-seat-location
  (are [actual expected] (= actual expected)
    (parse-seat "FBFBBFFRLR") 357
    (parse-seat "BFFFBBFRRR") 567
    (parse-seat "FFFBBBFRRR") 119
    (parse-seat "BBFFBBFRLL") 820))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day05.txt")]
    (is (= (part-1 input) 938))
    (is (= (part-2 input) 696))))

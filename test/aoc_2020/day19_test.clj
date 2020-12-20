(ns aoc-2020.day19-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day19 :refer :all]
    [clojure.test :refer :all]))

(deftest test-message-validity
  (are [input parser result] (= (count (apply message-validity (parser input))) result)
       (puzzle-input "2020/examples/day19a.txt") parse-input 2
       (puzzle-input "2020/examples/day19b.txt") parse-input 3
       (puzzle-input "2020/examples/day19b.txt") parse-input-pt2 12))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day19.txt")]
    (is (= (part-1 input) 190))
    (is (= (part-2 input) 311))))

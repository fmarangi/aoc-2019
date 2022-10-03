(ns aoc-2017.day09-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day09 :refer :all]))

(deftest test-score
  (are [a b] (= a b)
    (score "{}") 1
    (score "{{{}}}") 6
    (score "{{},{}}") 5
    (score "{{{},{},{{}}}}") 16
    (score "{<a>,<a>,<a>,<a>}") 1
    (score "{{<ab>},{<ab>},{<ab>},{<ab>}}") 9
    (score "{{<!!>},{<!!>},{<!!>},{<!!>}}") 9
    (score "{{<a!>},{<a!>},{<a!>},{<ab>}}") 3))

(deftest test-garbage
  (are [a b] (= a b)
    (garbage "<>") 0
    (garbage "<random characters>") 17
    (garbage "<<<<>") 3
    (garbage "<{!>}>") 2
    (garbage "<!!>") 0
    (garbage "<!!>>") 0
    (garbage "<{o\"i!a,<{i<a>") 10))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day09.txt")]
    (is (= (part-1 input) 11347))
    (is (= (part-2 input) 5404))))

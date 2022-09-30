(ns aoc-2015.day11-test
  (:require [clojure.test :refer :all]
            [aoc-2015.day11 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]))

(deftest test-no-forbidden
  (is (= (no-forbidden "francesca") true))
  (is (= (no-forbidden "francesco") false)))

(deftest test-has-straight
  (is (= (has-straight "xyzkkkk") true))
  (is (= (has-straight "abdiajq") false)))

(deftest test-has-pairs
  (is (= (has-pairs "aabbccdd") true))
  (is (= (has-pairs "aaaaaaaa") false))
  (is (= (has-pairs "abcdefgh") false)))

(deftest solve-puzzle
  (let [input (puzzle-input "2015/day11.txt")]
    (is (= (part-1 input) "vzbxxyzz"))
    (is (= (part-2 input) "vzcaabcc"))))

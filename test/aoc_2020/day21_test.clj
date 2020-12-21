(ns aoc-2020.day21-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day21 :refer :all]
    [clojure.test :refer :all]))

(deftest test-safe-ingredients
  (let [input (parse-input (puzzle-input "2020/examples/day21a.txt"))]
    (is (= (count (safe-ingredients input)) 5))))

(deftest test-canonical-list
  (let [input (parse-input (puzzle-input "2020/examples/day21a.txt"))]
    (is (= (canonical-list input) "mxmxvkd,sqjhc,fvjkl"))))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day21.txt")]
    (is (= (part-1 input) 2569))
    (is (= (part-2 input) "vmhqr,qxfzc,khpdjv,gnrpml,xrmxxvn,rfmvh,rdfr,jxh"))))

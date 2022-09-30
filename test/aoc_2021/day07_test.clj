(ns aoc-2021.day07-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day07 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day07.txt")]
    (is (= (part-1 input) 335271))
    (is (= (part-2 input) 95851339))))

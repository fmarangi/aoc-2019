(ns aoc-2021.day14-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day14 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day14.txt")]
    (is (= (part-1 input) 2712))
    (is (= (part-2 input) 8336623059567))))

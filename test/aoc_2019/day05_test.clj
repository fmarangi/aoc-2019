(ns aoc-2019.day05-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day05 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/day05.txt")]
    (is (= (part-1 input) 5074395))
    (is (= (part-2 input) 8346937))))

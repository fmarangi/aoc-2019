(ns aoc-2019.day22-test
  (:require [aoc-2019.day22 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day22.txt")]
    (is (= (part-1 input) 3074))
    (is (= (part-2 input) 104073967000066))))

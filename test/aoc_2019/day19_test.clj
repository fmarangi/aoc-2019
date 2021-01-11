(ns aoc-2019.day19-test
  (:require [aoc-2019.day19 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day19.txt")]
    (is (= (part-1 input) 217))
    (is (= (part-2 input) 6840937))))

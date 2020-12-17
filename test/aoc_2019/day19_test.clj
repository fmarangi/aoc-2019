(ns aoc-2019.day19-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day19 :refer :all]
    [aoc-2019.utils :refer [puzzle-input]]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day19.txt")]
    (is (= (part-1 input) 217))))

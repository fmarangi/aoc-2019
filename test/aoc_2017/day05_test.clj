(ns aoc-2017.day05-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day05 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day05.txt")]
    (is (= (part-1 input) 388611))
;   (is (= (part-2 input) 27763113))
    ))

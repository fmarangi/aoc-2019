(ns aoc-2020.day09-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day09 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-invalid-number
  (is (= (find-invalid-number [35 20 15 25 47 40 62 55 65 95 102 117 150 182 127 219 299 277 309 576] 5) 127)))

(deftest test-find-contiguous-sum
  (is (= (find-contiguous-sum [35 20 15 25 47 40 62 55 65 95 102 117 150 182 127 219 299 277 309 576] 127) 62)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day09.txt")]
    (is (= (part-1 input) 25918798))
    (is (= (part-2 input) 3340942))))

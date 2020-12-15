(ns aoc-2020.day14-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day14 :refer :all]
    [clojure.test :refer :all]))

(deftest test-binary-mask
  (is (= (apply-mask "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X" 11) 73))
  (is (= (apply-mask "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X" 101) 101))
  (is (= (apply-mask "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X" 0) 64)))

(deftest test-get-memory-addresses
  (is (= (set (get-memory-addresses "000000000000000000000000000000X1001X" 42)) #{26 27 58 59}))
  (is (= (set (get-memory-addresses "00000000000000000000000000000000X0XX" 26)) #{16 17 18 19 24 25 26 27})))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day14.txt")]
    (is (= (part-1 input) 6631883285184))
    (is (= (part-2 input) 3161838538691))))

(ns aoc-2020.day08-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day08 :refer :all]
    [clojure.test :refer :all]))

(deftest test-run-bootcode
  (is (= (second (run-bootcode (parse-input "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6"))) 5)))

(deftest test-fix-bootcode
  (is (= (fix-bootcode (parse-input "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6")) 8)))
;
(deftest solve-puzzle
  (let [input (puzzle-input "2020/day08.txt")]
    (is (= (part-1 input) 1200))
    (is (= (part-2 input) 1023))))

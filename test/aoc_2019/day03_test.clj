(ns aoc-2019.day03-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day03 :refer :all]))

(deftest calculate-manhattan-distance
  (is (= (manhattan [0 0] [3 3]) 6))
  (is (= (manhattan [0 0] [-5 3]) 8))
  (is (= (manhattan [0 0] [-4 -3]) 7))
  (is (= (manhattan [0 0 0] [3 -3 3]) 9)))

(deftest move-to-next
  (is (= ((move :U) [0 0]) [0 1]))
  (is (= ((move :L) [0 0]) [-1 0]))
  (is (= ((move :R) [0 0]) [1 0]))
  (is (= ((move :D) [0 0]) [0 -1])))

(deftest parse-step
  (is (= (step "R888") [:R 888])))

(deftest get-path
  (is (= (vec (path "R3" [0 0])) [[1 0] [2 0] [3 0]]))
  (is (= (vec (path "D3" [0 0])) [[0 -1] [0 -2] [0 -3]])))

;(deftest solve-puzzle
;  (let [input (slurp "resources/day02.txt")]
;    (is (= (part-1 input) 3409710))
;    (is (= (part-2 input) 7912))))

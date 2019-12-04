(ns aoc-2019.day03-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day03 :refer :all]))

(deftest move-to-next
  (is (= ((move :U) [0 0]) [0 1]))
  (is (= ((move :L) [0 0]) [-1 0]))
  (is (= ((move :R) [0 0]) [1 0]))
  (is (= ((move :D) [0 0]) [0 -1])))

(deftest parse-step
  (is (= (step "R888") [:R 888])))

(deftest get-path
  (is (= (vec (path "R3" [0 0])) [[1 0] [2 0] [3 0]]))
  (is (= (vec (path "D3" [0 0])) [[0 -1] [0 -2] [0 -3]]))
  (is (= (vec (full-path "R3,D3" [0 0])) [[1 0] [2 0] [3 0] [3 -1] [3 -2] [3 -3]])))

(deftest find-closest-cross
  (is (= (closest-cross "R8,U5,L5,D3" "U7,R6,D4,L4") 6))
  (is (= (closest-cross "R75,D30,R83,U83,L12,D49,R71,U7,L72"
                        "U62,R66,U55,R34,D71,R55,D58,R83") 159))
  (is (= (closest-cross "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
                        "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") 135)))

(deftest solve-puzzle
  (let [input (slurp "resources/day03.txt")]
    (is (= (part-1 input) 232))))
;    (is (= (part-2 input) 7912))))

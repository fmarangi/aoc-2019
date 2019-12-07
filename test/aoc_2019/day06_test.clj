(ns aoc-2019.day06-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day06 :refer :all]))

(deftest calc-checksum
  (is (= (checksum "COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L" :COM) 42)))

(deftest find-santa
  (is (= (orbital-transfers "COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
K)YOU
I)SAN" :YOU :SAN)) 4))

(deftest solve-puzzle
  (let [input (slurp "resources/day06.txt")]
    (is (= (part-1 input) 621125))
    (is (= (part-2 input) 550))))

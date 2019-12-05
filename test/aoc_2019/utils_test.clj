(ns aoc-2019.utils-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.utils :refer :all]))

(deftest calculate-manhattan-distance
  (is (= (manhattan [0 0] [3 3]) 6))
  (is (= (manhattan [0 0] [-5 3]) 8))
  (is (= (manhattan [0 0] [-4 -3]) 7))
  (is (= (manhattan [0 0 0] [3 -3 3]) 9)))

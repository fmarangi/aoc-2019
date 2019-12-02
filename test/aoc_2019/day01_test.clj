(ns aoc-2019.day01-test
  (:require
    [clojure.core :only [slurp]]
    [clojure.test :refer :all]
    [aoc-2019.day01 :refer :all]))

(deftest calculate-mass
  (is (= (mass 12) 2))
  (is (= (mass 14) 2))
  (is (= (mass 1969) 654))
  (is (= (mass 100756) 33583)))

(deftest calculate-fuel
  (is (= (fuel 12) 2))
  (is (= (fuel 14) 2))
  (is (= (fuel 1969) 966))
  (is (= (fuel 100756) 50346)))

(deftest solve-puzzle
  (let [input (slurp "resources/day01.txt")]
    (is (= (part-1 input) 3381405))
    (is (= (part-2 input) 5069241))))

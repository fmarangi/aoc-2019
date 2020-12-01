(ns aoc-2019.day04-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day04 :refer :all]))

(deftest valid-password
  (is (= (password? 111111) true))
  (is (= (password? 122345) true))
  (is (= (password? 223450) false))
  (is (= (password? 123789) false)))

(deftest no-larger-groups
  (is (= (strip-larger-groups 112233) 112233))
  (is (= (strip-larger-groups 123444) 1234))
  (is (= (strip-larger-groups 111122) 122)))

(deftest solve-puzzle
  (let [input (slurp "resources/2019/day04.txt")]
    (is (= (part-1 input) 1864))
    (is (= (part-2 input) 1258))))

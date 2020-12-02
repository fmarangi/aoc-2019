(ns aoc-2020.day02-test
  (:require
    [clojure.test :refer :all]
    [aoc-2020.day02 :refer :all]))

(deftest check-password-validity
  (are [actual expected] (= actual expected)
    (valid-password "1-3 a" "abcde") true
    (valid-password "1-3 b" "cdefg") false
    (valid-password "2-9 c" "ccccccccc") true))

(deftest check-toboggan-password-validity
  (are [actual expected] (= actual expected)
    (valid-toboggan-password "1-3 a" "abcde") true
    (valid-toboggan-password "1-3 b" "cdefg") false
    (valid-toboggan-password "2-9 c" "ccccccccc") false))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day02.txt")]
    (is (= (part-1 input) 614))
    (is (= (part-2 input) 354))))

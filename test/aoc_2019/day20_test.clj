(ns aoc-2019.day20-test
  (:require
    [aoc-2019.utils :refer [a*]]
    [aoc-2019.day20 :refer :all]
    [clojure.test :refer :all]))

(deftest test-find-path
  (are [actual expected] (= (dec (count actual)) expected)
    (find-path (slurp "resources/examples/day20a.txt")) 23
    (find-path (slurp "resources/examples/day20b.txt")) 58))

(deftest calc-outer-portals
  (are [m c] (= (count (filter (comp (is-outer? m) second) (portals m))) c)
    (slurp "resources/examples/day20a.txt") 5
    (slurp "resources/examples/day20b.txt") 12))

(deftest test-find-path-with-levels
  (are [actual expected] (= (dec (count actual)) expected)
    (find-path-with-levels (slurp "resources/examples/day20c.txt")) 396))

(deftest solve-puzzle
  (let [input (slurp "resources/day20.txt")]
    (are [actual expected] (= actual expected)
      (part-1 input) 604
      (part-2 input) 7166)))

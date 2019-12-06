(ns aoc-2019.day05-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day05 :refer :all]))

(->> "resources/day05.txt" (slurp) (parse-input) (run-program))

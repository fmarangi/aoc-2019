(ns aoc-2020.day07-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day07 :refer :all]
    [clojure.test :refer :all]))

(deftest test-count-all-bags
  (is (= (dec (count-all-bags (parse-input "shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.") "shiny gold")) 126)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day07.txt")]
    (is (= (part-1 input) 213))
    (is (= (part-2 input) 38426))))

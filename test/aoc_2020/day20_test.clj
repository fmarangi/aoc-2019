(ns aoc-2020.day20-test
  (:require [aoc-2019.utils :refer [puzzle-input]]
            [aoc-2020.day20 :refer :all]
            [clojure.test :refer :all]))

(deftest test-find-corners
  (are [input result] (= (->> input find-corners (map first) (reduce *)) result)
       (parse-input (puzzle-input "2020/examples/day20a.txt")) 20899048083289))

(deftest test-find-sea-monsters
  (is (= (find-sea-monsters (puzzle-input "2020/examples/day20b.txt")) 2)))

(deftest test-water-roughness
  (is (= (water-roughness (puzzle-input "2020/examples/day20b.txt")) 273)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day20.txt")]
    (is (= (part-1 input) 2699020245973))
    (is (= (part-2 input) 2012))))

(ns aoc-2019.day16-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day16 :refer :all]
    [clojure.string :refer [trim]]))

(deftest calc-seq-after-x-phases
  (are [actual expected] (= actual expected)
    (signal "12345678" 4) "01029498"
    (signal "80871224585914546619083218645595" 100) "24176176"
    (signal "19617804207202209144916044189917" 100) "73745418"
    (signal "69317163492948606335995924319873" 100) "52432133"))

(deftest calc-seq-from-bottom
  (are [actual expected] (= actual expected)
    (bottom-up "03036732577212944063491565474664" 100) "84462026"
    (bottom-up "02935109699940807407585447034323" 100) "78725270"
    (bottom-up "03081770884921959731165446850517" 100) "53553731"))

(deftest solve-puzzle
  (let [input (trim (slurp "resources/day16.txt"))]
    ;(is (= (part-2 input) "36265589"))
    (is (= (part-1 input) "27831665"))))

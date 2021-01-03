(ns aoc-2019.day01
  (:require [clojure.string :refer [split-lines]]))

(defn mass [x]
  (- (quot x 3) 2))

(defn fuel [x]
  (reduce + (take-while pos? (drop 1 (iterate mass x)))))

(defn part-1 [x]
  (reduce + (map (comp mass read-string) (split-lines x))))

(defn part-2 [x]
  (reduce + (map (comp fuel read-string) (split-lines x))))

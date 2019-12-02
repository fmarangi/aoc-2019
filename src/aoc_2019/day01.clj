(ns aoc-2019.day01
  (:require [clojure.string :refer [split-lines]]))

(defn mass [x]
  (- (quot x 3) 2))

(defn fuel [x]
  (apply + (take-while pos? (iterate mass (mass x)))))

(defn part-1 [x]
  (apply + (map #(mass (Integer/parseInt %)) (split-lines x))))

(defn part-2 [x]
  (apply + (map #(fuel (Integer/parseInt %)) (split-lines x))))

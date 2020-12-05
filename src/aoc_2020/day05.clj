(ns aoc-2020.day05
  (:require
    [clojure.string :refer [split-lines]]))

(defn parse-seat [code]
  (->> (reverse code)
       (map-indexed (fn [i x] (bit-shift-left ({\B 1 \R 1} x 0) i)))
       (reduce +)))

(defn part-1 [input]
  (->> (split-lines input)
       (map parse-seat)
       (reduce max)))

(defn part-2 [input]
  (->> (split-lines input)
       (map parse-seat)
       (sort)
       (reduce (fn [p c] (if (= c (inc p)) c p)))
       (inc)))

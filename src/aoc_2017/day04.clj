(ns aoc-2017.day04
  (:require [clojure.string :as s]))

(defn words [s]
  (s/split s #"\s+"))

(defn sort-chars [word]
  (apply str (sort word)))

(defn valid [words]
  (->> (frequencies words)
       (vals)
       (apply max)
       (= 1)))

(defn part-1 [input]
  (->> (s/split-lines input)
       (filter (comp valid words))
       (count)))

(defn part-2 [input]
  (->> (s/split-lines input)
       (map #(map sort-chars (words %)))
       (filter valid)
       (count)))

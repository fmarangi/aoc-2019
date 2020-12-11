(ns aoc-2020.day10
  (:require
    [clojure.string :refer [split-lines]]))

(defn- parse-input [input]
  (map read-string (split-lines input)))

(defn- diffs [numbers]
  (let [numbers (sort numbers)]
    (map - numbers (cons 0 numbers))))

(defn find-differences [numbers]
  (let [freqs (frequencies (diffs numbers))]
    (* (freqs 1) (inc (freqs 3)))))

; Sequence of 2 1s = 3 consecutive numbers
; a b c
; a - c

; Sequence of 3 1s = 4 consecutive numbers
; a b c d
; a - c d
; a b - d
; a - - d

; Sequence of 4 1s = 5 consecutive numbers
; a b c d e
; a - c d e
; a b - d e
; a b c - e
; a b - - e
; a - - d e
; a - c - e

(defn find-arrangements [numbers]
  (->> (diffs numbers)
       (partition-by identity)
       (filter (partial every? #{1}))
       (map (comp {1 1 2 2 3 4 4 7} count)) ; 2x1 = 2 arrangements, 3x1 = 4 arrangements, 4x1 = 7 arrangements
       (reduce *)))

(defn part-1 [input]
  (find-differences (parse-input input)))

(defn part-2 [input]
  (find-arrangements (parse-input input)))

(ns aoc-2021.day08
  (:require [clojure.string :as s]))

(defn words [s]
  (s/split s #" "))

(defn int-value [digit]
  (->> (map #(bit-shift-left 1 (- (int %) 97)) digit)
       (reduce +)))

(defn part-1 [input]
  (->> (s/split-lines input)
       (map #(s/split % #" \| "))
       (mapcat (comp words second))
       (map count)
       (filter #{2 3 4 7})
       (count)))

(defn by-length [digits length]
  (->> (filter #(= length (count %)) digits)
       (map int-value)))

(defn matching [digits value]
  (first (filter #(= (bit-and % value) value) digits)))

(defn- unique-values [digits]
  (let [l {2 1, 3 7, 4 4, 7 8}
        p #(let [x (l (count %))]
             (when x {x (int-value %)}))]
    (into {} (keep p digits))))

(defn mapping [digits]
  (let [found (unique-values digits)

        ; six segments
        six (by-length digits 6)
        found (assoc found 9 (matching six (found 4)))
        six (filter (complement #{(found 9)}) six)
        found (assoc found 0 (matching six (found 1)))
        found (assoc found 6 (first (filter (complement #{(found 0)}) six)))

        ; five segments
        five (by-length digits 5)
        found (assoc found 3 (matching five (found 1)))
        five (filter (complement #{(found 3)}) five)
        found (assoc found 5 (first (filter #(= (bit-and (found 9) %) %) five)))
        found (assoc found 2 (first (filter (complement #{(found 5)}) five)))]
    (into {} (map (fn [[k v]] {v k}) found))))

(defn- entry-value [[values digits]]
  (let [m (mapping values)
        v (map (comp m int-value) digits)]
    (reduce + (map * v (iterate #(quot % 10) 1000)))))

(defn part-2 [input]
  (->> (s/split-lines input)
       (map #(map words (s/split % #" \| ")))
       (map entry-value)
       (reduce +)))

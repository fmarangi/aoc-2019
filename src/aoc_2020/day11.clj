(ns aoc-2020.day11
  (:require
    [clojure.string :refer [index-of]]))

(defn seat-at [grid pos]
  (fn [offset] (get grid (+ pos offset))))

(defn visible-seat [grid pos]
  (fn [offset]
    (->> (reductions + (repeat offset))
         (map (seat-at grid pos))
         (take-while some?)
         (some #{\# \L \newline}))))

(defn occupied [grid seat-fn]
  (let [l (inc (index-of grid \newline))]
    (fn [x]
      (->> [(- -1 l) (- l) (- 1 l) -1 1 (+ l -1) l (+ l 1)]
           (map (seat-fn grid x))
           (filter #{\#})
           (count)))))

(defn next-state [seat-fn n]
  (fn [grid]
    (let [taken (occupied grid seat-fn)
          next  (fn [i c] (case c \# (if (> n (taken i)) \# \L) \L (if (zero? (taken i)) \# \L) c))]
      (apply str (map-indexed next grid)))))

(defn find-stable-state [grid next-state-fn]
  (let [count-occupied #(get (frequencies %) \#)]
    (->> (iterate next-state-fn grid)
         (partition 2 1)
         (filter (partial apply =))
         (ffirst)
         (count-occupied))))

(defn part-1 [input]
  (find-stable-state input (next-state seat-at 4)))

(defn part-2 [input]
  (find-stable-state input (next-state visible-seat 5)))

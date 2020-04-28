(ns aoc-2019.day10
  (:require
    [aoc-2019.utils :refer [angle manhattan]]
    [clojure.string :refer [split-lines trim]]))

(defn indexes [k]
  (->> k (map-indexed vector)
         (filter (comp #{\#} second))
         (map first)))

(defn normalize-angle [a]
  (mod (+ a (/ Math/PI 2)) (* Math/PI 2)))

(defn parse-input [input]
  (->> input (trim)
             (split-lines)
             (map indexes)
             (map-indexed vector)
             (map (fn [[a b]] (map #(list % a) b)))
             (reduce concat)))

(defn detected [a]
  (let [k #(map (partial angle %) (remove #{%} a))]
    (->> a (map (comp count distinct k))
           (interleave a)
           (apply hash-map))))

(defn best-location [a]
  (->> a (detected) (vals) (apply max)))

(defn vaporize [a n]
  (let [best (key (apply max-key val (detected a)))
        calc (fn [[x y]] (+ (* x 100) y))]
    (->> a (remove #{best})
           (map (juxt identity (partial manhattan best)))
           (sort-by second)
           (map first)
           (group-by #(normalize-angle (angle best %)))
           (into (sorted-map))
           (vals)
           (apply interleave)
           (take n)
           (last)
           (calc))))

(defn part-1 [input]
  (best-location (parse-input input)))

(defn part-2 [input]
  (vaporize (parse-input input) 200))

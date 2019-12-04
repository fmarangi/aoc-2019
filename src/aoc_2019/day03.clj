(ns aoc-2019.day03
  (:require [clojure.string :refer [split trim]]))

(defn to-int [n]
  (-> n str Integer/parseInt))

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (->> (map list a b)
       (map #(Math/abs (apply - %)))
       (reduce +)))

(defn move [direction]
  (let [m {:U #(vector %1 (inc %2))
           :D #(vector %1 (dec %2))
           :L #(vector (dec %1) %2)
           :R #(vector (inc %1) %2)}]
    (partial apply (m direction))))

(defn step [s]
  (vector (keyword (subs s 0 1)) (to-int (subs s 1))))

(defn path [p start]
  (let [[d times] (step p)]
    (->> start (iterate (move d))
               (rest)
               (take times))))

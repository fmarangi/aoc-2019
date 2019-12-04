(ns aoc-2019.day03
  (:require
    [aoc-2019.utils :refer [manhattan to-int]]
    [clojure.set :refer [intersection]]
    [clojure.string :refer [split split-lines trim]]))

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

(defn full-path [p start]
  (loop [parts (apply list (split p #"\,"))
         full  (list start)]
    (if (empty? parts)
      (rest full)
      (recur (rest parts)
             (concat full (path (first parts)
                          (last full)))))))

(defn closest-cross [a b]
  (->> [a b] (map #(set (full-path % [0 0])))
             (apply intersection)
             (map (partial manhattan [0 0]))
             (apply min)))

(defn part-1 [input]
  (->> input (trim)
             (split-lines)
             (map trim)
             (apply closest-cross)))

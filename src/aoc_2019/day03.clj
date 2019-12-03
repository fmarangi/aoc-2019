(ns aoc-2019.day03)

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (->> (map list a b)
       (map #(Math/abs (apply - %)))
       (reduce +)))

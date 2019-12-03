(ns aoc-2019.day03)

(defn manhattan [a b]
  (->> (map vector a b)
       (map #(Math/abs (apply - %)))
       (reduce +)))

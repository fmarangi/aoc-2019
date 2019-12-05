(ns aoc-2019.utils)

(defn to-int [x]
  (-> x str Integer/parseInt))

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (->> (map list a b)
       (map #(Math/abs (apply - %)))
       (reduce +)))

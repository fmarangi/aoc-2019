(ns aoc-2019.utils)

(defn to-int [x]
  (-> x str Integer/parseInt))

(defn digits [x]
  (->> x (iterate #(quot % 10))
         (take-while pos?)
         (map #(mod % 10))))

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (->> (map list a b)
       (map #(Math/abs (apply - %)))
       (reduce +)))

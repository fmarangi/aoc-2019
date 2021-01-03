(ns aoc-2017.day01)

(defn- parse-input [input]
  (map (comp read-string str) input))

(defn part-1 [input]
  (let [i (parse-input input)]
    (->> (drop 1 (cycle i))
         (map list i)
         (filter (partial apply =))
         (map first)
         (reduce +))))

(defn part-2 [input]
  (let [i (parse-input input)]
    (->> (drop (quot (count i) 2) (cycle i))
         (map list i)
         (filter (partial apply =))
         (map first)
         (reduce +))))

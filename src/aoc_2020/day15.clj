(ns aoc-2020.day15
  (:require
    [clojure.string :refer [split]]))

(defn memory-game [numbers after]
  (let [nums (butlast numbers)
        init (zipmap nums (range))
        calc (fn [[l s i]] [(- s (get i l s)) (inc s) (assoc i l s)])]
    (->> (iterate calc [(last numbers) (count init) init])
         (map first)
         (concat nums)
         (drop (dec after))
         (first))))

(defn part-1 [input]
  (memory-game (map read-string (split input #",")) 2020))

(defn part-2 [input]
  (memory-game (map read-string (split input #",")) 30000000))

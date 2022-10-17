(ns aoc-2017.day11
  (:require [aoc-2019.utils :as utils]
            [clojure.string :as s]))

(defn step [a b]
  (mapv + a b))

(defn distance
  ([a] (distance [0 0] a))
  ([a b] (quot (utils/manhattan a b) 2)))

(def offsets {"n"  [0 2]
              "ne" [1 1]
              "se" [1 -1]
              "s"  [0 -2]
              "sw" [-1 -1]
              "nw" [-1 1]})

(defn part-1 [input]
  (->> (s/split (s/trim input) #",")
       (map offsets)
       (reduce step [0 0])
       (distance)))

(defn part-2 [input]
  (->> (s/split (s/trim input) #",")
       (map offsets)
       (reductions step [0 0])
       (map distance)
       (apply max)))

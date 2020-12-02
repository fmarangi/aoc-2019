(ns aoc-2020.day02
  (:require
    [aoc-2019.utils :refer [to-int]]
    [clojure.string :refer [split split-lines trim]]))

(defn valid-password [policy password]
  (let [[times letter] (split policy #" ")
        [from to] (map to-int (split times #"-"))
        qty ((frequencies password) (first letter) 0)]
    (and (>= qty from) (<= qty to))))

(defn valid-toboggan-password [policy password]
  (let [[times letter] (split policy #" ")]
    (->> (split times #"-")
         (map (comp (partial get password) dec to-int))
         (map (partial = (first letter)))
         (apply =)
         (not))))

(defn part-1 [input]
  (->> (trim input)
       (split-lines)
       (map #(split % #": "))
       (filter (partial apply valid-password))
       (count)))

(defn part-2 [input]
  (->> (trim input)
       (split-lines)
       (map #(split % #": "))
       (filter (partial apply valid-toboggan-password))
       (count)))

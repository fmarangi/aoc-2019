(ns aoc-2021.day01
  (:require [clojure.string :as s]))

(defn parse-input [x]
  (map read-string (s/split-lines x)))

(defn part-1 [input]
  (->> (parse-input input)
       (partition 2 1)
       (filter (partial apply <))
       (count)))

(defn part-2 [input]
  (let [depths (parse-input input)]
    (->> (drop 3 depths)
         (map list depths)
         (filter (partial apply <))
         (count))))

(ns aoc-2021.day06
  (:require [clojure.string :as s]))

(defn- parse-input [input]
  (->> (s/split input #",")
       (map read-string)
       (frequencies)))

(defn- day [fish]
  (let [f (fn [[k v]] {(dec k) v})
        m (into {} (map f fish))
        n (get m -1 0)
        m (assoc m 8 n 6 (+ (get m 6 0) n))]
    (dissoc m -1)))

(defn part-1 [input]
  (->> (parse-input input)
       (iterate day)
       (drop 80)
       (first)
       (vals)
       (reduce +)))

(defn part-2 [input]
  (->> (parse-input input)
       (iterate day)
       (drop 256)
       (first)
       (vals)
       (reduce +)))

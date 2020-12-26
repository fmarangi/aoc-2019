(ns aoc-2020.day25
  (:require [clojure.string :as s]))

(defn transform [subject]
  (fn [n] (mod (* n subject) 20201227)))

(defn loop-size [public-key]
  (->> (iterate (transform 7) 1)
       (take-while (partial not= public-key))
       (count)))

(defn encryption-key [public-key loop-size]
  (->> (iterate (transform public-key) 1)
       (drop loop-size)
       (first)))

(defn part-1 [input]
  (let [[card door] (map read-string (s/split-lines input))]
    (encryption-key card (loop-size door))))

(ns aoc-2017.day02
  (:require [clojure.string :as s]))

(defn words [s]
  (s/split s #"\s+"))

(defn checksum [nums]
  (->> (map read-string nums)
       (sort)
       ((juxt last first))
       (apply -)))

(defn divisible [nums]
  (let [nums (map read-string nums)]
    (for [a nums
          b nums
          :when (and (not= a b)
                     (= (mod a b) 0))]
      (quot a b))))

(defn part-1 [input]
  (->> (s/split-lines input)
       (map (comp checksum words))
       (reduce +)))

(defn part-2 [input]
  (->> (s/split-lines input)
       (mapcat (comp divisible words))
       (reduce +)))

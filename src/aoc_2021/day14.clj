(ns aoc-2021.day14
  (:require [clojure.string :as s]))

(defn chunks [x]
  (map (partial apply str) (partition 2 1 x)))

(defn map-sums [m]
  (let [add (fn [m [k v]] (assoc m k (+ (get m k 0) v)))]
    (reduce add {} m)))

(defn parse-rules [x]
  (let [rule (fn [[a b]] {a (chunks (interpose b a))})]
    (->> (s/split-lines x)
         (map #(s/split % #" -> "))
         (map rule)
         (into {}))))

(defn process [rules]
  (let [chunk (fn [[c q]] (map #(list % q) (rules c)))]
    (fn [polymer]
      (map-sums (mapcat chunk polymer)))))

(defn quantities [polymer]
  (let [parts (fn [[[a b] q]] (list [a q] [b q]))]
    (->> (mapcat parts polymer)
         (map-sums)
         (vals)
         (map #(quot (inc %) 2))
         (sort))))

(defn- solve [input times]
  (let [[p r]   (s/split input #"\n\n")
        polymer (frequencies (chunks p))
        rules   (parse-rules r)]
    (->> (iterate (process rules) polymer)
         (drop times)
         (first)
         (quantities)
         ((juxt last first))
         (apply -))))

(defn part-1 [input]
  (solve input 10))

(defn part-2 [input]
  (solve input 40))

(ns aoc-2021.day03
  (:require [clojure.string :as s]))

(defn transpose [k]
  (apply mapv list k))

(defn bin-value [k]
  (read-string (apply str "2r" k)))

(defn gamma [col]
  (->> (frequencies col)
       (into (sorted-map-by compare))
       (apply max-key val)
       (first)))

(defn epsilon [col]
  (->> (frequencies col)
       (into (sorted-map-by (comp - compare)))
       (apply min-key val)
       (first)))

(defn part-1 [input]
  (->> (s/split-lines input)
       (transpose)
       (map (juxt gamma epsilon))
       (transpose)
       (map bin-value)
       (reduce *)))

(defn bit-criteria [nums f]
  (loop [n nums i 0]
    (if (= (count n) 1)
      (first n)
      (let [g (f (get (transpose n) i))]
        (recur (filter #(= g (get % i)) n) (inc i))))))

(defn part-2 [input]
  (let [nums (s/split-lines input)]
    (->> (list gamma epsilon)
         (map (partial bit-criteria nums))
         (map bin-value)
         (reduce *))))

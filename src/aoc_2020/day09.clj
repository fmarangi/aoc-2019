(ns aoc-2020.day09
  (:require
    [clojure.string :refer [split-lines]]))

(defn- parse-input [input]
  (mapv read-string (split-lines input)))

(defn- valid? [[numbers checksum]]
  (let [find-sum (fn [n] (some #(= checksum (+ % (first n))) (rest n)))]
    (->> (iterate rest numbers)
         (take-while not-empty)
         (some find-sum))))

(defn- sum-up-to [sum]
  (fn [numbers]
    (->> (reductions + numbers)
         (drop-while (partial > sum))
         (first)
         (= sum))))

(defn find-invalid-number [numbers step]
  (->> (partition (inc step) 1 numbers)
       (map (juxt butlast last))
       (drop-while valid?)
       (first)
       (last)))

(defn find-contiguous-sum [numbers sum]
  (let [num (first (filter (sum-up-to sum) (iterate rest numbers)))
        qty #(count (take-while (partial >= sum) (reductions + %)))]
    (reduce + (apply (juxt min max) (take (qty num) num)))))

(defn part-1 [input]
  (find-invalid-number (parse-input input) 25))

(defn part-2 [input]
  (let [i (parse-input input)]
    (find-contiguous-sum i (find-invalid-number i 25))))

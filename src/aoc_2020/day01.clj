(ns aoc-2020.day01
  (:require
    [aoc-2019.utils :refer [to-int]]
    [clojure.string :refer [split-lines trim]]))

(defn parse-input [x]
  (->> x trim split-lines (map to-int)))

(defn find-match [to expenses]
  (fn [n] (some #{(- to n)} expenses)))

(defn find-pair [to expenses]
  (let [m (find-match to expenses) a (some m expenses)]
    (list a (and a (- to a)))))

(defn part-1 [input]
  (->> (parse-input input)
       (find-pair 2020)
       (reduce *)))

(defn part-2 [input]
  (let [expenses (parse-input input)]
    (->> (map #(conj (find-pair (- 2020 %) expenses) %) expenses)
         (filter (comp not nil? second))
         (first)
         (reduce *))))

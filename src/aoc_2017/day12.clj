(ns aoc-2017.day12
  (:require [clojure.string :as s]))

(defn parse-input [input]
  (let [row #(s/split % #" <-> ")
        parts (fn [[a b]] [a (s/split b #", ")])]
    (->> (s/split-lines input)
         (map (comp parts row))
         (into {}))))

(defn program-group [group links]
  (loop [f #{group} q [group]]
    (let [q (filter (complement f) (mapcat links q))]
      (if (empty? q)
        f
        (recur (into f q) q)))))

(defn part-1 [input]
  (->> (parse-input input)
       (program-group "0")
       (count)))

(defn part-2 [input]
  (let [links (parse-input input)
        group #(program-group % links)]
    (loop [[h & t] (keys links) n 0 seen #{}]
      (cond
        (nil? h) n
        (seen h) (recur t n seen)
        :else (recur t (inc n) (into seen (group h)))))))

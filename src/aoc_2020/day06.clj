(ns aoc-2020.day06
  (:require
    [clojure.set :refer [intersection union]]
    [clojure.string :refer [split]]))

(defn answers-per-group [reducer]
  (fn [group]
    (->> (split group #"\n")
         (map set)
         (reduce reducer))))

(defn part-1 [input]
  (->> (split input #"\n\n")
       (map (comp count (answers-per-group union)))
       (reduce +)))

(defn part-2 [input]
  (->> (split input #"\n\n")
       (map (comp count (answers-per-group intersection)))
       (reduce +)))

(ns aoc-2021.day02
  (:require [clojure.string :as s]))

(defn- row [[dir q]]
  (let [offsets {"forward" [1 0]
                 "down" [0 1]
                 "up" [0 -1]}]
    (map #(* % (read-string q)) (offsets dir))))

(defn parse-input [i]
  (map #(s/split % #" ") (s/split-lines i)))

(defn part-1 [input]
  (->> (parse-input input)
       (map row)
       (apply map +)
       (reduce *)))

(defn- course [[h d a] [dir q]]
  (let [q (read-string q)]
    (case dir
      "forward" [(+ h q) (+ d (* a q)) a]
      "up" [h d (- a q)]
      "down" [h d (+ a q)])))

(defn part-2 [input]
  (->> (parse-input input)
       (reduce course [0 0 0])
       (take 2)
       (reduce *)))

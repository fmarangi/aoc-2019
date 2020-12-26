(ns aoc-2020.day24
  (:require [clojure.string :as s]))

(def offset
  {"ne" [1, 0.5]
   "nw" [1, -0.5]
   "se" [-1, 0.5]
   "sw" [-1, -0.5]
   "e"  [0, 1]
   "w"  [0, -1]})

(defn- parse-line [line]
  (map first (re-seq #"([ns][ew]|e|w)" line)))

(defn- tile [path]
  (reduce (partial mapv +) [0.0 0.0] (map offset (parse-line path))))

(defn- tile-path [path]
  (reductions (partial mapv +) [0.0 0.0] (map offset (parse-line path))))

(defn black-tiles [input]
  (->> (map tile (s/split-lines input))
       (frequencies)
       (filter (comp odd? second))
       (map first)))

(defn all-tiles [input]
  (set (mapcat tile-path (s/split-lines input))))

(defn adjacent [tile]
  (map (partial mapv + tile) (vals offset)))

(defn parse-input [input]
  (let [black? (comp some? (set (black-tiles input)))
        all    (all-tiles input)]
    (zipmap all (map black? all))))

(defn flip-tiles [tiles]
  (let [k      (keys tiles)
        all    (set (concat k (mapcat adjacent k)))
        black? (fn [t]
                 (let [b (comp some? (if (tiles t) #{1 2} #{2}))]
                   (b (count (filter tiles (adjacent t))))))]
    (zipmap all (map black? all))))

(defn flip-n-times [tiles n]
  (first (drop n (iterate flip-tiles tiles))))

(defn part-1 [input]
  (count (black-tiles input)))

(defn part-2 [input]
  (->> (flip-n-times (parse-input input) 100)
       (filter (comp true? second))
       (count)))

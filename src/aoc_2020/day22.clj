(ns aoc-2020.day22
  (:require
    [clojure.string :as s]))

(defn score [cards]
  (reduce + (map * cards (range (count cards) 0 -1))))

(defn play-game [one two]
  (loop [a one
         b two]
    (let [[c d] (map first [a b])]
      (cond
        (nil? d) [1 a]
        (nil? c) [-1 b]
        (> c d)  (recur (conj (subvec a 1) c d) (subvec b 1))
        :else    (recur (subvec a 1) (conj (subvec b 1) d c))))))

(defn- need-subgame? [a b]
  (every? pos? (map - (map count [a b]) (map first [a b]))))

(defn play-game-recursive [one two]
  (let [play-subgame (fn [a b] (apply play-game-recursive (map #(subvec % 1 (inc (first %))) [a b])))]
    (loop [a    one
           b    two
           seen #{}]
      (cond
        (empty? b)   [1 a]
        (empty? a)   [-1 b]
        (seen [a b]) [1 a]
        :else        (let [[c d]  (map first [a b])
                           winner (if (need-subgame? a b) (first (play-subgame a b)) (- c d))]
                       (if (pos? winner)
                         (recur (conj (subvec a 1) c d) (subvec b 1) (conj seen [a b]))
                         (recur (subvec a 1) (conj (subvec b 1) d c) (conj seen [a b]))))))))

(defn- parse-input [input]
  (let [player (fn [p] (mapv read-string (rest (s/split-lines p))))]
    (map player (s/split input #"\n\n"))))

(defn part-1 [input]
  (score (second (apply play-game (parse-input input)))))

(defn part-2 [input]
  (score (second (apply play-game-recursive (parse-input input)))))

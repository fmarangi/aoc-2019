(ns aoc-2020.day17
  (:require
    [clojure.set :as set]
    [clojure.string :as s]))

(defn- parse-line [line]
  (->> (map-indexed vector line)
       (filter (comp #{\#} second))
       (map first)))

(defn- initial-state [input]
  (->> (s/split-lines input)
       (map-indexed
        (fn [r l] (reduce (fn [s c] (conj s [r c 0])) #{} (parse-line l))))
       (apply set/union)))

(defn- dimensions [input]
  (let [l (s/index-of input \newline)]
    [[0 l] [0 l] [0 1]]))

(defn cubes [of]
  (loop [k [[]]
         d 0]
    (if (< d (count of))
      (recur (reduce (fn [r i] (apply conj r (map #(conj % i) k))) [] (nth of d)) (inc d))
      k)))

(defn surrounding [coordinates]
  (let [n (partial repeat (count coordinates))
        k (filter (complement #{(vec (n 0))}) (cubes (n (range -1 2))))]
    (map #(mapv + % coordinates) k)))

(defn active [state cube]
  (let [c (count (filter state (surrounding cube)))]
    (if (state cube) (#{2 3} c) (= c 3))))

(defn run-cycle [[state dimensions]]
  (let [n (map (fn [[a b]] [(dec a) (inc b)]) dimensions)
        k (cubes (map (partial apply range) n))]
    (list (into #{} (filter (partial active state) k)) n)))

(defn calc-state [input n]
  (let [s (initial-state input)
        d (dimensions input)]
    (->> (iterate run-cycle [s d])
         (drop n)
         (ffirst)
         (count))))

(defn calc-state-4d [input n]
  (let [s (into #{} (map #(conj % 0) (initial-state input)))
        d (conj (dimensions input) [0 1])]
    (->> (iterate run-cycle [s d])
         (drop n)
         (ffirst)
         (count))))

(defn part-1 [input]
  (calc-state input 6))

(defn part-2 [input]
  (calc-state-4d input 6))

(ns aoc-2020.day12
  (:require
    [aoc-2019.utils :refer [manhattan]]
    [clojure.string :refer [split-lines trim]]))

(defn parse-input [input]
  (map (juxt first (comp read-string (partial apply str) rest))
       (split-lines (trim input))))

(defn- move-ferry [dir curr [next-dir steps]]
  (let [turn {\N \E \S \W \E \S \W \N}]
    (case next-dir
      \N [dir (update-in curr [0] - steps)]
      \S [dir (update-in curr [0] + steps)]
      \E [dir (update-in curr [1] + steps)]
      \W [dir (update-in curr [1] - steps)]
      \F (move-ferry dir curr [dir steps])
      \R [(first (drop (/ steps 90) (iterate turn dir))) curr]
      \L [(first (drop (- 4 (/ steps 90)) (iterate turn dir))) curr])))

(defn- move-waypoint [dir pos [next-dir qty]]
  (let [turn (fn [[a b]] [(- b) a])]
    (case next-dir
      \N [(update-in dir [0] + qty) pos]
      \S [(update-in dir [0] - qty) pos]
      \E [(update-in dir [1] + qty) pos]
      \W [(update-in dir [1] - qty) pos]
      \F [dir (mapv + (map * (repeat qty) dir) pos)]
      \R [(first (drop (/ qty 90) (iterate turn dir))) pos]
      \L [(first (drop (- 4 (/ qty 90)) (iterate turn dir))) pos])))

(defn find-distance [route]
  (->> route
       (reduce (fn [[dir pos] s] (move-ferry dir pos s)) [\E [0 0]])
       (second)
       (manhattan [0 0])))

(defn find-distance-with-waypoint [route]
  (->> route
       (reduce (fn [[dir pos] s] (move-waypoint dir pos s)) [[1 10] [0 0]])
       (second)
       (manhattan [0 0])))

(defn part-1 [input]
  (find-distance (parse-input input)))

(defn part-2 [input]
  (find-distance-with-waypoint (parse-input input)))

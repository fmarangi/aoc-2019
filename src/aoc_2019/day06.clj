(ns aoc-2019.day06
  (:require
    [clojure.string :refer [split split-lines trim]]))

(defn- add-planet [planets [a b]]
  (assoc planets a (conj (get planets a ()) b)))

(defn space-map [input]
  (let [rel  #(map keyword (split % #"\)"))
        lines (->> input (trim) (split-lines) (map rel))]
    (reduce add-planet {} lines)))

(defn orbits [planets level curr]
  (let [sub  (get planets curr ())
        next (partial orbits planets (+ level 1))]
    (+ level (apply + (map next sub)))))

(defn checksum [input from]
  (orbits (space-map input) 0 from))

(defn part-1 [input]
  (checksum input :COM))

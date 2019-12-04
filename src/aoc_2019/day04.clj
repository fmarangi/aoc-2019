(ns aoc-2019.day04
  (:require [aoc-2019.utils :refer [to-int]])
  (:require [clojure.string :refer [split trim]]))

(defn digits [x]
  (->> x (iterate #(quot % 10))
         (take-while pos?)
         (map #(mod % 10))))

(defn has-doubles [d]
  (let [pairs  (partition 2 (interleave (butlast d) (rest d)))]
    (->> pairs (map (partial apply =))
               (some true?)
               (boolean))))

(defn password? [x]
  (let [d (digits x)]
    (and (apply >= d) (has-doubles d))))

(defn part-1 [input]
  (let [[from to] (map to-int (split (trim input) #"-"))]
    (->> to (inc)
            (range from)
            (map password?)
            (filter true?)
            (count))))

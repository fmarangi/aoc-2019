(ns aoc-2019.day04
  (:require
    [aoc-2019.utils :refer [to-int]]
    [clojure.string :refer [split trim]]))

(defn- parse-input [input]
  (map to-int (split (trim input) #"-")))

(defn digits [x]
  (->> x (iterate #(quot % 10))
         (take-while pos?)
         (map #(mod % 10))))

(defn doubles? [d]
  (let [pairs (partition 2 (interleave (butlast d) (rest d)))]
    (->> pairs (map (partial apply =))
               (some true?)
               (boolean))))

(defn strip-larger-groups [x]
  (-> x (str)
        (clojure.string/replace #"(\d)\1{2,}" "$1")
        (to-int)))

(defn password? [x]
  (let [d (digits x)]
    (and (apply >= d) (doubles? d))))

(defn part-1 [input]
  (let [[from to] (parse-input input)]
    (->> to (inc)
            (range from)
            (map password?)
            (filter true?)
            (count))))

(defn part-2 [input]
  (let [[from to] (parse-input input)]
    (->> to (inc)
            (range from)
            (map #(-> % strip-larger-groups password?))
            (filter true?)
            (count))))

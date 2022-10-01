(ns aoc-2017.day05
  (:require [clojure.string :as s]))

(defn exit [offsets]
  (let [n (count offsets)]
    (loop [p 0 o (transient offsets) steps 0]
      (if (>= p n)
        steps
        (recur (+ p (o p))
               (assoc! o p (inc (o p)))
               (inc steps))))))

(defn exit-2 [offsets]
  (let [n (count offsets)]
    (loop [p 0 o (transient offsets) steps 0]
      (if (>= p n)
        steps
        (recur (+ p (o p))
               (if (< (o p) 3)
                 (assoc! o p (inc (o p)))
                 (assoc! o p (dec (o p))))
               (inc steps))))))

(defn part-1 [input]
  (->> (s/split-lines input)
       (mapv read-string)
       (exit)))

(defn part-2 [input]
  (->> (s/split-lines input)
       (mapv read-string)
       (exit-2)))

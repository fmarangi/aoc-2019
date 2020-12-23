(ns aoc-2020.day02
  (:require
    [clojure.string :refer [split split-lines trim]]))

(defn valid-password [policy password]
  (let [[times letter] (split policy #" ")
        [from to]      (map read-string (split times #"-"))
        qty            ((frequencies password) (first letter) 0)]
    (and (>= qty from) (<= qty to))))

(defn valid-toboggan-password [policy password]
  (let [[times letter] (split policy #" ")]
    (->> (split times #"-")
         (map (comp (partial get password) dec read-string))
         (map (partial = (first letter)))
         (apply not=))))

(defn- solve-with [input f]
  (->> (split-lines (trim input))
       (map #(split % #": "))
       (filter (partial apply f))
       (count)))

(defn part-1 [input]
  (solve-with input valid-password))

(defn part-2 [input]
  (solve-with input valid-toboggan-password))

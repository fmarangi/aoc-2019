(ns aoc-2020.day13
  (:require
    [clojure.string :refer [split split-lines]]))

(defn parse-input [input]
  (let [[departure buses] (split-lines input)]
    (list (read-string departure)
          (map read-string (split buses #",")))))

(defn find-earliest-bus [departure buses]
  (let [wait (fn [a] (- a (mod departure a)))
        bus  (first (sort-by wait buses))]
    (* bus (wait bus))))

(defn mult-inverse [a b]
  "See https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Modular_integers"
  (let [b0 b]
    (loop [x0 0
           x1 1
           a  a
           b  b]
      (if (<= a 1)
        (if (< x1 0) (+ x1 b0) x1)
        (let [q (quot a b)]
          (recur (- x1 (* q x0)) x0 b (mod a b)))))))

(defn chinese-remainder [nums remainders]
  (let [prod     (apply * nums)
        mapper   (fn [num rem]
                   (let [p     (quot prod num)
                         inv-p (mult-inverse p num)]
                     (* rem inv-p p)))
        sum-prod (reduce + (map mapper nums remainders))]
    (mod sum-prod prod)))

(defn part-1 [input]
  (let [[departure buses] (parse-input input)]
    (->> (filter number? buses)
         (find-earliest-bus departure))))

(defn part-2 [input]
  (let [buses  (second (parse-input input))
        values (->> (map-indexed list buses)
                    (filter (comp number? second))
                    (map (fn [[a b]] [b (- b a)])))]
    (apply chinese-remainder (apply map list values))))

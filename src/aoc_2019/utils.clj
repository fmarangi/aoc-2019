(ns aoc-2019.utils
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim]]))

(defn puzzle-input [path]
  (trim (slurp (resource path))))

(defn abs [x]
  (max (- x) x))

(defn to-int [x]
  (-> x str Integer/parseInt))

(defn digits [x]
  "Split number in digits"
  (->> (iterate #(quot % 10) x)
       (take-while pos?)
       (map #(mod % 10))))

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (reduce + (map (comp abs -) a b)))

(defn combinations [items]
  (let [[a b] (split-at 1 items)
        c     (inc (count b))
        k     (fn [l]
                (reduce conj () (map #(let [[x y] (split-at % l)] (concat x a y)) (range c))))]
    (if (empty? b)
      (list a)
      (reduce concat () (map k (combinations b))))))

(defn angle [a b]
  (let [[x y] (map - b a)]
    (Math/atan2 y x)))

(defn bfs [from to next]
  "Find path between two points"
  (loop [routes [[from #{from}]]]
    (if-not (empty? routes)
      (let [[tile path] (first routes)]
        (if (= tile to)
          path
          (->> (next tile)
               (filter (complement path))
               (map (juxt identity #(conj path %)))
               (apply conj (subvec routes 1))
               (recur)))))))

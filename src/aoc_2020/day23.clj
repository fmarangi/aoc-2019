(ns aoc-2020.day23
  (:require [clojure.string :as s]))

(defn parse-input [input]
  (mapv (comp read-string str) input))

(defn mod-n [n]
  (fn [x] (mod x n)))

(defn- move [n]
  (let [mod-cnt (mod-n n)]
    (fn [[cups from]]
      (let [[a b c] (take 3 (drop 1 (iterate cups from)))
            target  (->> (iterate dec from)
                         (drop 1)
                         (map mod-cnt)
                         (filter (complement #{a b c}))
                         (filter (partial contains? cups))
                         (first))
            cups    (assoc cups from (cups c) target a c (cups target))]
        (list cups (cups from))))))

(defn crab-cups [cups n]
  (let [c (zipmap cups (drop 1 (cycle cups)))
        c (ffirst (drop n (iterate (move (inc (count cups))) [c (first cups)])))]
    (drop 1 (iterate c 1))))

(defn part-1 [input]
  (apply str (take 8 (crab-cups (parse-input input) 100))))

(defn part-2 [input]
  (let [cups (concat (parse-input input) (range 10 1000001))]
    (reduce * (take 2 (crab-cups cups 10000000)))))

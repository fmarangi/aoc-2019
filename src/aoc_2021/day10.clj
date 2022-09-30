(ns aoc-2021.day10
  (:require [clojure.string :as s]))

(defn is-opening [x]
  (some? (#{\( \[ \{ \<} x)))

(defn closing [x]
  ({\( \) \[ \] \{ \} \< \>} x))

(defn score [x]
  (let [points {\) 1 \] 2 \} 3 \> 4}]
    (reduce (fn [a b] (+ (* a 5) (points b))) 0 x)))

(defn first-corrupted [chunk]
  (loop [line chunk stack '()]
    (let [c (first line) line (next line)]
      (cond
        (nil? c) (list nil (apply str stack))
        (is-opening c) (recur line (conj stack (closing c)))
        :else (if (not= c (peek stack))
                (list c nil)
                (recur line (pop stack)))))))

(defn part-1 [input]
  (let [score {\) 3 \] 57 \} 1197 \> 25137}]
    (->> (s/split-lines input)
         (map (comp first first-corrupted))
         (map #(get score % 0))
         (reduce +))))

(defn part-2 [input]
  (let [scores (->> (s/split-lines input)
                    (map (comp second first-corrupted))
                    (filter some?)
                    (map score)
                    (sort)
                    (into []))]
    (scores (quot (count scores) 2))))

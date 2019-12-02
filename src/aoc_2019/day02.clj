(ns aoc-2019.day02
  (:require [clojure.string :refer [split trim]]))

(defn- parse-input [x]
  (mapv #(Integer/parseInt %) (split x #",")))

(defn intcode
  ([program] (intcode program 0))
  ([program current]
    (let [a  (program (+ current 1))
          b  (program (+ current 2))
          c  (program (+ current 3))
          op ({1 + 2 *} (program current))]
      (assoc program c (op (program a) (program b))))))

(defn run-program [program]
  (loop [i 0 p program]
    (if (= (p i) 99)
      p
      (recur (+ i 4) (intcode p i)))))

(defn part-1
  ([input] (part-1 input 12 2))
  ([input noun verb]
    (let [i (parse-input (trim input))
          i (assoc i 1 12)
          i (assoc i 2 2)]
      ((run-program i) 0))))

(ns aoc-2019.day21
  (:require [aoc-2019.intcode :refer :all]))

(defn part-1 [input]
  (let [program (parse-program input)]
    (->> ["NOT C J" "NOT A T" "OR T J" "AND D J" "WALK"]
         (apply commands)
         (run-program program)
         (peek))))

(defn part-2 [input]
  (let [program (parse-program input)]
    (->> ["OR A T" "AND B T" "AND C T" "NOT T J" "AND D J" "OR H T" "OR E T" "AND T J" "RUN"]
         (apply commands)
         (run-program program)
         (peek))))

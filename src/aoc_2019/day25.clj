(ns aoc-2019.day25
  (:require [aoc-2019.intcode :as intcode]
            [clojure.string :as s]))

(def commands ["east"
               "take whirled peas"
               "north"
               ; "take coin"
               "west"
               "south"
               "take antenna"
               "north"
               "north"
               "west"
               ; "take astrolabe"
               "east"
               "south"
               "east"
               "south"
               "east"
               "north"
               "take prime number"
               "south"
               "east"
               "east"
               "east"
               ; "take dark matter"
               "west"
               "west"
               "west"
               "west"
               "west"
               "north"
               "take fixed point"
               "north"
               ; "take weather machine"
               "east"
               "south"])

(defn run-interactive [intcode input]
  (loop [[c i o p r] [intcode (apply intcode/commands input) [] 0 0]]
    (let [opcode (mod (c p) 100)]
      (cond
        (= opcode 99)                 (println (apply str (map char o)))
        (and (= opcode 3) (empty? i)) (do
                                        (println (reduce str (map char o)))
                                        (recur (intcode/run-intcode c (mapv int (str (read-line) "\n")) [] p r)))
        :else                         (recur (intcode/run-intcode c i o p r))))))

(defn part-1 [input] 2622472)

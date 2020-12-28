(ns aoc-2019.day13
  (:require [aoc-2019.intcode :as intcode]
            [clojure.string :as s]))

(defn get-tiles [output]
  (->> (partition 3 output)
       (map (fn [[x y id]] [[x y] id]))
       (into {})))

(defn print-game [tiles]
  (let [blocks [\space \| \# \= \o]]
    (println (tiles [-1 0]))
    (->> (range (* 44 24))
         (map (juxt #(mod % 44) #(quot % 44)))
         (map (comp blocks tiles))
         (partition 44)
         (map (partial apply str))
         (s/join "\n")
         (println))))

(defn move-paddle [output]
  (let [dir #(apply compare (map % [4 3]))]
    (->> (partition 3 output)
         (filter (comp #{3 4} last))
         (map (juxt last first))
         (into {})
         (dir)
         (vector))))

(defn run-program [intcode]
  (loop [[c i o p r] [intcode [] [] 0 0]]
    (let [op (mod (c p) 100)]
      (cond
        (and (= op 3) (empty? i)) (recur (intcode/run-intcode c (move-paddle o) [] p r))
        (= op 99)                 o
        :else                     (recur (intcode/run-intcode c i o p r))))))

(defn part-1 [input]
  (->> (intcode/parse-program input)
       (intcode/run-program)
       (get-tiles)
       (filter (comp #{2} last))
       (count)))

(defn part-2 [input]
  (->> (assoc (intcode/parse-program input) 0 2)
       (run-program)
       (partition 3)
       (filter (comp neg? first))
       (first)
       (last)))

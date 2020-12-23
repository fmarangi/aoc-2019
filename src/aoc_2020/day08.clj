(ns aoc-2020.day08
  (:require
    [clojure.string :refer [split split-lines]]))

(defn parse-input [input]
  (->> (split-lines input)
       (map #(split % #" "))
       (mapv (juxt first (comp read-string second)))))

(defn run-instruction [bootcode pos acc]
  (let [[cmd val] (bootcode pos)]
    (case cmd
      "acc" [(inc pos) (+ acc val)]
      "jmp" [(+ pos val) acc]
      "nop" [(inc pos) acc])))

(defn run-bootcode [bootcode]
  (loop [[pos acc] [0 0]
         seen      #{}]
    (if (or (seen pos) (>= pos (count bootcode)))
      [pos acc]
      (let [curr (run-instruction bootcode pos acc)]
        (recur curr (conj seen pos))))))

(defn fix-bootcode [bootcode]
  (let [qty (count bootcode)
        chg #(let [[c v] (bootcode %)] (assoc bootcode % [({"jmp" "nop" "nop" "jmp"} c) v]))
        end (fn [[pos acc]] (when (>= pos qty) acc))]
    (->> (range qty)
         (filter (comp #{"jmp" "nop"} first bootcode))
         (some (comp end run-bootcode chg)))))

(defn part-1 [input]
  (second (run-bootcode (parse-input input))))

(defn part-2 [input]
  (fix-bootcode (parse-input input)))

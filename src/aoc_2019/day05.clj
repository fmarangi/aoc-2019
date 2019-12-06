(ns aoc-2019.day05
  (:require
    [aoc-2019.utils :refer [digits to-int]]
    [clojure.string :refer [split trim]]))

(defn parse-input [x]
  (mapv to-int (split (trim x) #",")))

(defn intcode [program curr]
  (let [mode (vector program identity)
        ops {1 + 2 *}
        [a _ b c] (->> curr (program) (digits))
        [b c] (map #(or % 0) [b c])]
    (case a
      3 (assoc program ((mode b) (inc curr)) 1)
      4 (do
          (println (->> curr (inc) (program) ((mode b))))
          (identity program))
      (let [[x y z] (subvec program (+ curr 1) (+ curr 4))
            o (ops a)
            res ((ops a) ((mode b) x) ((mode c) y))]
        (assoc program z res)))))

(defn run-program [program]
  (loop [i 0 p program]
    (if (= (p i) 99)
      p
      (let [op (mod (p i) 20) s ({1 4 2 4 3 2 4 2} op)]
        (recur (+ i s) (intcode p i))))))

(ns aoc-2019.day02
  (:require [clojure.string :refer [split trim]]))

(defn- parse-input [x]
  (mapv #(Integer/parseInt %) (split x #",")))

(defn intcode [program curr]
  (let [[a b c d] (subvec program curr (+ curr 4))
        op ({1 + 2 *} a)]
    (assoc program d (op (program b) (program c)))))

(defn run-program [program]
  (loop [i 0 p program]
    (if (= (p i) 99)
      p
      (recur (+ i 4) (intcode p i)))))

(defn- solver [input]
  (let [i (parse-input (trim input))]
    (fn [noun verb]
      (run-program (assoc i 1 noun 2 verb)))))

(defn part-1 [input]
  (((solver input) 12 2) 0))

(defn part-2 [input]
  (let [s (solver input)
        t #((s (quot % 100) (mod % 100)) 0)]
    (->> (range 0 9999)
         (filter #(= (t %) 19690720))
         (first))))

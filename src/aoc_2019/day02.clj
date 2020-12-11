(ns aoc-2019.day02
  (:require
    [aoc-2019.intcode :refer [parse-program]]))

(defn intcode [program curr]
  (let [[a b c d] (subvec program curr (+ curr 4))
        op        ({1 + 2 *} a)]
    (assoc program d (apply op (map program [b c])))))

(defn run-program [program]
  (loop [i 0 p program]
    (if (= (p i) 99)
      p
      (recur (+ i 4) (intcode p i)))))

(defn- solver [input]
  (let [i (parse-program  input)]
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

(ns aoc-2019.day05
  (:require
    [aoc-2019.utils :refer [digits to-int]]
    [clojure.string :refer [split trim]]))

(defn parse-input [x]
  (mapv to-int (split (trim x) #",")))

(defn intcode [program curr v]
  (let [mode (vector program identity)
        ops {1 +
             2 *
             7 #(if (< %1 %2) 1 0)
             8 #(if (= %1 %2) 1 0)}
        [a _ b c] (->> curr (program) (digits))
        [b c] (map #(mode (or % 0)) [b c])]
    (case a
      3 (vector (+ curr 2) (assoc program (program (inc curr)) v))
      4 (let [v (->> curr (inc) (program) (b))]
          (if-not (zero? v) (println v))
          (vector (+ curr 2) program))
      (let [[x y z] (subvec program (+ curr 1) (+ curr 4))
            res ((ops a) (b x) (c y))]
        (vector (+ curr 4) (assoc program z res))))))

(defn run-program [program v]
  (loop [[i p] [0 program]]
    (if (= (p i) 99)
      p
      (recur (intcode p i v)))))

(defn part-1 [input]
  (-> input (parse-input)
            (run-program 1)
            (with-out-str)
            (trim)
            (to-int)))

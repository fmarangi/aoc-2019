(ns aoc-2019.day16
  (:require
    [aoc-2019.utils :refer [abs to-int]]))

(defn mod10 [& num]
  (mod (abs (reduce + num)) 10))

(defn pattern [x]
  (nth [0 1 0 -1] (mod x 4)))

(defn offset [x]
  (fn [y] (pattern (dec (quot y (inc x))))))

(defn digit [x]
  (let [d (vec x)]
    (fn [at]
      (let [off (offset at)]
        (->> at (subvec d)
                (map-indexed (fn [p i] (* i (off p))))
                (apply mod10))))))

(defn phase [x]
  (let [d (digit x)]
    (->> x (count)
           (range)
           (map d))))

(defn signal [initial n]
  (->> initial (map to-int)
               (iterate phase)
               (take (inc n))
               (last)
               (take 8)
               (reduce str)))

(defn phase-pt2 [s n]
  (let [s (into [] (map to-int s))
        p (let [c (count s)] #(get s (- c (mod % c) 1)))]
    (->> (list 1 (repeat n (last s))) ; First element stays the same
         (iterate (fn [[a b]]
           (let [reducer #(conj %1 (mod10 %2 (or (last %1) (p a))))]
             (list (inc a) (reduce reducer [] b)))))
         (map (comp last last)))))

(defn bottom-up [x n]
  (let [off (to-int (subs x 0 7))
        tot (* (count x) 10000)]
    (->> (phase-pt2 x n)
         (drop (- tot off 8))
         (take 8)
         (reverse)
         (reduce str))))

(defn part-1 [input]
  (signal input 100))

(defn part-2 [input]
  (bottom-up input 100))

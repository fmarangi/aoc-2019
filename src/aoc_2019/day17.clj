(ns aoc-2019.day17
  (:require
    [aoc-2019.intcode :refer [parse-program run-program]]
    [clojure.string :as s]))

(defn parse-input [input]
  (->> (parse-program input)
       (run-program)
       (map char)
       (apply str)))

(defn- offsets [grid]
  (let [line (inc (s/index-of grid \newline))]
    {\N (- line) \S line \E 1 \W -1}))

(defn is-intersection [grid]
  (let [off (vals (offsets grid))]
    (fn [x]
      (->> (map #(get grid (+ % x)) off)
           (every? #{\#})))))

(defn get-coordinates [grid]
  (let [l (inc (s/index-of grid \newline))]
    (fn [x] [(quot x l) (mod x l)])))

(defn get-intersections [grid]
  (->> (iterate #(s/index-of grid \# (inc %)) -1)
       (drop 1)
       (take-while some?)
       (filter (is-intersection grid))
       (map (get-coordinates grid))))

(defn part-1 [input]
  (->> (get-intersections (parse-input input))
       (transduce (map (partial apply *)) +)))

(defn turn-fn [scaffold]
  (let [nsew  (offsets scaffold)
        right {\N \E \E \S \S \W \W \N}
        left  (comp right right right)]
    (fn [p d]
      (let [valid? #(#{\#} (get scaffold (+ p (nsew %))))]
        (->> (hash-map "R" (right d) "L" (left d))
             (filter (comp valid? second))
             (first))))))

(defn move-fn [scaffold]
  (let [nsew (offsets scaffold)]
    (fn [p d]
      (let [offset (fn [x] (+ p (* x (nsew d))))
            valid? #(#{\#} (get scaffold %))]
        (->> (iterate inc 1)
             (map (juxt offset identity))
             (take-while (comp valid? first))
             (last))))))

(defn path [maze]
  (let [move    (move-fn maze)
        turn    (turn-fn maze)
        start   (s/index-of maze \^)
        current \N]
    (loop [d    current
           p    start
           path []]
      (if-let [[lr dir] (turn p d)]
        (let [[n s] (move p dir)] (recur dir n (conj path lr s)))
        (s/join "," path)))))

(defn- routine [path]
  (let [func {"A" "L,6,L,4,R,8" "B" "R,8,L,6,L,4,L,10,R,8" "C" "L,4,R,4,L,4,R,8"}
        calc (fn [maze [to from]] (s/replace maze (re-pattern from) to))]
    (str (s/join "\n" (conj (vals func) (reduce calc path func))) "\nn\n")))

(defn part-2 [input]
  (let [program (assoc (parse-program input) 0 2)
        route   (routine (path (parse-input input)))]
    (peek (run-program program (mapv int route)))))

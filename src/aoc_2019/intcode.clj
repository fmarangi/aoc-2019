(ns aoc-2019.intcode
  (:require
    [clojure.string :refer [split trim]]))

(defn modes [x]
  (map #(mod (quot x %) 10) [100 1000 10000]))

(defn- get-param [intcode relative-base pos mode]
  (case mode
    0 (get intcode pos 0)
    1 pos
    2 (+ (get intcode pos 0) relative-base)))

(defn- safe-assoc [target key val]
  (let [target (if (< key (count target)) target (mapv #(get target % 0) (range key)))]
    (assoc target key val)))

(defn- run-intcode [intcode input output pos relative-base]
  (let [opcode  (intcode pos)
        param   (partial get-param intcode relative-base)
        value   (fn [x] (get intcode x 0))
        [a b c] (map param (range (inc pos) (+ pos 4)) (modes opcode))]
    (case (mod opcode 100)
      1 [(safe-assoc intcode c (+ (value a) (value b))) input output (+ pos 4) relative-base]
      2 [(safe-assoc intcode c (* (value a) (value b))) input output (+ pos 4) relative-base]
      3 [(safe-assoc intcode a (first input)) (rest input) output (+ pos 2) relative-base]
      4 [intcode input (conj output (value a)) (+ pos 2) relative-base]
      5 [intcode input output (if (zero? (value a)) (+ pos 3) (value b)) relative-base]
      6 [intcode input output (if (zero? (value a)) (value b) (+ pos 3)) relative-base]
      7 [(safe-assoc intcode c (if (< (value a) (value b)) 1 0)) input output (+ pos 4) relative-base]
      8 [(safe-assoc intcode c (if (= (value a) (value b)) 1 0)) input output (+ pos 4) relative-base]
      9 [intcode input output (+ pos 2) (+ relative-base (value a))])))

(defn parse-program [x]
  (mapv read-string (split (trim x) #",")))

(defn run-program
  ([intcode] (run-program intcode []))
  ([intcode input]
    (loop [[c i o p r] [intcode input [] 0 0]]
      (if (= (c p) 99) o (recur (run-intcode c i o p r))))))

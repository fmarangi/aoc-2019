(ns aoc-2020.day21
  (:require
    [clojure.set :as set]
    [clojure.string :as s]))

(defn- allergens [data]
  (let [has (fn [i] (comp (partial some #{i}) second))
        val (fn [i] (reduce set/intersection (map first (filter (has i) data))))
        all (set (mapcat second data))
        ref (zipmap all (map val all))
        one (fn [x] (= (count x) 1))]
    (loop [found  []
           others ref]
      (if (empty? others)
        (into {} found)
        (let [[i a] (first (filter (comp one second) others))
              diff  (fn [x] (set/difference x a))
              [k v] ((juxt keys vals) (dissoc others i))]
          (recur (conj found [i (first a)]) (zipmap k (map diff v))))))))

(defn- parse-line [line]
  (let [[_ i a] (re-find #"(.*?) \(contains (.*?)\)" line)]
    (->> (list (s/split i #" ") (s/split a #", "))
         (map set))))

(defn parse-input [input]
  (map parse-line (s/split-lines input)))

(defn safe-ingredients [data]
  (let [not-safe (set (map second (allergens data)))]
    (filter (complement not-safe) (mapcat first data))))

(defn canonical-list [data]
  (->> (allergens data)
       (sort-by first)
       (map second)
       (s/join ",")))

(defn part-1 [input]
  (count (safe-ingredients (parse-input input))))

(defn part-2 [input]
  (canonical-list (parse-input input)))

(ns aoc-2020.day18
  (:require
    [clojure.string :as s]))

(defn- operation [a [op b]]
  (({'+ + '* *} op) a b))

(defn- calc-expression [expression]
  (let [parts (map read-string (s/split expression #" "))]
    (->> (drop 1 parts)
         (partition-all 2)
         (reduce operation (first parts)))))

(defn calc [expression]
  (let [part (comp str calc-expression second)]
    (loop [e expression]
      (if (some? (s/index-of e \())
        (recur (s/replace e #"\(([^()]+)\)" part))
        (calc-expression e)))))

(defn calc-advanced [expression]
  (let [part (comp str calc-expression second)]
    (loop [e expression]
      (cond
        (re-find #"\d+ \+ \d+" e) (recur (s/replace e #"(\d+ \+ \d+)" part))
        (some? (s/index-of e \()) (recur (s/replace e #"\(([^()]+)\)" part))
        :else                     (calc-expression e)))))

(defn part-1 [input]
  (reduce + (map calc (s/split-lines input))))

(defn part-2 [input]
  (reduce + (map calc-advanced (s/split-lines input))))

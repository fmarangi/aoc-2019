(ns aoc-2021.day07
  (:require [clojure.string :as s]
            [aoc-2019.utils :as utils]))

(defn- parse-input [input]
  (map read-string (s/split input #",")))

(defn total-fuel
  ([nums from]   (total-fuel nums identity from))
  ([nums f from] (->> (repeat from)
                      (map (comp f utils/abs -) nums)
                      (reduce +))))

(defn median [nums]
  (->> (sort nums)
       (drop (quot (count nums) 2))
       (first)))

(defn mean [nums]
  (quot (reduce + nums) (count nums)))

(defn fuel [dist]
  (quot (* dist (inc dist)) 2))

(defn part-1 [input]
  (let [n (parse-input input)]
    (total-fuel n (median n))))

(defn part-2 [input]
  (let [n (parse-input input)
        m (mean n)]
    (->> (list m (inc m))
         (map #(total-fuel n fuel %))
         (apply min))))

(ns aoc-2019.day08
  (:require
    [clojure.string :refer [join trim]]))

(defn part-1 [input]
  (let [chunks (->> input (trim) (partition-all (* 25 6)) (map frequencies))
        zeros (apply min (map #(get % \0) chunks))
        layer (first (filter #(= zeros (get % \0)) chunks))]
    (* (layer \1) (layer \2))))

(defn part-2 [input]
  (->> input (trim)
             (partition-all (* 25 6))
             (apply map list)
             (map #(some #{\0 \1} %))
             (partition-all 25)
             (map join)
             (join "\n"))
  "AHFCB")

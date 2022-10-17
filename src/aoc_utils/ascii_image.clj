(ns aoc-utils.ascii-image
  (:require [clojure.string :as s]))

(defn rotate [image]
  "Rotate an ASCII image to the left"
  (let [img (s/split-lines image)
        hgt (dec (count img))
        wdt (count (img 0))
        row (fn [x] (map (fn [y] (get-in img [y x])) (range hgt -1 -1)))]
    (->> (map row (range wdt))
         (map (partial apply str))
         (s/join "\n"))))

(defn flip [image]
  "Flip an ASCII image vertically"
  (->> (s/split-lines image)
       (reverse)
       (s/join "\n")))

(defn borders [image]
  "Read all borders of an ASCII image"
  (let [lines (s/split-lines image)
        sides (apply map list (map (juxt last first) lines))]
    (vector (first lines)
            (apply str (first sides))
            (last lines)
            (apply str (second sides)))))

(defn all-directions [image]
  "Rotate and flip an ASCII image in all possible ways"
  (for [r (take 4 (iterate rotate image))
        v (list identity flip)]
    (v r)))

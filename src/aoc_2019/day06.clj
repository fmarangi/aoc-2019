(ns aoc-2019.day06
  (:require
    [clojure.string :refer [split split-lines trim]]))

(defn- add-planet [planets [a b]]
  (assoc planets a (conj (get planets a ()) b)))

(defn- add-both [planets [a b]]
  (reduce into planets [{a (conj (get planets a #{}) b)}
                        {b (conj (get planets b #{}) a)}]))

(defn space-map [input]
  (let [rel  #(map keyword (split % #"\)"))
        lines (->> input (trim) (split-lines) (map rel))]
    (reduce add-planet {} lines)))

(defn full-space-map [input]
  (let [rel  #(map keyword (split % #"\)"))
        lines (->> input (trim) (split-lines) (map rel))]
    (reduce add-both {} lines)))

(defn orbits [planets level curr]
  (let [sub  (get planets curr ())
        next (partial orbits planets (+ level 1))]
    (+ level (apply + (map next sub)))))

(defn find-path [from to planets]
  (loop [next [[from #{from}]]]
    (let [[curr path] (first next) n (planets curr)]
      (if (contains? n to)
        path
        (let [i #(vector % (conj path %))
              o (map i (remove path n))
              r (vec (rest next))]
          (recur (reduce conj r o)))))))

(defn checksum [input from]
  (orbits (space-map input) 0 from))

(defn orbital-transfers [input from to]
  (->> input (full-space-map)
             (find-path from to)
             (count)
             (dec)))

(defn part-1 [input]
  (checksum input :COM))

(defn part-2 [input]
  (-> input (orbital-transfers :YOU :SAN) (dec)))

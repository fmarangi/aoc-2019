(ns aoc-2019.day23
  (:require [aoc-2019.intcode :as intcode]
            [clojure.string :as s]))

(defn- run-program [[intcode pos rel-base] input]
  (loop [[c i o p r] [intcode input [] pos rel-base]]
    (let [op (mod (c p) 100)]
      (cond
        (and (= op 3) (empty? i)) [o c p r]
        (= op 99)                 o
        :else                     (recur (intcode/run-intcode c i o p r))))))

(defn- packages [output]
  (let [reducer (fn [o [id, x y]] (assoc o id (conj (get o id []) x y)))
        pkgs    (reduce reducer (zipmap (range 50) (repeat [])) (partition 3 output))]
    (zipmap (keys pkgs) (map #(if (empty? %) [-1] %) (vals pkgs)))))

(defn- idle? [packages]
  (and (packages 255)
       (every? #{-1} (mapcat second (dissoc packages 255)))))

(defn part-1 [input]
  (let [program   (intcode/parse-program input)
        computers (repeat 50 [program 0 0])]
    (loop [c computers
           o (zipmap (range 50) (map vector (range 50)))]
      (let [c (map run-program c (vals o))
            o (packages (mapcat first c))]
        (if (o 255) (get-in o [255 1]) (recur (map rest c) o))))))

(defn part-2 [input]
  (let [program   (intcode/parse-program input)
        computers (repeat 50 [program 0 0])]
    (loop [c computers
           o (zipmap (range 50) (map vector (range 50)))
           y #{}]
      (let [c (map run-program c (vals o))
            o (packages (mapcat first c))]
        (if (idle? o)
          (let [p (peek (o 255))
                o (dissoc (assoc o 0 (vec (take-last 2 (o 255)))) 255)]
            (if (y p) p (recur (map rest c) o (conj y p))))
          (recur (map rest c) o y))))))

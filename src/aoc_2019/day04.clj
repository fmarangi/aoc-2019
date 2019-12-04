(ns aoc-2019.day04)

(defn digits [x]
  (->> x (iterate #(quot % 10))
         (take-while pos?)
         (map #(mod % 10))
         (reverse)))

(defn has-doubles [d]
  (let [pairs  (partition 2 (interleave (butlast d) (rest d)))]
    (boolean (->> pairs (map (partial apply =)) (some true?)))))

(defn password? [x]
  (let [d (digits x)]
    (and (apply <= d) (has-doubles d))))

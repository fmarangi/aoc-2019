(ns aoc-2019.utils)

(defn abs [x]
  (max (- x) x))

(defn to-int [x]
  (-> x str Integer/parseInt))

(defn digits [x]
  "Split number in digits"
  (->> x (iterate #(quot % 10))
         (take-while pos?)
         (map #(mod % 10))))

(defn manhattan [a b]
  "Calculate the Manhattan distance between two points"
  (->> (map (comp abs (partial apply -) list) a b)
       (reduce +)))

(defn combinations [items]
  (let [[a b] (split-at 1 items)
        c (inc (count b))
        k (fn [l] (reduce conj () (map #(let [[x y] (split-at % l)] (concat x a y)) (range c))))]
    (if (empty? b)
      (list a)
      (reduce concat () (map k (combinations b))))))

(defn angle [a b]
  (->> [b a] (apply map (comp (partial apply -) list))
             (apply #(Math/atan2 %2 %1))))

(defn a* [from to next]
  "Find path between two points"
  (loop [routes [[from #{from}]]]
    (if-not (empty? routes)
      (let [[tile path] (first routes)]
        (if (= tile to)
          path
          (->> (next tile)
               (filter (complement path))
               (map (juxt identity #(conj path %)))
               (apply conj (subvec routes 1))
               (recur)))))))

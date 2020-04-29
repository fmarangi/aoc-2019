(ns aoc-2019.day24)

(defn next-minute [input]
  (let [off   [-1 -6 1 6]
        adjac (fn [o] (filter #{\#} (map #(get input (+ o %)) off)))
        rules {\. {1 \# 2 \#} \# {1 \#}}
        next  (fn [w c] (get-in rules [w c] ({\. \. \# \.} w w)))]
    (->> input (map-indexed (fn [a b] (next b (count (adjac a)))))
               (reduce str))))

(defn appears-twice [input]
  (->> [input #{input}]
       (iterate (fn [[l s]] (let [n (next-minute l)] [n (conj s n)])))
       (map-indexed (fn [c [l s]] (list l (< c (count s)) s)))
       (drop-while second)
       (first)
       (first)))

(defn biodiversity [input]
  (->> input (filter (comp not #{\newline}))
             (map (partial {\. 0 \# 1}))
             (map-indexed #(bit-shift-left %2 %1))
             (apply +)))

(defn part-1 [input]
  (->> input (appears-twice) (biodiversity)))

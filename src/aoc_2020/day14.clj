(ns aoc-2020.day14
  (:require
    [clojure.string :as s]))

(defn- zero-pad [l n]
  (let [bn (Long/toBinaryString n)]
    (str (apply str (repeat (- l (count bn)) "0")) bn)))

(defn- floating-bits [mask]
  (->> (map-indexed vector mask)
       (filter (comp #{\X} second))
       (map first)))

(defn- floating-masks [pos n]
  (let [cnt  (count pos)
        from (zero-pad cnt n)]
    (->> (mapcat vector pos from)
         (apply assoc (into [] (repeat 36 "1")))
         (reduce str "2r")
         (read-string))))

(defn apply-mask [mask x]
  (let [to-bin     #(read-string (s/replace (str "2rX" mask) #"X" %))
        [zero one] (map to-bin ["0" "1"])]
    (bit-and (bit-or x zero) one)))

(defn get-memory-addresses [mask n]
  (let [f (floating-bits mask)
        m (bit-or n (read-string (str "2r" (s/replace mask #"X" "1"))))
        p (range (bit-shift-left 1 (count f)))]
    (->> (map (partial floating-masks f) p)
         (map (partial bit-and m)))))

(defn part-1 [input]
  (let [values  (fn [l] (map read-string (next (re-find #"mem\[(\d+)\] = (\d+)" l))))
        reducer (fn [[mem mask] l]
                  (cond
                    (s/starts-with? l "mask") [mem (partial apply-mask (subs l 7))]
                    :else                     (let [[a b] (values l)] [(assoc mem a (mask b)) mask])))]
    (->> (s/split-lines input)
         (reduce reducer [{} identity])
         (first)
         (vals)
         (reduce +))))

(defn part-2 [input]
  (let [values  (fn [l] (map read-string (next (re-find #"mem\[(\d+)\] = (\d+)" l))))
        reducer (fn [[mem mask] l]
                  (cond
                    (s/starts-with? l "mask") [mem (partial get-memory-addresses (subs l 7))]
                    :else                     (let [[a b] (values l)]
                                                [(apply assoc mem (mapcat vector (mask a) (repeat b))) mask])))]
    (->> (s/split-lines input)
         (reduce reducer [{} identity])
         (first)
         (vals)
         (reduce +))))

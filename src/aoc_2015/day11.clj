(ns aoc-2015.day11)

(defn next-password [pwd]
  (->> (rseq (mapv int pwd))
       (reduce (fn [[res d] p] (let [p (+ p d -97)] [(str (char (+ 97 (mod p 26))) res) (quot p 26)])) ["" 1])
       (first)))

(defn no-forbidden [password]
  (nil? (re-find #"[ilo]" password)))

(defn has-straight [password]
  (->> (map int password)
       (partition 3 1)
       (some (fn [[a b c]] (and (= b (inc a)) (= c (inc b)))))
       (true?)))

(defn has-pairs [password]
  (->> (partition 2 1 password)
       (filter (partial apply =))
       (into #{})
       (count)
       (< 1)))

(defn part-1 [input]
  (let [f (every-pred no-forbidden has-straight has-pairs)]
    (->> (iterate next-password input)
         (filter f)
         (first))))

(defn part-2 [input]
  (let [f (every-pred no-forbidden has-straight has-pairs)]
    (->> (iterate next-password input)
         (filter f)
         (drop 1)
         (first))))

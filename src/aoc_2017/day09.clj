(ns aoc-2017.day09)

(defn score [stream]
  (loop [[c & s] stream
         garbage? false
         level 0
         score 0]
    (if (nil? c)
      score
      (case c
        \< (recur s true level score)
        \> (recur s false level score)
        \{ (recur s garbage? (if garbage? level (inc level)) score)
        \! (recur (next s) garbage? level score)
        \} (if garbage?
             (recur s garbage? level score)
             (recur s garbage? (dec level) (+ score level)))
        (recur s garbage? level score)))))

(defn garbage [stream]
  (loop [[h & s] stream g false c 0]
    (if (nil? s)
      c
      (case h
        \< (recur s true (if g (inc c) c))
        \> (recur s false c)
        \! (recur (next s) g c)
        (recur s g (if g (inc c) c))))))

(def part-1 score)

(def part-2 garbage)

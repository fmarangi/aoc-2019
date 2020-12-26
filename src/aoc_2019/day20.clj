(ns aoc-2019.day20
  (:require [aoc-2019.utils :refer [bfs]]
            [clojure.string :refer [index-of trim]]))

(def one-level 1000000)

(defn find-portals [maze]
  (let [len (index-of maze \newline)
        m   (-> (str "(?s)([A-Z]{2}|[A-Z].{" len "}?[A-Z])")
                (re-pattern)
                (re-matcher maze))
        itr (fn [[o g]] (when (.find m (+ o 2)) [(.start m) (.group m)]))]
    (take-while some? (drop 1 (iterate itr [-2 ""])))))

(defn portal-name [i]
  (apply str ((juxt first last) i)))

(defn valid-tile [maze]
  (fn [p] (#{\.} (get maze p))))

(defn open-tile [maze l]
  (let [valid? (valid-tile maze)]
    (fn [offset]
      (->> (map (partial + offset) [-1 2 (- l) (* 2 l)])
           (filter valid?)
           (first)))))

(defn teleport [portals]
  (->> (filter (comp not #{"AA" "ZZ"} first) portals)
       (group-by first)
       (vals)
       (map #(map second %))
       (map (juxt identity reverse))
       (flatten)
       (apply hash-map)))

(defn teleport-to-level [portals outer]
  (->> (teleport portals)
       (map (fn [[f t]] [f ((if (outer f) - +) t one-level)]))
       (into {})))

(defn portals [maze]
  (let [l (inc (index-of maze \newline))
        o (open-tile maze l)]
    (->> (find-portals maze)
         (map (juxt (comp portal-name last) #(o (first %)))))))

(defn next-step [maze portals]
  (let [line-length (inc (index-of maze \newline))
        offsets     (list 1 -1 line-length (- line-length))
        valid?      (valid-tile maze)
        teleports   (teleport portals)]
    (fn [p]
      (->> (map (partial + p) offsets)
           (concat (list (teleports p)))
           (filter (every-pred some? valid?))))))

(defn is-outer? [maze]
  (let [l  (inc (index-of maze \newline))
        r  (quot (count (trim maze)) l)
        tb #{2 (- r 2)}
        lr #{2 (- l 4)}]
    (fn [o] (some? (or (tb (quot o l)) (lr (mod o l)))))))

(defn next-step-with-levels [maze portals max-level]
  (let [line-length (inc (index-of maze \newline))
        offsets     (list 1 -1 line-length (- line-length))
        valid?      (valid-tile maze)
        teleports   (teleport-to-level portals (is-outer? maze))]
    (fn [p]
      (let [current-level (quot p one-level)
            p             (mod p one-level)]
        (if (> current-level max-level)
          (list)
          (->> (map (partial + p) offsets)
               (filter valid?)
               (concat (filter some? (list (teleports p))))
               (map (partial + (* current-level one-level)))
               (filter pos?)))))))

(defn from-to [portals]
  (let [p (into {} portals)]
    (map p ["AA" "ZZ"])))

(defn find-path [maze]
  (let [ports     (portals maze)
        [from to] (from-to ports)]
    (bfs from to (next-step maze ports))))

(defn find-path-with-levels [maze]
  (let [ports     (portals maze)
        [from to] (from-to ports)]
    (bfs from to (next-step-with-levels maze ports 25))))

(defn part-1 [input]
  (dec (count (find-path input))))

(defn part-2 [input]
  (dec (count (find-path-with-levels input))))

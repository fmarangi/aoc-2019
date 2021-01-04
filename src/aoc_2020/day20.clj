(ns aoc-2020.day20
  (:require [aoc-utils.ascii-image :as image]
            [clojure.set :as set]
            [clojure.string :as s]))

(def sea-monster "..................#.\n#....##....##....###\n.#..#..#..#..#..#...")

(defn transpose [x]
  (apply mapv vector x))

(defn ascii->int [x]
  (read-string (str "2r" (apply str (map #(get {\# 1} % 0) x)))))

(defn- parse-tile [tile]
  (let [parts (s/split-lines tile)
        id    (read-string (subs (first parts) 5 9))]
    (vector id (s/join "\n" (rest parts)))))

(defn- borders [tile]
  (let [abcd (image/borders tile)]
    (map (partial apply str) (concat abcd (map reverse abcd)))))

(defn- others [tiles x]
  (set (mapcat second (dissoc tiles x))))

(defn parse-input [input]
  (into {} (map parse-tile (s/split input #"\n\n"))))

(defn find-corners [tiles]
  (let [tiles (zipmap (keys tiles) (map borders (vals tiles)))
        xs    (fn [a b] (set/intersection (set b) (others tiles a)))]
    (->> (map (fn [[a b]] [a (xs a b)]) tiles)
         (sort-by (comp count second))
         (take 4))))

(defn is-sea-monster? [sea-monster]
  (let [sm (map ascii->int (s/split-lines sea-monster))]
    (fn [portion]
      (->> (map ascii->int portion)
           (map bit-and sm)
           (map = sm)
           (every? true?)))))

(defn- check-rows [rows]
  (->> (map #(partition 20 1 %) rows)
       (apply map list)
       (filter (is-sea-monster? sea-monster))
       (count)))

(defn find-sea-monsters [grid]
  (->> (partition 3 1 (s/split-lines grid))
       (map check-rows)
       (reduce +)))

(defn- for-edges [tile edges pos]
  (let [values (fn [s] (filter (set pos) (map #(.indexOf s %) edges)))]
    (->> (image/all-directions tile)
         (map (juxt identity image/borders))
         (filter (comp (partial = (count pos)) count values second))
         (first))))

(defn- first-corner [tiles]
  (let [[id s] (first (find-corners tiles))]
    (list id (first (for-edges (tiles id) s [1 2])))))

(defn- find-tile [tiles side]
  (let [all-sides (zipmap (keys tiles) (map (comp set borders) (vals tiles)))]
    (ffirst (filter (comp #(% side) second) all-sides))))

(defn- remove-borders [image]
  (let [m (comp butlast next)]
    (->> (m (s/split-lines image))
         (map (comp (partial apply str) m))
         (s/join "\n"))))

(defn rebuild-image [tiles]
  (let [edge-len (int (Math/sqrt (count tiles)))]
    (loop [res    (let [[id t] (first-corner tiles)] [[id t (vec (image/borders t))]])
           others (dissoc tiles (ffirst res))]
      (if (empty? others)
        (->> (map (comp s/split-lines remove-borders second) res)
             (partition edge-len)
             (mapcat #(partition edge-len (apply interleave %)))
             (map (partial apply str))
             (s/join "\n"))
        (let [cnt         (count res)
              last-found  (if (zero? (mod cnt edge-len)) 2 1)
              last-border (get-in res (if (= last-found 2) [(- cnt edge-len) 2 2] [(dec cnt) 2 1]))
              id          (find-tile others last-border)
              [t e]       (for-edges (others id) [last-border] [({1 3 2 0} last-found)])]
          (recur (conj res [id t (vec e)]) (dissoc others id)))))))

(defn count-hash [img]
  (get (frequencies img) \#))

(defn water-roughness [grid]
  (->> (image/all-directions grid)
       (map find-sea-monsters)
       (filter pos?)
       (first)
       (* (count-hash sea-monster))
       (- (count-hash grid))))

(defn part-1 [input]
  (reduce * (map first (find-corners (parse-input input)))))

(defn part-2 [input]
  (water-roughness (rebuild-image (parse-input input))))

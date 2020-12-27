(ns aoc-2019.day11
  (:require [aoc-2019.intcode :as intcode]
            [clojure.string :as s]))

(def offsets {\N [0 -1] \E [1 0] \S [0 1] \W [-1 0]})

(defn turn [[panel dir] lr]
  (let [right  {\N \E \E \S \S \W \W \N}
        left   (comp right right right)
        newdir (({0 left 1 right} lr) dir)]
    [(mapv + panel (offsets newdir)) newdir]))

(defn- run-program [code input _ pos rel-base]
  (loop [[c i o p r] [code input [] pos rel-base]]
    (let [op (mod (c p) 100)]
      (cond
        (= op 99)                 [c i o p r]
        (and (= op 3) (empty? i)) [c i o p r]
        :else                     (recur (intcode/run-intcode c i o p r))))))

(defn paint-hull [intcode init]
  (loop [panels {[0 0] init}
         curr   [[0 0] \N]
         data   [intcode [(panels [0 0])] [] 0 0]
         k      0]
    (let [data (apply run-program data)]
      (if (empty? (data 2))
        panels
        (let [[color dir] (data 2)
              panel       (turn curr dir)]
          (recur
            (assoc panels (curr 0) color)
            panel
            (assoc data 1 [(panels (panel 0) 0)]) (inc k)))))))

(defn registration-id [panels]
  (let [row-len (apply max (map first (keys panels)))]
    (->> (range)
         (map (juxt #(mod % row-len) #(quot % row-len)))
         (take (* row-len 6))
         (map #(get {0 \. 1 \#} (panels %) \.))
         (partition row-len)
         (map (partial apply str))
         (s/join "\n"))))

(defn part-1 [input]
  (let [intcode (intcode/parse-program input)]
    (count (paint-hull intcode 0))))

(defn part-2 [input]
  (let [intcode (intcode/parse-program input)]
    (registration-id (paint-hull intcode 1))
    "EGHKGJER"))

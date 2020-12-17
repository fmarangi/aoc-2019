(ns aoc-2020.day16
  (:require
    [clojure.set :as set]
    [clojure.string :refer [split split-lines starts-with?]]))

(defn- from-to [x]
  (let [[from to] (map read-string (split x #"-"))]
    (fn [v] (and (>= v from) (<= v to)))))

(defn- to-rule [line]
  (let [[name data] (split line #": ")]
    [name (apply some-fn (map from-to (split data #" or ")))]))

(defn- ticket-values [ticket]
  (mapv read-string (split ticket #",")))

(defn- difference [fields field]
  (apply set/difference (fields field) (vals (dissoc fields field))))

(defn- rule-finder [fields]
  (let [diff (partial difference fields)
        one  (fn [c] (= (count c) 1))]
    (-> (fn [[f r]] (let [d (if (one r) r (diff f))] (when (one d) [f (first d)])))
        (some fields))))

(defn- remove-found-field [fields field rule]
  (->> (dissoc fields field)
       (map (fn [[k v]] [k (disj v rule)]))
       (into {})))

(defn part-1 [input]
  (let [[rules _ nearby] (split input #"\n\n")
        all-rules        (apply some-fn (map (comp second to-rule) (split-lines rules)))
        all-values       (mapcat ticket-values (rest (split-lines nearby)))]
    (reduce + (filter (complement all-rules) all-values))))

(defn part-2 [input]
  (let [[rules my nearby] (split input #"\n\n")
        my-ticket         (ticket-values (second (split-lines my)))
        rules             (into {} (map to-rule (split-lines rules)))
        all-rules         (apply some-fn (vals rules))
        valid-data        (partial every? all-rules)
        xform             (comp (drop 1)
                                (map ticket-values)
                                (filter valid-data))
        tickets           (map-indexed vector (apply mapv vector (into [] xform (split-lines nearby))))
        valid-per-field   (fn [f] (into #{} (map first (filter (fn [[i t]] (every? f t)) tickets))))
        fields            (into {} (map (fn [[k v]] [k (valid-per-field v)]) rules))
        find-and-unset    (fn [[rules fields]]
                            (let [[f r] (rule-finder fields)]
                              [(conj rules [f r]) (remove-found-field fields f r)]))
        solver            (comp (filter #(starts-with? (first %) "departure"))
                                (map second)
                                (map my-ticket))]
    (->> (iterate find-and-unset [[] fields])
         (drop-while (comp not-empty second))
         (ffirst)
         (transduce solver *))))

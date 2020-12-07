(ns aoc-2020.day07
  (:require
    [aoc-2019.utils :refer [to-int]]))

(defn parse-group [group]
  (->> (re-seq #"(\d) (.*?) bags?" group)
       (map rest)
       (map (juxt second (comp to-int first)))
       (into {})))

(defn parse-input [input]
  (->> (re-seq #"(.*?) bags contain ([^.]*)." input)
       (map rest)
       (map (juxt first (comp parse-group second)))
       (into {})))

(defn bag-finder [input]
  (let [bags (parse-input input)]
    (fn [type] (keys (filter (comp #(% type) second) bags)))))

(defn find-all [finder type]
  (let [types (finder type)]
    (->> (map (partial find-all finder) types)
         (reduce concat types))))

(defn count-all-bags [bags type]
  (->> (get bags type)
       (map (fn [[k v]] (* v (count-all-bags bags k))))
       (reduce + 1)))

(defn part-1 [input]
  (->> (find-all (bag-finder input) "shiny gold")
       (into #{})
       (count)))

(defn part-2 [input]
  (-> (parse-input input)
      (count-all-bags "shiny gold")
      (dec)))

(ns aoc-2020.day19
  (:require
    [clojure.string :as s]))

(defn- parse-rule [r]
  (let [chunk (fn [c] (map read-string (s/split c #" ")))]
    (get #{"a" "b"} r (map chunk (s/split r #" \| ")))))

(defn- parse-rules [rules]
  (->> (s/replace rules "\"" "")
       (s/split-lines)
       (map #(let [[k v] (s/split % #": ")] [(read-string k) (parse-rule v)]))
       (into {})))

(defn- validation-pattern [rules rule]
  (let [vp (fn [r] (let [k (rules r)] (if (string? k) k (validation-pattern rules r))))
        rp (map #(apply str (map vp %)) (rules rule))]
    (str "(?:" (s/join "|" rp) ")")))

(defn parse-input [input]
  (let [[rules messages] (s/split input #"\n\n")
        pattern          (validation-pattern (parse-rules rules) 0)]
    [(s/split-lines messages) (partial re-matches (re-pattern pattern))]))

(defn parse-input-pt2 [input]
  (let [[r messages] (s/split input #"\n\n")
        pattern      (partial validation-pattern (parse-rules r))
        replace-8    (fn [from c]
                       (s/replace from (pattern 8)
                                  (str (pattern 42) "+")))
        replace-11   (fn [from c]
                       (s/replace from (pattern 11)
                                  (str (pattern 42) "{" c "}" (pattern 31) "{" c "}")))]
    (->> (range 1 5)
         (map #(replace-8 (replace-11 (pattern 0) %) %))
         (map #(partial re-matches (re-pattern %)))
         (apply some-fn)
         (list (s/split-lines messages)))))

(defn message-validity [messages pattern]
  (filter pattern messages))

(defn part-1 [input]
  (count (apply message-validity (parse-input input))))

(defn part-2 [input]
  (count (apply message-validity (parse-input-pt2 input))))

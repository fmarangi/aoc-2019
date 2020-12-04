(ns aoc-2020.day04
  (:require
    [aoc-2019.utils :refer [to-int]]
    [clojure.string :refer [split]]))

(defn between [from to]
  (fn [x] (and (>= (to-int x) from) (<= (to-int x) to))))

(defn matches [pattern]
  (fn [x] (some? (re-matches pattern x))))

(def validation-rules
  {"byr" (between 1920 2002)
   "iyr" (between 2010 2020)
   "eyr" (between 2020 2030)
   "hgt" (let [rules {"cm" (between 150 193) "in" (between 59 76)}]
           (fn [x]
             (let [[_ h u] (re-matches #"^(\d+)(cm|in)$" x)]
               ((rules u (constantly false)) h))))
   "hcl" (matches #"^#[0-9a-f]{6}$")
   "ecl" (matches #"^(amb|blu|brn|gry|grn|hzl|oth)$")
   "pid" (matches #"^\d{9}$")
   "cid" (constantly true)})

(defn parse-passport [data]
  (->> (re-seq #"(byr|iyr|eyr|hgt|hcl|ecl|pid|cid):([^\s$]+)" data)
       (map (comp vec rest))
       (into {"cid" nil})))

(defn parse-passports [data]
  (map parse-passport (split data #"\n\n")))

(defn has-all-data [passport]
  (= (count passport) 8))

(defn has-valid-data [passport]
  (let [valid (fn [[key value]] ((validation-rules key) value))]
    (every? valid (seq passport))))

(defn check-valid [passports]
  (filter has-all-data passports))

(defn check-valid-values [passports]
  (filter has-valid-data (check-valid passports)))

(defn part-1 [input]
  (count (check-valid (parse-passports input))))

(defn part-2 [input]
  (count (check-valid-values (parse-passports input))))

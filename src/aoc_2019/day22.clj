(ns aoc-2019.day22
  (:require [clojure.string :as s]))

(defn value [op]
  (read-string (re-find #"-?\d+" op)))

(defn technique [cards]
  (fn [pos desc]
    (cond
      (s/starts-with? desc "cut")       (mod (- pos (value desc)) cards)
      (s/starts-with? desc "deal with") (mod (* pos (value desc)) cards)
      :else                             (- cards 1 pos))))

(defn shuffle-deck [ops cards pos]
  (reduce (technique cards) pos (s/split-lines ops)))

(defn pow [n exp m]
  (.modPow (biginteger n) (biginteger exp) (biginteger m)))

(defn zero [input cards]
  (shuffle-deck input cards 0))

(defn multiplier [input cards]
  (mod (- (shuffle-deck input cards 1) (zero input cards)) cards))

(defn offset-after-x-times [cards times multi offset]
  (mod (* offset (- 1 (pow multi times cards)) (pow (- 1 multi) -1 cards)) cards))

(defn card-at-position [input cards times pos]
  (let [[o m]  (map #(% input cards) [zero multiplier])
        multi  (pow m times cards)
        offset (offset-after-x-times cards times m o)]
    (-> (mod (- pos offset) cards)
        (* (pow multi -1 cards) multi)
        (mod (* multi cards))
        (quot multi))))

(defn part-1 [input]
  (shuffle-deck input 10007 2019))

; Observations
; - after 1 round, each cards moved its initial position * steps (multiplier) + offset forward
; - after n rounds, the position multiplier is (multiplier ^ n) % cards, eg. 1688 7356 8248 2887 9854 for the test deck
; - the offset increases in a geometric series mod cards, see https://en.wikipedia.org/wiki/Geometric_series
; - the offset calculation is slightly different than a geomtric sum:
;   since we are using modular arithmetic, the modular multiplicative inverse is needed (pow n -1 mod)
; - once we have the multiplier and the offset, we can calculate the card at pos x using the Chinese remainder theorem
; - VOILÀ!

(defn part-2 [input]
  (long (card-at-position input 119315717514047 101741582076661 2020)))

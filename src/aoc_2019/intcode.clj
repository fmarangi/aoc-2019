(ns aoc-2019.intcode
  (:require
    [aoc-2019.utils :refer [digits to-int]]
    [clojure.string :refer [split trim]]))

(defn parse-input [x]
  (mapv to-int (split (trim x) #",")))

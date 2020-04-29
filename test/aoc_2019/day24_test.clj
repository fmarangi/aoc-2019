(ns aoc-2019.day24-test
  (:require
    [aoc-2019.day24 :refer :all]
    [clojure.string :refer [trim]]
    [clojure.test :refer :all]))

(deftest calc-next-minute
  (are [in out] (= (next-minute in) out)
    "....#\n#..#.\n#..##\n..#..\n#...." "#..#.\n####.\n###.#\n##.##\n.##.."
    "#..#.\n####.\n###.#\n##.##\n.##.." "#####\n....#\n....#\n...#.\n#.###"
    "#####\n....#\n....#\n...#.\n#.###" "#....\n####.\n...##\n#.##.\n.##.#"
    "#....\n####.\n...##\n#.##.\n.##.#" "####.\n....#\n##..#\n.....\n##..."))

(deftest calc-first-layout-that-appers-twice
  (is (= (appears-twice "....#\n#..#.\n#..##\n..#..\n#....") ".....\n.....\n.....\n#....\n.#...")))

(deftest calc-biodiversity
  (is (= (biodiversity ".....\n.....\n.....\n#....\n.#...") 2129920)))

(deftest solve-puzzle
  (let [input (trim (slurp "resources/day24.txt"))]
    (is (= (part-1 input) 28903899))))

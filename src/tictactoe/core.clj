(ns tictactoe.core
  (:gen-class))

(defn repeatv [n to-repeat]
  (vec (repeat n to-repeat)))

(defn make-board [] (vec (repeat 9 "_")))

; Make this better.
(defn winner [board player]
  (let [winning-vector (repeatv 3 player)]
    (or
      (= (mapv board [0 1 2]) winning-vector)
      (= (mapv board [3 4 5]) winning-vector)
      (= (mapv board [6 7 8]) winning-vector)

      (= (mapv board [0 3 6]) winning-vector)
      (= (mapv board [1 4 7]) winning-vector)
      (= (mapv board [2 5 8]) winning-vector)

      (= (mapv board [0 4 8]) winning-vector)
      (= (mapv board [2 4 6]) winning-vector))))

(defn get-move [board player]
  (let [move (read)]
    (if (is-valid board move) (assoc board move player) false)))

(defn is-valid [board position]
  (= (get board position) "_"))
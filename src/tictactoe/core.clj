; It'd be great if this could be data structure agnostic?
; Why does it depend on vectors?
(ns tictactoe.core
  (:gen-class))

; Utility methods
(defn repeatv [n to-repeat]
  (vec (repeat n to-repeat)))

(defn make-board [] (vec (repeat 9 "__")))

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

(defn is-valid? [board position]
  (= (get board position) "__"))

(defn drawn? [board]
  (not (some true? (mapv (partial is-valid? board) (range 0 9)))))

(defn game-over? [board]
  (or (winner board :x) (winner board :o) (drawn? board)))

(defn get-move [board player]
  (let [move (read)]
    (if (is-valid? board move) (assoc board move player) false)))

(defn next-player [player]
  (if (= player :x) :o :x))

(defn print-board [board]
  (println (mapv board [0 1 2]))
  (println (mapv board [3 4 5]))
  (println (mapv board [6 7 8])))

; Should check winning conditions and break if not met
(defn play [board player]
  (print-board board)
  (if-let [new-board (get-move board player)]
    (if (game-over? new-board)
      (if (winner new-board player)
        (do (print-board board) (println "Winner!"))
        (println "Tied!"))
      (recur new-board (next-player player)))
    (recur board player)))


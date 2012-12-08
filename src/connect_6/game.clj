(ns connect-6.game
  (:use [slingshot.slingshot :only [throw+]]))

(def k 3)

(defn make-board [n]
  (let [vals (repeat n :empty)
        row  (apply vector vals)
        b-seq (repeat n row)]
    (apply vector b-seq)))

(defn cell-state [board row col]
  (nth (nth board row) col))

(defn check-win [board row col drow dcol]
  (let [color (cell-state board row col)]
    (defn check-rest [r c n]
      (println (list r c n))
      (cond
       (= n 0) color
       (= (cell-state board r c) color) (check-rest (+ r drow) (+ c dcol) (- n 1))
       :else false))
    (check-rest row col k)))

(defn win-left? [board row col]
  (check-win board row col 0 1))
(defn win-down? [board row col]
  (check-win board row col 1 0))
(defn win-diag-1? [board row col]
  (check-win board row col 1 1))
(defn win-diag-2? [board row col]
  (check-win board (+ row k -1) (+ col k -1) -1 -1))

(defn win-cell? [board row col]
  (or (win-left? board row col)
      (win-down? board row col)
      (win-diag-1? board row col)
      (win-diag-2? board row col)))

(defn win? [board]
  (def v (range (- (count board) k)))
  (doseq [row v]
    (doseq [col v] (win-cell? board row col))))

(defn play-stone [board row col color]
  (if (not (= (cell-state board row col) :empty))
    (throw+ {:type ::illegal-play})
    (let [next (assoc board row
                      (assoc (nth board row) col color))]
      (if (win? next)
        (throw+ {:type ::win :color color})
        next))))

(defn print-board [board]
  (doseq [r board]
    (doseq [v r]
      (case v
        :empty (print ". ")
        :white (print "o ")
        :black (print "x ")))
    (println)))
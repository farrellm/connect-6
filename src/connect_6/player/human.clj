(ns connect-6.player.human
  (:require [ connect-6.game :as game]))

(defn str-to-ints [string]
  (flatten
   (map #(if (= % "")
           '()
           (Integer/parseInt %))
        (.split #"[\s,]+" string))))

(defn make-player []
  (fn [board last plays]
    (game/print-board board)
    (map
     (fn [i] (str-to-ints (read-line)))
     (range plays))))
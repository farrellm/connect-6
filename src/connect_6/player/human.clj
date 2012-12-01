(ns connect-6.player.human
  (:require [ connect-6.game :as game]))

(defn str-to-ints [string]
  (flatten
   (map #(if (= % "")
           '()
           (Integer/parseInt %))
        (.split #"[\s,]+" string))))

(defrecord Play [player moves])

(defn player [board last plays]
  (game/print-board board)
  (Play. player 
         (map
          (fn [i] (str-to-ints (read-line)))
          (range plays))))

(defn make-player []
  player)
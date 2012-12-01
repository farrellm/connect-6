(ns connect-6.core
  (:require [ connect-6.game :as game])
  (:require [ connect-6.player.human :as human])
  (:use [slingshot.slingshot :only [throw+ try+]])
  (:gen-class :main true))

(defrecord Round [board player1 player2])

(defn play [board player plays])

(defn first-round [state]
  (play (:board state) (:player2 state) 1))

(defn game-loop [state]
  #(game-loop)
  (play board player1 2)
  (play board player2 2)
  )

(defn -main []
  (println "Connect-6")

  (let [s0 {:board (game/make-board 16)
            :player1 (human/make-player)
            :player1 (human/make-player)}
        s1 (first-round s0)]
    (trampoline game-loop)))

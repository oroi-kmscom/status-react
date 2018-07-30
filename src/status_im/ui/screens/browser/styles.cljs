(ns status-im.ui.screens.browser.styles
  (:require-macros [status-im.utils.styles :refer [defstyle defnstyle]])
  (:require [status-im.ui.components.colors :as colors]))

(def browser {:flex 1})

(defstyle dapp-name
  {:flex            1
   :justify-content :center
   :margin-left     12
   :android         {:padding-bottom 6}})

(def dapp-name-text
  {:color     colors/text-light-gray
   :font-size 16})

(defstyle dapp-text
  {:color   colors/gray
   :ios     {:margin-top 4}
   :android {:font-size 13}})

(def toolbar
  {:background-color   :white
   :height             48
   :flex-direction     :row
   :align-items        :center
   :padding-horizontal 32})

(def disabled-button
  {:opacity 0.4})

(def forward-button
  {:margin-left 72})

(def background
  {:flex             1
   :background-color colors/gray-lighter
   :align-items      :center
   :justify-content  :center})

(def web-view-loading
  {:flex             1
   :background-color colors/gray-transparent
   :align-items      :center
   :justify-content  :center
   :position         :absolute
   :top              0
   :bottom           0
   :left             0
   :right            0})

(def web-view-error
  {:flex             1
   :justify-content  :center
   :align-items      :center
   :background-color colors/gray-lighter})

(def web-view-error-text
  {:color colors/gray})

(defnstyle toolbar-content [show-actions]
  {:flex-direction     :row
   :flex               1
   :border-radius      4
   :height             36
   :background-color   colors/gray-lighter
   :padding-horizontal 12
   :margin-right       5
   :align-items        :center
   :android            {:margin-left (if show-actions 66 20)}
   :ios                {:margin-left 20}})

(defstyle url-input
  {:flex              1
   :font-size         14
   :letter-spacing    -0.2
   :margin-horizontal 5
   :android           {:padding 0}})

(def url-text
  {:font-size         14
   :letter-spacing    -0.2
   :margin-horizontal 5})


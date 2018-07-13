(ns status-im.extensions.registry
  (:require [pluto.reader :as reader]
            [pluto.registry :as registry]
            [status-im.ui.components.react :as react]))

(def components
  {'view react/view
   'text react/text})

(def extensions
  '{meta {:name          ""
          :description   ""
          :documentation ""}

    views/STRK
    [view {}
     [view {:style {:flex 1}}
      [text {}
       "STRK"
       #_(or name (i18n/label :t/cryptokitty-name {:id id}))]
      [text {}
       "Short bio"]]]

    hooks/status.collectibles.STRK
    {:name     "CryptoStrikers"
     :symbol   :STRK
     :view     @views/STRK
     :contract "0xdcaad9fd9a74144d226dbf94ce6162ca9f09ed7e"}

    views/command.send.token.selector
    [list/item {}
     [text ""]]

    hooks/status.chat.commands.send
    {:properties [{:id :asset
                   :source @query/status.wallet.tokens ;; Must follow some format?
                   :default :ETH
                   :selection @views/command.send.token.selector}
                  {:id   :amount
                   :type :gwei}]
     :on-triggered @events/status.wallet.send}})

(defn parse [m]
  (reader/parse {:capacities {:components components
                              :events [{:name 'events/status.wallet.send}]
                              :hooks {'hooks/status.collectibles {:properties [{:name :name :type :string}
                                                                               {:name :symbol :type :string}
                                                                               {:name :view :type :view}
                                                                               {:name :contract :type :string}]}
                                      'hooks/status.chat.commands {:properties [{:name :scope :type #{:personal-chats}}
                                                                                {:name :short-preview :type :view}
                                                                                {:name :preview :type :view}
                                                                                {:name :parameters
                                                                                 :type [{:name :id :type :keyword}
                                                                                        {:name :type :type #{:text :phone :password :number}}
                                                                                        {:name :placeholder :type :string}
                                                                                        {:name :suggestions :type :view}]}
                                                                                {:name :on-triggered :type :event}]}}}}
                m))

(def registry (registry/new-registry))

(def id "status")

(let [{:keys [data errors] :as ext} (parse extensions)]
  (when errors
    (throw (ex-info "Failed to parse status extensions" ext)))
  (registry/add! registry id data)
  (registry/activate! registry id))

(defn collectibles []
  (registry/hooks registry 'hooks/status.collectibles))

;; TODO: Check this file manually
(ns bukkure.config
  "Provides a thin wrapper for bukkit config"
  (:require [bukkure.logging :as logging])
  (:require [bukkure.util :as util]))

(defn config-defaults 
  "Loads the bukkit config file for the given plugin and sets defaults, returns a configuration object"
  [plugin]
  (.saveDefaultConfig plugin))

(defn defcn
  "Defines the config methods for a type"
  [type]
  `(defn ~(symbol (str "get-" (name type)))
     ~(str "Get a " type " from a config path")
     [~(symbol "plugin") ~(symbol "path")]
     (try
       (~(symbol (str ".get" (util/camelcase (name type)))) (.getConfig ~(symbol "plugin")) ~(symbol "path"))
       (catch Exception ~(symbol "e") nil))))

(defmacro defcns
  "Wrapper around [[defn]] to map a list to it, and execute the form returned"
  [& types]
  (let [forms (map defcn types)]
    `(do ~@forms)))

;; This creates the functions (get-... plugin path), eg
;; (get-string plugin path) to get specific config
;; entries with the correct types.

(defcns string int boolean double long list string-list integer-list boolean-list double-list float-list long-list byte-list character-list short-list map-list vector offline-player item-stack configuration-section)

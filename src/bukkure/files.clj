(ns bukkure.files
  (:require [cheshire.core :as json])
  (:require [clojure.java.io :as io]))

(defn data-folder
  "Returns the folder that the plugin data's files are located in."
  [plugin]
  (.getDataFolder plugin))

(defn read-json-file
  "Read a json file from the [[data-folder]]"
  [plugin filename]
  (try
    (json/decode (slurp (io/file (data-folder plugin) filename)) true)
    (catch java.io.FileNotFoundException e {})
    (catch Exception e (.printStackTrace e) {})))

(defn write-json-file
  "Write a json file to the [[data-folder]]"
  [plugin filename data]
  (let [file (io/file (data-folder plugin) filename)]
    (io/make-parents file)
    (spit file (json/encode data))))

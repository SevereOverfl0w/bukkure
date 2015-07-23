;; TODO: Check this file manually
(ns bukkure.logging
  (:import [org.bukkit Bukkit ChatColor]))

(defn logsend [str]
  (if-let [sender (Bukkit/getConsoleSender)]
    (.sendMessage sender str)
    (println str)))

(defmacro info
  "Log an info-style message"
  [fmt & args]
  `(logsend (format ~(str ChatColor/GREEN (.getName *ns*) ChatColor/RESET ":" ChatColor/BLUE (:line (meta &form)) ChatColor/RESET " - " fmt) ~@args)))

(defmacro warn [fmt & args]
  "Log an warn-style message"
  `(logsend (format ~(str ChatColor/YELLOW (.getName *ns*) ChatColor/RESET ":" ChatColor/BLUE (:line (meta &form)) ChatColor/RESET " - " fmt) ~@args)))

(defmacro debug [fmt & args]
  "Log an debug-style message"
  `(logsend (format ~(str ChatColor/RED (.getName *ns*) ChatColor/RESET ":" ChatColor/BLUE (:line (meta &form)) ChatColor/RESET " - " fmt) ~@args)))

(defmacro bug
  "as in bug in code/coding when this is reached"
  [fmt & args]
  `(logsend (Bukkit/getConsoleSender) (format ~(str "[BUG]" ChatColor/RED (.getName *ns*) ChatColor/RESET ":" ChatColor/BLUE (:line (meta &form)) ChatColor/RESET " - " fmt) ~@args)))

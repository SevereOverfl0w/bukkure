;; TODO: Check this file manually
(ns bukkure.bukkit
  (:require [bukkure.util :as util])
  (:import [org.bukkit Bukkit]
           [java.util UUID]))

(defn server
  "Returns an instance of the server"
  []
  (Bukkit/getServer))

(defn plugin-manager
  "Returns the plugin manager for the server"
  []
  (.getPluginManager (server)))

(defn scheduler
  "Returns the scheduler for the server"
  []
  (.getScheduler (server)))

(defn services-manager
  "Returns the services manager"
  []
  (.getServicesManager (server)))

(defn worlds
  "Get the worlds for the server"
  []
  (.getWorlds (server)))

(defn online-players
  "Get a list of online players as Player objects"
  []
  (seq (.getOnlinePlayers (server))))

(defn broadcast
  "Broadcast a message to all online players"
  [fmt & args]
  (.broadcastMessage (server) (apply format fmt args)))

(defn broadcast-permission
  "Broadcast a message to all online players with a permission"
  [permission fmt & args]
  (.broadcastMessage (server) (apply format fmt args) permission))

(defn world-by-name
  "Get a world by it's name"
  [name]
  (.getWorld (server) name))

(defn world-by-uuid
  "Get a world by it's uuid"
  [uuid]
  (.getWorld (server) (UUID/fromString uuid)))

(defn seconds-to-ticks [s]
  "Converts seconds to ticks"
  (int (* 20 s)))

(defn ui-sync
  "Execute a given function on the main UI thread"
  [plugin fn]
  (.runTask (scheduler) plugin fn))

(defn delayed-task
  "Execute a given function on the main UI thread after a delay in server ticks (1 tick = 1/20 second), will return a task id you can use to cancel the task - if you specify async?, take care not to directly call any Bukkit API and, by extension, and clj-minecraft functions that use the Bukkit API within this function"
  [plugin fn delay & [async?]]
  (if async?
    (.runTaskLaterAsynchronously (scheduler) plugin fn (long delay))
    (.runTaskLater (scheduler) plugin fn (long delay))))

(defn repeated-task
  "Execute a given function repeatedly on the UI thread, delay and period in server ticks. If you specify async?, take care not to directly call any Bukkit API and, by extension, and clj-minecraft functions that use the Bukkit API within this function"
  [plugin fn delay period & [async?]]
  (if async?
    (.runTaskTimerAsynchronously (scheduler) plugin fn (long delay) (long period))
    (.runTaskTimer (scheduler) plugin fn (long delay) (long period))))

(defn cancel-task
  "Cancel a task by it's id"
  [task-id]
  (.cancelTask (scheduler) task-id))

(defn running-task?
  "Check if a task is currently running"
  [task-id]
  (.isCurrentlyRunning (scheduler) task-id))

(defn queued-task?
  "Check if a task is queued"
  [task-id]
  (.isQueued (scheduler) task-id))

;; TODO: Check this file manually
(ns bukkure.entity
  (:require [bukkure.util :as util]))

(def entitytypes (util/map-enums org.bukkit.entity.EntityType))

(defn get-entities
  "Get the entities for a world, search by entity class, living or all"
  [world & {:keys [type-keys living?]}]
  (cond
   type-keys
   (let [entclasses
         (filter #(not (nil? %))
                 (map #(if-let [t (get entitytypes %)] (.getEntityClass t))
                      (if (coll? type-keys) type-keys [type-keys])))]
     (if (not-empty entclasses)
       (.getEntitiesByClasses world (into-array Class entclasses))
       []))
   living?
   (.getLivingEntities world)
   :else
   (.getEntities world)))

(defn find-entity
  "Find an entity by it's name"
  [nm]
  (let [names (map #(name (first %)) entitytypes)]
    (filter #(.contains % (.toLowerCase nm)) names)))

(defn spawn-entity
  "Spawn an entity by name, at a location"
  [location entityname]
  (let [type (get entitytypes (keyword entityname))]
    (when (and type (.isSpawnable type))
      (.spawnEntity (.getWorld location) location type))))

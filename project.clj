(defproject cljbukkit "0.1.0-SNAPSHOT"
  :description "Bringing Clojure to the Bukkit API"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-source-paths ["javasrc"]
  :javac-options ["-d" "classes/" "-source" "1.7" "-target" "1.7"]
  :resource-paths ["resources/*"]
  :plugins [[lein-localrepo "0.5.3"]]
  :dependencies [[org.clojure/clojure "1.7.0"]])

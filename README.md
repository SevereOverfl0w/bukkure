# Bukkure
Integrating Bukkit and Clojure for heaveanly delight.
[![Clojars Project](http://clojars.org/bukkure/latest-version.svg)](http://clojars.org/bukkure)

## Getting started
To start, you will need a build of Spigot. The official instructions to get Spigot is [here](https://www.spigotmc.org/wiki/spigot-installation/).
However, you can download the jar from [md-5's CI](http://ci.md-5.net/job/Spigot/) instead of building.

You should start spigot once, to create required folders & files (e.g. the plugins directory)

You will also need a build of Bukkure, currently the way to do this is to clone this repo

`git clone https://github.com/SevereOverfl0w/bukkure.git`
and then build with [leiningen](http://leiningen.org/)
`lein uberjar`
The created standalone jar from the `target` directory should then be placed in your Spigot `plugins` directory.

You now have a Minecraft server with a Clojure REPL running. To connect to it, you can do like so:
`lein repl :connect 4005`

There is also a bukkure plugin template available, but it is still in snapshot, to get it use:
`lein new bukkure my-awesome-plugin --snapshot`

## REPL
If you install Bukkure into your `plugins` directory, you will get a network REPL available. Connecting to it
is simple, just `lein repl :connect 4005`. The REPL can be configured from the `plugins/bukkure/config.yml` file.


Here's an example lifted from cljminecraft.
```clojure
user=> (in-ns 'bukkure.core)
#<Namespace bukkure.core>

bukkure.core=> (ev/find-event "break")
("painting.painting-break-by-entity" "hanging.hanging-break" "painting.painting-break" "entity.entity-break-door" "hanging.hanging-break-by-entity" "player.player-item-break" "block.block-break")

;; block.block-break looks good.. lets see what we can get out of it
bukkure.core=> (ev/describe-event "block.block-break")
#{"setExpToDrop" "isCancelled" "getEventName" "setCancelled" "getExpToDrop" "getPlayer" "getBlock"}

;; Cool, getBlock looks like I can use it..
bukkure.core=> (defn mybreakfn [ev] {:msg (format "You broke a %s" (.getBlock ev))})
#'bukkure.core/mybreakfn

bukkure.core=> (ev/register-event @clj-plugin "block.block-break" #'mybreakfn)
nil

;; Test breaking a block, I get a crazy message, let's make that more sane
bukkure.core=> (defn mybreakfn [ev] {:msg (format "You broke a %s" (.getType (.getBlock ev)))})
#'bukkure.core/mybreakfn
```

## API
The api is completely undocumented at this time, the best way to find out the api methods at this time is by diving into the source.

## License

Copyright © 2015 Dominic Monroe

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

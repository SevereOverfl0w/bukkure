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
The created standalone jar from the `target` directory should then be placed in your Spigot plugins directory.

You now have a Minecraft server with a Clojure REPL running. To connect to it, you can do like so:
`lein repl :connect 4005`

There is also a bukkure plugin template available, but it is still in snapshot, to get it use:
`lein new bukkure my-awesome-plugin --snapshot`

## API
The api is completely undocumented at this time, the best way to find out the api methods at this time is by diving into the source.

## License

Copyright © 2015 Dominic Monroe

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

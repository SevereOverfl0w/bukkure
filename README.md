# cljbukkit

A Clojure library designed to ... well, that part is up to you.

## Building
As the Bukkit API is now private, and not available on any repos, you must now
build it yourself. To do this, follow the
[Spigot build instructions](https://www.spigotmc.org/wiki/buildtools/), once 
built, you will need to work on the Bukkit file from
`Bukkit/target/bukkit-VERSION-SNAPSHOT.jar` where version might be `1.8.7-R0.1`
for example.

You will then need to install this to a local maven repository, fortunately
this is very easy.

`$ lein localrepo install ../build-spigot/Bukkit/target/bukkit-VERSION-SNAPSHOT.jar org.bukkit/bukkit VERSION`

## Usage


## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

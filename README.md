# Bukkure
Integrating Bukkit and Clojure for heaveanly delight.
[![Clojars Project](http://clojars.org/bukkure/latest-version.svg)](http://clojars.org/bukkure)

## Installing
As the Bukkit API is no longer available on public repos, you must now
build it yourself. To do this, follow the
[Spigot build instructions](https://www.spigotmc.org/wiki/buildtools/), once 
built, you will need to work on the Bukkit file from
`Bukkit/target/bukkit-VERSION-SNAPSHOT.jar` where version might be `1.8.7-R0.1`
for example.

You will then need to install this to a local maven repository, fortunately
this is very easy using the [localrepo plugin](https://github.com/kumarshantanu/lein-localrepo)

`$ lein localrepo install ../build-spigot/Bukkit/target/bukkit-VERSION-SNAPSHOT.jar org.bukkit/bukkit VERSION`

Then you should be able to add Bukkure as a dependency to your project.

## License

Copyright © 2015 Dominic Monroe

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

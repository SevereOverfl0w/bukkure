package cljbukkit;

import org.bukkit.plugin.java.JavaPlugin;
import clojure.java.api.Clojure;
import clojure.lang.IFn;
// Undocumented API imports
import clojure.lang.DynamicClassLoader;
// Imports for new Class loader
import java.security.AccessController;
import java.security.PrivilegedAction;
// Loading jars into classpath
import java.net.URL;
import java.net.MalformedURLException;

public class ClojurePlugin extends JavaPlugin {
  static {
    // Static initialization of ClojurePlugin
    ClassLoader bukkitCL = Thread.currentThread().getContextClassLoader();
    final ClassLoader ClojurePluginCL = ClojurePlugin.class.getClassLoader(); // Different from clojure class loader!
    Thread.currentThread().setContextClassLoader(ClojurePluginCL);

    try {
      /* This code sets the class loader of Clojure. It uses an undocumented API
       * and is very likely to break in the future. However, until such a time 
       * an API is provided, it's our only method :(
       *
       * It is required due to Bukkit changing the class loader, to load plugins.
      */
      DynamicClassLoader newCL = (DynamicClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
        @Override
        public Object run() {
          return new clojure.lang.DynamicClassLoader(ClojurePluginCL);
        }
      });

      // Init required to set clojure.lang.Compiler.LOADER a little earlier.
      clojure.lang.RT.init();
      // This changes the internal class loader of Clojure.
      clojure.lang.Var.pushThreadBindings(clojure.lang.RT.map(clojure.lang.Compiler.LOADER, newCL));
    } finally {
      // We must set the class loader back, else face serious consequences!
      Thread.currentThread().setContextClassLoader(bukkitCL);
    }
  }


  @Override
  public final void onEnable(){
    String pluginName = getDescription().getName();
    loadClojureNameSpace(pluginName + ".core");
    invokeClojureFunction(pluginName + ".core", "on-enable");
  }

  @Override
  public final void onDisable(){
    String pluginName = getDescription().getName();
    invokeClojureFunction(pluginName + ".core", "on-disable");
  }

  @Override
  public final void onLoad() {
    URL jarURL;
    try {
      jarURL = this.getFile().toURI().toURL();
    } catch (MalformedURLException e){
      return; // Necessary for toURL();
    }

    ((DynamicClassLoader) clojure.lang.Compiler.LOADER.deref()).addURL(jarURL);
  }

  public void loadClojureNameSpace(String ns){
    IFn require = Clojure.var("clojure.core", "require");
    Object cljFileRead = Clojure.read(ns);
    require.invoke(cljFileRead);
  }

  public Object invokeClojureFunction(String ns, String function){
    return Clojure.var(ns, function).invoke(this); // Call with plugin instance as the parameter
  }
}

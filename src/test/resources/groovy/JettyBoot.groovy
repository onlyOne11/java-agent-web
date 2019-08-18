
//groovy.grape.Grape.grab(group:'org.eclipse.jetty',module:'jetty-servlet',version:'9.4.12.RC2')
//groovy.grape.Grape.grab(group:'org.eclipse.jetty',module:'jetty-webapp',version:'9.4.12.RC2')
//@GrabResolver('http://nexus/content/repositories/central')
//groovy.grape.Grape.grab('org.eclipse.jetty:jetty-webapp:9.4.12.RC2')
//groovy.grape.Grape.grab('org.eclipse.jetty:jetty-servlet:9.4.12.RC2')
//groovy.grape.Grape.grab('javax.servlet:javax.servlet-api:3.1.0')
//groovy.grape.Grape.grab('javax.annotation:javax.annotation-api:1.3')
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.server.Server
import org.wt.JettyGroovyBootstrap

// 创建Server
def server = new Server(8080);
def cd = new File(System.getProperty("user.dir"))
def projectRootFile = cd
while(!new File(projectRootFile,"pom.xml").exists()){
    projectRootFile = projectRootFile.getParentFile()
}

def projectRoot = projectRootFile.getAbsolutePath()
def webContext = new WebAppContext("${projectRoot}/src/main/webapp", "/");
ClassLoader cl = new JettyGroovyBootstrap.MyClassLoader();
webContext.setClassLoader(cl)
//webContext.setDescriptor("${projectRoot}/src/main/webapp/WEB-INF/web.xml");
//webContext.setResourceBase("${projectRoot}/src/main/webapp");
//webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
//server.setHandler(webContext);
server.insertHandler(webContext);

/*def cl = org.wt.Dog.class.getClassLoader()
println cl
def cxtCl = Thread.currentThread().getContextClassLoader()
Thread.currentThread().setContextClassLoader(cxtCl)*/
/*
def c = Class.forName("org.wt.Dog",true,cl)
c.metaClass.invokeMethod = {
    name,args->
        println "invoke....................."
        name
}*/
server.stop();
server.start();
println "server start at port 8080"
server.join();


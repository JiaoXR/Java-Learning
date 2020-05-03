package com.jaxer.jvm.memory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17:59 2020-01-10
 * 1.8
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 * <p>
 * 1.7 -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 *
 * @author jiaoxiangru
 */
public class PermGenOOM2 {
    public static void main(String[] args) {

        URL url;
        List<ClassLoader> list = new ArrayList<ClassLoader>();

        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                list.add(loader);
                loader.loadClass("com.jaxer.com.jaxer.jvm.example.cglib.OOMObject");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

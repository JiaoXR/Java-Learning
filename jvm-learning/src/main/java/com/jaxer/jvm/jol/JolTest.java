package com.jaxer.jvm.jol;

import org.openjdk.jol.info.ClassLayout;

public class JolTest {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}

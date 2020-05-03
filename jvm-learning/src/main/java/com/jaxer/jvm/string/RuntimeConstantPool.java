package com.jaxer.jvm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16:22 2020-01-10
 * <p>
 * 1.8
 * -Xmx10m -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.Arrays.copyOf(Arrays.java:3332)
 * at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * <p>
 * 1.7
 * -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.Arrays.copyOf(Arrays.java:2367)
 *
 * @author jiaoxiangru
 */
public class RuntimeConstantPool {
	static String metaStr = "string";

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String str = metaStr + metaStr;
			metaStr = str;
			list.add(str.intern());
		}
	}
}

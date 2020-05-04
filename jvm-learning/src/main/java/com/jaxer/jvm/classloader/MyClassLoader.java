package com.jaxer.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jiaoxiangru on 15:04 2019-04-23
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = loadClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		}
		return defineClass(name, classData, 0, classData.length);
	}

	private byte[] loadClassData(String className) {
		try {
			String resource = this.getClass().getClassLoader().getResource("").getPath();
			System.out.println("resource=" + resource);
			String fileName = resource + File.separatorChar + className.replace('.', File.separatorChar) + ".class";

			FileInputStream inputStream = new FileInputStream(fileName);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
}

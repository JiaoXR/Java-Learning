package com.jaxer.jvm.local;

/**
 * Created by jaxer on 2020-03-25
 */
public class GCTest {
	public static void main(String[] args) {
		{
			byte[] bytes = new byte[64 * 1024 * 1024];
		}
		int a = 0;
		System.gc();
	}
}

package com.jaxer.jvm.compiler;

/**
 * 逃逸分析测试
 */
public class EscapeAnalysisTest {
    public static void main(String[] args) {
        EscapeAnalysisTest analysisTest = new EscapeAnalysisTest();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 2000_0000; i++) {
            analysisTest.allocate(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    private void allocate(int i) {
        new Person(i, "jack" + i);
    }

    private static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.name = name;
            this.id = id;
        }
    }
}

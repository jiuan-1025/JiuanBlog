package com.jiuan.blogTest;

import javafx.util.Builder;

/**
 * @Author: JiuAn
 * @Date: 2023-08-01-18:28
 * @Version 1.0 （版本号）
 * @Description:
 */
public class t1 {
        private static int staticField = 10;
        private int instanceField = 20;

        public static class StaticNestedClass implements Builder {
            private static int nestedStaticField = 30;

            public void printFields() {
                System.out.println("StaticNestedClass - staticField: " + staticField);
                System.out.println("StaticNestedClass - nestedStaticField: " + nestedStaticField);
//                 System.out.println("StaticNestedClass - instanceField: " + instanceField); // 无法访问外部类的实例字段
            }
            public void reset() {
                this.nestedStaticField = 30;
            }

            public void add() {
                this.nestedStaticField = 30+1;
            }
            @Override
            public Object build() {
                t1 t1 = new t1();

                return null;
            }
        }

        public static void main(String[] args) {
            t1.StaticNestedClass nested = new t1.StaticNestedClass();
            nested.printFields();
        }


}

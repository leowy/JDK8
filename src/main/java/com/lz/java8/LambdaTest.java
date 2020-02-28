package com.lz.java8;

/**
 *
 * @author Leowy Zhuang
 */
public class LambdaTest {

	public static void main(String[] args) {

		// 参数类型声明
		MathOperation add = (int a, int b) -> a + b;
		// 参数不声明类型
		MathOperation sub = (a, b) -> a - b;
		// 大括号带返回语句
		MathOperation mul = (int a, int b) -> { return a * b; };
		// 不带大括号以及返回语句
		MathOperation div = (a, b) -> a / b;

		System.out.println(add.operate(10, 5));
		System.out.println(sub.operate(10, 5));
		System.out.println(mul.operate(10, 5));
		System.out.println(div.operate(10, 5));
		

	}

	@FunctionalInterface
	interface MathOperation {
		int operate(int a, int b);
	}

}

package com.leo;

/**
 *
 * @author Leowy Zhuang
 */
public class LambdaTest {

	public static void main(String[] args) {

		LambdaTest lambdaTest = new LambdaTest();

		// 参数类型声明
		MathOperation add = (int a, int b) -> a + b;
		// 参数不声明类型
		MathOperation sub = (a, b) -> a - b;
		// 大括号带返回语句
		MathOperation mul = (int a, int b) -> { return a * b; };
		// 不带大括号以及返回语句
		MathOperation div = (a, b) -> a / b;

		System.out.println(lambdaTest.operate(10, 5, add));
		System.out.println(lambdaTest.operate(10, 5, sub));
		System.out.println(lambdaTest.operate(10, 5, mul));
		System.out.println(lambdaTest.operate(10, 5, div));

	}

	public int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operate(a, b);
	}

	@FunctionalInterface
	interface MathOperation {
		int operate(int a, int b);
	}

}

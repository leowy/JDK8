package com.lz.java8;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author Leowy Zhuang
 */
public class MethodReferenceTest {

	public static void main(String[] args) {

		// 构造器引用
		Supplier<Apple> sup1 = Apple::new;
		Apple apple = sup1.get();

		// 任意类型实例方法引用
		Consumer<Apple> sup2 = Apple::eat;
		sup2.accept(apple);

		// 静态方法引用
		Supplier<String> sup3 = Apple::value;
		sup3.get();

	}

}

class Apple {

	public Apple() {
		System.out.println("This is new apple");
	}

	public void eat() {
		System.out.println("It's delicious");
	}

	public static String value() {
		System.out.println("It's nutritional");
		return "It's nutritional";
	}

}

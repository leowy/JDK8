package com.leo;

import java.util.Optional;

/**
 *
 * @author Leowy Zhuang
 */
public class OptionalTest {

	public static void main(String[] args) {
		
		Student std = new Student("hello",8);
		
		//如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional
		Optional<Student> os = Optional.ofNullable(std);
		
		//如果值存在则使用该值调用 consumer , 否则不做任何事情
		os.ifPresent(System.out::println);
		
		//如果存在该值，返回值， 否则返回 other
		Student std2 = os.orElse(new Student("test",8));
		
		//返回一个指定非null值的Optional
		Optional<Student> os1 = Optional.of(std2);
		
		//filter:如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional
		//isPresent(): 如果值存在则方法会返回true，否则返回 false
		boolean result = os1.filter(x -> !x.getName().equals("test")).isPresent();
		System.out.println(result);
		
		//如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
		Student std3 = os1.get();
		System.out.println(std3);
		
		//判断其他对象是否等于 Optional
		boolean objRes = os.equals(Optional.ofNullable(std3));
		System.out.println(objRes);
		

	}

}

class Student {
	private String name;
	private int age;

	
	public Student() {
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

}

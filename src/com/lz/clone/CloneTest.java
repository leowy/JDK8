package com.lz.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 深拷贝实现方式
 *
 * @author Leowy Zhuang
 */
public class CloneTest {

	public static void main(String[] args) {

		// 浅拷贝
		StudentA stua = new StudentA("zhang2", 17, new School("上海第二中学", 2, "no2"));
		StudentA stuacopy = (StudentA) stua.clone();
		System.out.println("内存地址是否一致：" + (stua == stuacopy));
		System.out.println("内存地址是否一致：" + (stua.getSchool() == stuacopy.getSchool()));

		// 深拷贝(重写clone方法)
		StudentB stub = new StudentB("zhang1", 18, new School("上海第一中学", 3, "no1"));
		StudentB stubcopy = (StudentB) stub.clone();
		System.out.println("内存地址是否一致：" + (stub == stubcopy));
		System.out.println("内存地址是否一致：" + (stub.getSchool() == stubcopy.getSchool()));

		// 深拷贝(序列化)

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(stua);
			oos.close();

			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			StudentA stuacopy2 = (StudentA)ois.readObject();
			ois.close();
			System.out.println("内存地址是否一致：" + (stua == stuacopy2));
			System.out.println("内存地址是否一致：" + (stua.getSchool() == stuacopy2.getSchool()));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}

class StudentA implements Cloneable,Serializable {

	private static final long serialVersionUID = 4454712288965652921L;
	
	private String name;
	private int age;
	private School school;

	public StudentA(String name, int age, School school) {
		super();
		this.name = name;
		this.age = age;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public School getSchool() {
		return school;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}

class StudentB implements Cloneable {
	// 重写clone()方法

	private String name;
	private int age;
	private School school;

	public StudentB(String name, int age, School school) {
		super();
		this.name = name;
		this.age = age;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public School getSchool() {
		return school;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	protected Object clone() {

		try {
			StudentB stub = (StudentB) super.clone();
			stub.school = (School) school.clone();
			return stub;
		} catch (CloneNotSupportedException e) {
			return null;
		}

	}

}

class School implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 7780500938166989412L;
	
	private String name;
	private int grade;
	private String teacherName;

	public School(String name, int grade, String teacherName) {
		super();
		this.name = name;
		this.grade = grade;
		this.teacherName = teacherName;
	}

	public String getName() {
		return name;
	}

	public int getGrade() {
		return grade;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}

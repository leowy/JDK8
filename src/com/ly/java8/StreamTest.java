package com.ly.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**  
 *
 *  @author	Leowy Zhuang
 */
public class StreamTest {
	
	public static void main(String[] args) {
		
		List<Integer> number = Arrays.asList(9,0,8,1,7,2,6,3,5,4);
		List<Integer> number2 = Arrays.asList(1,4,4,4,6);
		
		// forEach 遍历流元素(对于并行，不保证流的执行顺序)
		System.out.println("forEach:");
		number.stream().forEach(System.out::println);
		
		// forEachOrdered 遍历流元素(无论顺序执行还是并行，能够保证顺序执行)
		System.out.println("forEachOrdered:");
		number.stream().forEachOrdered(System.out::println);
		
		// peek 遍历流元素 （与forEach区别，peek是中间操作 intermediate operations，forEach是终点操作 terminal operations）
		System.out.println("peek:");
		number.stream().peek(System.out::println).forEach(System.out::println);
		
		// filter 过滤元素
		System.out.println("filter:");
		number.stream().filter(i -> i > 5).forEach(System.out::println);
		
		// map 数据1：1转换
		System.out.println("map:");
		number.stream().map(i -> i * i).forEach(System.out::println);
		
		// flatmap 将映射后的流的元素全部放入到一个新的流中 ,主要是可以用来将stream层级扁平化
		System.out.println("flatmap:");
		Stream.of(number,number2).flatMap(i -> i.stream()).forEach(System.out::println);
		
		// findFirst 返回stream的第一个元素的Optional或为空。这是一个Terminal操作
		System.out.println("findFirst");
		Integer num = number.stream().findFirst().get();
		System.out.println(num);
		
		// findAny 返回任意一个元素，如果流为空，返回空的Optional，对于并行流来说，它只需要返回任意一个元素即可，
		//	所以性能可能要好于findFirst()，但是有可能多次执行的时候返回的结果不一样
		System.out.println("findAny");
		Integer num2 = number.stream().findAny().get();
		System.out.println(num2);
		
		// count 流元素数量
		System.out.println("count:");
		long count = number.stream().count();
		System.out.println(count);
		
		// sorted 排序
		System.out.println("sorted");
		number.stream().sorted().forEach(System.out::println);
		number.stream().sorted((s1,s2) -> s2.compareTo(s1)).forEach(System.out::println);
		
		// min/max
		System.out.println("min/max");
		Integer min = number.stream().min((s1,s2) -> s1.compareTo(s2)).get();
		Integer max = number.stream().max((s1,s2) -> s1.compareTo(s2)).get();
		System.out.println("min="+min+",max="+max);
		
		// limit 返回元素限制个数
		System.out.println("limit:");
		number.stream().limit(5).forEach(System.out::println);
		
		// distinct 去重
		System.out.println("distinct:");
		number2.stream().distinct().forEach(System.out::println);
		
		// skip 跳过元素个数
		System.out.println("skip:");
		number.stream().skip(5).forEach(System.out::println);
		
		// match 检查流中元素是否满足断言
		// allMatch ：只有在所有的元素都满足断言时才返回true,否则false,流为空时总是返回true
		// anyMatch ：只有在任意一个元素满足断言时就返回true,否则false
		// noneMatch：只有在所有的元素都不满足断言时才返回true,否则false
		System.out.println(number.stream().allMatch( i -> i > 5));
		System.out.println(number.stream().anyMatch( i -> i > 5 ));
		System.out.println(number.stream().noneMatch( i -> i > 5));
		
		// reduce 把Stream提供的多个元素归纳成一个对象
		System.out.println("reduce:");
		Integer sum = number.stream().reduce((a,b) -> a + b).get();
		System.out.println(sum);
		
		// toArray 流转数组
		Object[] nums = number.stream().toArray();
		Arrays.asList(nums).forEach(System.out::println);
		
		// concat 连接类型一样的两个流
		System.out.println("concat:");
		Stream.concat(number.stream(), number2.stream()).forEach(System.out::println);
		
		// collect (收集器)
		System.out.println("collect:");
		number.stream().collect(Collectors.toList()).forEach(System.out::println);
		String str = number.stream().map(String::valueOf).collect(Collectors.joining(","));
		System.out.println(str);
		
		IntSummaryStatistics stat = number.stream().collect(Collectors.summarizingInt(x -> x));
		System.out.println(stat);
		
	}

}

# Java8 新特性  
+ Lambda表达式
		
		可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
		可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
		可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
		可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
+ 方法引用
		
		方法引用通过方法的名字来指向一个方法。
		方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
		方法引用使用一对冒号 :: 。
		
+ 默认方法
		
		默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
		我们只需在方法名前面加个 default 关键字即可实现默认方法。

+ 函数式接口  
		
		Comsumer<T>			输入一个参数，返回void
		Function<T,R>		输入一个T类型，返回R类型
		Predicate<T>		输入一个T类型，返回boolean
		Supplier<T>			无输入，返回T类型
+ Stream
		
		数据源: 可以是集合，数组，I/O channel， 产生器generator 等。
		聚合操作:类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
		

   Stream操作还有两个基础的特征：

		Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。
		内部迭代： 对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。
				Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
				
+ Optional类
		
		Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
		Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
		Optional 类的引入很好的解决空指针异常。
		
+ Nashorn JavaScript引擎
+ 时间日期API
旧版的 Java 中，日期时间 API:  
		
		
		非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
		设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化
				和解析的类在java.text包中定义。  java.util.Date同时包含日期和时间，而java.sql.Date仅包
				含日期，将其纳入java.sql包并不合理。且这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
		时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和
				java.util.TimeZone类，但他们同样存在上述所有的问题。
		
java.time 包下提供了很多新的 API:  
	 							
		Local(本地) − 简化了日期时间的处理，没有时区的问题。
		Zoned(时区) − 通过制定的时区处理日期时间。		
+ Base64

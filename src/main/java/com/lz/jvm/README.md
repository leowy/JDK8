# Java虚拟机

![java内存结构](https://github.com/leowy/JVM/blob/master/res/JVM内存结构.png)  

### JVM 内存模型

-[Java虚拟机栈溢出](https://github.com/leowy/JVM/blob/master/src/com/leo/StackErrorMock.java)  
    
    虚拟机栈：每个线程有一个私有的栈，随着线程的创建而创建。栈里面存着的是一种叫“栈帧”的东西，每个方法会创建一个  
    栈帧，栈帧中存放了局部变量表（基本数据类型和对象引用）、操作数栈、方法出口等信息。栈的大小可以固定也可以动态  
    扩展。当栈调用深度大于JVM 所允许的范围，会抛出StackOverflowError的错误，不过这个深度范围不是一个恒定的值  
    
-[本地方法栈]()  
    
    这部分主要与虚拟机用到的 Native 方法相关  
    
-[PC 寄存器]()
    
	PC 寄存器，也叫程序计数器。JVM支持多个线程同时运行，每个线程都有自己的程序计数器。倘若当前执行的是 JVM  
    的方法，则该寄存器中保存当前执行指令的地址；倘若执行的是native 方法，则PC寄存器中为空  
    
-[堆]()
    
    堆内存是 JVM 所有线程共享的部分，在虚拟机启动的时候就已经创建。所有的对象和数组都在堆上进行分配。这部分空间  
    可通过 GC 进行回收。当申请不到空间时会抛出 OutOfMemoryError。  
    
-[方法区]()
    
    方法区也是所有线程共享。主要用于存储类的信息、常量池、方法数据、方法代码等。方法区逻辑上属于堆的一部分，但是  
    为了与堆进行区分，通常又叫“非堆”。  
    
### PermGen（永久代）

	绝大部分 Java 程序员应该都见过 "java.lang.OutOfMemoryError: PermGen space "这个异常。这里的  
	“PermGen space”其实指的就是方法区。不过方法区和“PermGen space”又有着本质的区别。前者是 JVM 的规  
	范，而后者则是 JVM 规范的一种实现，并且只有 HotSpot 才有 “PermGen space”，而对于其他类型的虚拟机，如  
	JRockit（Oracle）、J9（IBM） 并没有“PermGen space”。由于方法区主要存储类的相关信息，所以对于动态  
	生成类的情况比较容易出现永久代的内存溢出。最典型的场景就是，在 jsp 页面比较多的情况，容易出现永久代内存  
	溢出。
	
	
### Metaspace（元空间）
	
	其实，移除永久代的工作从JDK1.7就开始了。JDK1.7中，存储在永久代的部分数据就已经转移到了Java Heap或者  
	是 Native Heap。但永久代仍存在于JDK1.7中，并没完全移除，譬如符号引用(Symbols)转移到了native  
	heap；字面量(interned strings)转移到了java heap；类的静态变量(class statics)转移到了java heap。  
	
	
引用资料: [Java8内存模型—永久代(PermGen)和元空间(Metaspace)](https://www.cnblogs.com/paddix/p/5309550.html)

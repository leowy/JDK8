# cglib代理

### 原理
+ CGLIB是高效的代码生成包，底层依靠ASM（开源的java字节码编辑类库）操作字节码实现的
+ CGLIB动态生成的代理类会继承我们的业务类，并在代理类中对代理方法进行强化处理(前置处理、后置处理等)
+ 由于第二条，所以对于final类或final方法无法增强

### 实现
+ 实现方法拦截器MethodInterceptor
+ 通过Enhancer增强类继承业务类
+ 需要单方法增强需要实现回调过滤器CallbackFilter

### 注意
+ 无法代理final类
+ 无法代理final方法

# java动态代理
### 原理
+ 利用InvocationHandler拦截器
+ 使用反射机制生成一个实现匿名代理类

### 注意
+ 被代理类必须实现了接口

# java动态代理与cglib代理
### 区别
+ java动态代理需要被代理类实现接口
+ 被代理类为final类或者有final方法，则无法使用cglib
+ java使用的反射机制
+ cglib通过字节码底层继承代理类

### 效率
+ jdk1.6、jdk1.7 Java动态代理比cglib慢
+ jdk1.8 Java动态代理比cglib快
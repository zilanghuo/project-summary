# project-summary
个人总结

---
#### 类加载
1. what？
2. 类的加载在什么时候？
2. 类的生命周期？
3. JVM如何初始化？
4. 类加载器有哪些？
#####  什么是类的加载？
指的是将类的.Class文件的二进制数据读入到内存中，将其运行在数据区的方法区内，然后在堆区创建产生Class对象，用来封装在方法区内的数据结构。类的加载的最终产品是Class对象，并封装了类在方法区内的数据结构，并且提供方位方法区内的数据结构的接口。

##### 类的加载的时间点
允许类加载器预期某个类将要被使用时加载

##### 类的生命周期
正常流程：加载-->验证-->准备-->解析-->初始化

- 加载（双亲委派机制）：查找并加载类的二进制数据
- 验证：文件格式验证、元数据验证、字节码验证、符号引用
- 准备:为类的静态变量分配内存，并初始化为默认值（static）
1. 成员变量不需要赋值，局部变量需要赋值
2. 对static和final 同时申明的参数，必须显示申明
- 解析：将类的符号引用，转化为直接引用
- 初始化：为类的静态变量初始化值；初始化类的static代码块
- 结束生命周期：System.exit()；java进程销毁;程序结束

###### jvm 初始化的主动方式
- new 方式
- 访问某个类或某个接口的静态变量，或对静态变量进行赋值
- 调用类的静态方法
- 反射机制：Class.forName("com.Test")
- 初始化某个类的子类，会先初始化这个类的父类
- Java虚拟机启动时被标明为启动类的类（Java Test），直接使用java.exe命令来运行某个主类

---
#### 双亲委派机制

1.  什么是双亲委派机制？
2. 为什么会有双亲委派机制？
3. 双亲委派机制的用途？
***
##### 类加载器的三个类型

- 启动类加载器：Bootstrap ClassLoader,加载JDK/jre/lib 中的类
- 扩展类加载器：Extension ClassLoader,加载JDK/jre/lib/ext 中的类
- 应用类加载器：Application ClassLoader加载用户类路径（ClassPath）所指定的类

##### jvm 类加载机制
 - 全盘负责：当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另外一个类加载器来载入。
 
 - 父亲加载（双亲）：父亲加载该类，只有当父亲无法加载该类，才会尝试自己加载该类

 - 缓存机制：加载过的类，会被缓存起来，当程序使用到某个Class对象时，，如果缓存区不存在，才会读取该类的二进制流，转化为Class对象，进行缓存起来。所以，为什么修改类，修改重启JVM，才能生效。
 
##### 双亲委派机制
- 但一个类加载器收到加载类的请求时，该类加载器首先不会去加载，而是将请求委托给父类加载器，依次向上。所有的类加载请求，都会被委托给顶层类加载器（Boostrap ClassLoader）,只有当父类无法加载该类，子加载器才会去加载类（ClassNotFoundException）

Application ClassLoader -- > Ext ClassLoader -- > Bootstrap ClassLoader

##### 双亲委派的意义

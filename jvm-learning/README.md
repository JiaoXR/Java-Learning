# JVM实战相关

## 1. 常用参数

一些常用参数及说明：

| 参数                               | 说明                                                         |
| :--------------------------------- | :----------------------------------------------------------- |
| -XX:+UseSerialGC                   | 使用 Serial + Serial Old 的收集器组合进行内存回收            |
| -Xms20m                            | 堆空间初始容量为 20MB                                        |
| -Xmx20m                            | 堆空间最大容量为 20MB                                        |
| -Xmn10m                            | 堆中新生代容量为 10MB                                        |
| -XX:SurvivorRatio=8                | 新生代 Eden 占比为 8（两个 Survivor 分别为 1）               |
| -XX:+PrintGCDetails                | GC 时打印内存回收日志，并在进程退出时输出当前各个内存区域分配情况 |
| -verbose:gc                        | 输出每一个 GC 事件的信息                                     |
| -XX:PretenureSizeThreshold=3145728 | 指定老年代的阈值<br />（大于该值的对象直接在老年代分配，此处为 3MB） |
| -XX:MaxTenuringThreshold=1         | 对象晋升到老年代的年龄最大阈值为 1（默认是 15）              |
| -XX:+PrintTenuringDistribution     | 打印对象年龄信息                                             |

> 其他更多请参考官方文档：https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html

## 2. 性能监控与故障处理工具

### 2.1 JDK自带工具

| 名称   | 主要作用                                                     |
| ------ | ------------------------------------------------------------ |
| jps    | JVM Process Status Tool, 显示指定系统内所有的 HotSpot 虚拟机进程 |
| jstat  | JVM Statistics Monitoring Tool, 用于收集 HotSpot 虚拟机各方面的运行数据 |
| jinfo  | Configuration Info for Java, 显示虚拟机配置信息              |
| jmap   | Memory Map for Java, 生成虚拟机的内存转储快照（heapdump 文件） |
| jhat   | JVM Heap Analysis Tool, 用于分析 headdump 文件，它会建立一个 HTTP/HTML 服务器， 让用户可以再浏览器上查看分析结果 |
| jstack | Stack Trace for Java, 显示虚拟机的线程快照                   |

> 官方文档：https://docs.oracle.com/javase/8/docs/technotes/tools/unix/toc.html

#### 2.1.1 jps: 虚拟机进程状况工具

- 命令格式

```bash
jps [ options ] [ hostid ]
```

- jps

```bash
$ jps
15236 Jps
14966 Example1
```

- jps -l

```bash
$ jps -l
15249 sun.tools.jps.Jps
14966 com.jaxer.jvm.egs.Example1
```

- jps -m

```bash
$ jps -m
15264 Jps -m
14966 Example1
```

- jps -v

```bash
$ jps -v
14966 Example1 -Dvisualvm.id=44321340563858 -Xmx50m -Xms50m -XX:+PrintGCDetails -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=61849:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
15278 Jps -Dapplication.home=/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home -Xms8m
```

- jps -q

```bash
$ jps -q
9938
14966
15334
```

#### 2.1.2 jstat: 虚拟机统计信息监视工具

- 命令格式

```bash
jstat [option vmid [interval[s|ms] [count]] ]
```

- 示例 1：统计最近一次GC信息

```bash
$ jstat -gc 14966
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
2048.0 2048.0  0.0    0.0   12800.0   9345.8   34304.0    26638.8   5248.0 4971.3 640.0  554.9       2    0.032   2      0.049    0.082
```

如图所示：

![jstat_1](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jstat_1.png)

> S0: Survivor space 0 utilization as a percentage of the space's current capacity.
>
> S1: Survivor space 1 utilization as a percentage of the space's current capacity.
>
> E: Eden space utilization as a percentage of the space's current capacity.
>
> O: Old space utilization as a percentage of the space's current capacity.
>
> M: Metaspace utilization as a percentage of the space's current capacity.
>
> CCS: Compressed class space utilization as a percentage.
>
> YGC: Number of young generation GC events.
>
> YGCT: Young generation garbage collection time.
>
> FGC: Number of full GC events.
>
> FGCT: Full garbage collection time.
>
> GCT: Total garbage collection time.
>
> 官方文档：https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html#BEHHGFAE

- 示例 2：每隔一秒打印GC信息，输出10次

```bash
$ jstat -gc 14966 1000 10
```

如图所示：

![jstat_2](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jstat_2.png)

- 示例 3：查看类加载/卸载信息

```bash
$ jstat -class 14966
Loaded  Bytes  Unloaded  Bytes     Time
   829  1604.4        0     0.0       0.37
```

#### 2.1.3 jinfo: Java 配置信息工具

- 查看JVM启动参数

```bash
$ jinfo -flags 26472
VM Flags:
-XX:CICompilerCount=3 -XX:InitialHeapSize=52428800 -XX:MaxHeapSize=52428800 -XX:MaxNewSize=17301504 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=17301504 -XX:OldSize=35127296 -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC
```

此处程序运行在 JDK 8 环境下，执行 jinfo 命令的 JDK 版本是 11，分别如下：

```bash
$ java -version
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

```bash
$ java -version
java version "11.0.5" 2019-10-15 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.5+10-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.5+10-LTS, mixed mode)
```

原因是 JDK 8 中使用该命令会抛出异常：

```bash
$ jinfo -flags 26284
Attaching to process ID 26284, please wait...
Error attaching to process: sun.jvm.hotspot.debugger.DebuggerException: Can't attach symbolicator to the process
sun.jvm.hotspot.debugger.DebuggerException: sun.jvm.hotspot.debugger.DebuggerException: Can't attach symbolicator to the process
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal$BsdDebuggerLocalWorkerThread.execute(BsdDebuggerLocal.java:169)
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal.attach(BsdDebuggerLocal.java:287)
	at sun.jvm.hotspot.HotSpotAgent.attachDebugger(HotSpotAgent.java:671)
	at sun.jvm.hotspot.HotSpotAgent.setupDebuggerDarwin(HotSpotAgent.java:659)
	at sun.jvm.hotspot.HotSpotAgent.setupDebugger(HotSpotAgent.java:341)
	at sun.jvm.hotspot.HotSpotAgent.go(HotSpotAgent.java:304)
	at sun.jvm.hotspot.HotSpotAgent.attach(HotSpotAgent.java:140)
	at sun.jvm.hotspot.tools.Tool.start(Tool.java:185)
	at sun.jvm.hotspot.tools.Tool.execute(Tool.java:118)
	at sun.jvm.hotspot.tools.JInfo.main(JInfo.java:138)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.tools.jinfo.JInfo.runTool(JInfo.java:108)
	at sun.tools.jinfo.JInfo.main(JInfo.java:76)
Caused by: sun.jvm.hotspot.debugger.DebuggerException: Can't attach symbolicator to the process
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal.attach0(Native Method)
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal.access$100(BsdDebuggerLocal.java:65)
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal$1AttachTask.doit(BsdDebuggerLocal.java:278)
	at sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal$BsdDebuggerLocalWorkerThread.run(BsdDebuggerLocal.java:144)
```

查资料说是 JVM 的 bug（链接：https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8160376 ），在 JDK 9 b129 修复了。

#### 2.1.4 jstack: Java 堆栈跟踪工具

- jstack (Stack Trace for Java) 命令用于生成虚拟机当前时刻的线程快照（一般称为 threaddump 或 javacore 文件）。
- 线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合，生成堆栈快照的主要目的是定位线程出现长时间停顿的原因，如线程死锁、死循环、请求外部资源导致的长时间等待等都是导致线程长时间停顿的常见原因。

```bash
$ jstack -l 26472 | more
```

如图所示：

![jstack](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jstack.png)

#### 2.1.5 jmap: Java 内存映像工具

```bash
$ jmap -dump:live,format=b,file=/Users/jaxer/Desktop/dump.hprof 26472
Dumping heap to /Users/jaxer/Desktop/dump.hprof ...
Heap dump file created
```

可以在对应路径下看到堆转储快照文件 dump.hprof。导出来之后，就可以用其他工具对堆栈快照进行分析了。

#### 2.1.6 jhat: 堆转储快照分析工具

分析 jmap 生成的堆栈文件：

```bash
$ jhat dump.hprof
Reading from dump.hprof...
Dump file created Sun May 03 17:09:07 CST 2020
Snapshot read, resolving...
Resolving 42293 objects...
Chasing references, expect 8 dots........
Eliminating duplicate references........
Snapshot resolved.
Started HTTP server on port 7000
Server is ready.
```

Server 启动后，浏览器打开 http://localhost:7000/，可以看到如下信息：

![jhat](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jhat.png)

> 实际工作中，一般不会直接使用 jhat 命令来分析 dump 文件，主要原因：
>
> 1. 一般不会在部署应用程序的服务器上直接分析 dump 文件（分析工作一般比较耗时，而且消耗硬件资源，在其他机器上进行时则没必要受到命令行工具的限制）；
> 2. jhat 分析功能相对简陋，VisualVM、Eclipse Memory Analyzer、IBM HeapAnalyzer 等更工具功能强大。

#### 2.1.7 jvisualvm & VisualVM: 堆转储快照分析工具

jvisualvm 也是 JDK 自带的命令，虽然后面独立发展了。这两种方式都可以使用。

> VisualVM 链接：https://visualvm.github.io/

使用 VisualVM 分析上面 jmap 导出的堆栈转储文件，导入后如下：

- 概览

![visualvm_1](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/visualvm_1.png)

- 对象信息

![visualvm_2](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/visualvm_2.png)

- 线程信息

![visualvm_3](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/visualvm_3.png)

这个工具使用起来比 jhat 舒服多了。

#### 2.1.8 jconsole: JVM 性能监控

使用 jconsole 命令可以启动客户端，如下：

![jconsole_1](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jconsole_1.png)

可以选择本地或者远程的 JVM 进程进行连接。

> 本人测试连接时报错，描述及解决方案详见：https://blog.csdn.net/u010551118/article/details/105907403

连接成功之后如下：

- 概览

![jconsole_2](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jconsole_2.png)

- 死锁检测

![jconsole_3](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/jconsole_3.png)

除了上面 JDK 自带的工具，还有个很好用的阿里出品的 Arthas。

### 2.2 Arthas

> 官方文档：https://alibaba.github.io/arthas/  https://alibaba.github.io/arthas/en/
>

#### 2.2.0 准备代码

为了演示部分功能，这里准备了一些简单的示例代码：

- Hello.java

```java
package com.jaxer.jvm.arthas;

import java.time.LocalDateTime;

public class Hello {
	public void sayHello() {
		System.out.println(LocalDateTime.now() + " hello");
	}
}
```

- ArthasTest.java

```java
package com.jaxer.jvm.arthas;

import java.util.concurrent.TimeUnit;

public class ArthasTest {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			TimeUnit.SECONDS.sleep(5);
			new Hello().sayHello();
		}
	}
}
```

代码比较简单，这里不再赘述。

#### 2.2.1 启动 Arthas

Arthas 其实是一个 jar 包，下载后运行：

```bash
$ java -jar arthas-boot.jar
```

启动成功后：

![arthas_boot_1](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_boot_1.png)

Arthas 会检测本地 JVM 进程并列出来（参见上面的 jps 命令），选择前面的序号就能进入对应的进程分析。这里选择 1，然后回车：

![arthas_boot_2](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_boot_2.png)

这样就成功连接到了该进程。接下来就可以执行各种命令来分析 JVM 了。

#### 2.2.2 help

使用 help 命令可以看到 Arthas 的命令概览：

```bash
[arthas@36934]$ help
 NAME         DESCRIPTION
 help         Display Arthas Help
 keymap       Display all the available keymap for the specified connection.
 sc           Search all the classes loaded by JVM
 sm           Search the method of classes loaded by JVM
 classloader  Show classloader info
 jad          Decompile class
 getstatic    Show the static field of a class
 monitor      Monitor method execution statistics, e.g. total/success/failure count, average rt, fail rate, etc.
 stack        Display the stack trace for the specified class and method
 thread       Display thread info, thread stack
 trace        Trace the execution time of specified method invocation.
 watch        Display the input/output parameter, return object, and thrown exception of specified method invocation
 tt           Time Tunnel
 jvm          Display the target JVM information
 perfcounter  Display the perf counter infornation.
 ognl         Execute ognl expression.
 mc           Memory compiler, compiles java files into bytecode and class files in memory.
 redefine     Redefine classes. @see Instrumentation#redefineClasses(ClassDefinition...)
 dashboard    Overview of target jvm's thread, memory, gc, vm, tomcat info.
 dump         Dump class byte array from JVM
 heapdump     Heap dump
 options      View and change various Arthas options
 cls          Clear the screen
 reset        Reset all the enhanced classes
 version      Display Arthas version
 session      Display current session information
 sysprop      Display, and change the system properties.
 sysenv       Display the system env.
 vmoption     Display, and update the vm diagnostic options.
 logger       Print logger info, and update the logger level
 history      Display command history
 cat          Concatenate and print files
 echo         write arguments to the standard output
 pwd          Return working directory name
 mbean        Display the mbean information
 grep         grep command for pipes.
 tee          tee command for pipes.
 profiler     Async Profiler. https://github.com/jvm-profiling-tools/async-profiler
 stop         Stop/Shutdown Arthas server and exit the console.
```

此外，还可以查看每个命令的解释及用法。命令后面加 -help，例如：

```bash
[arthas@36934]$ help -help
 USAGE:
   help [-h] [cmd]

 SUMMARY:
   Display Arthas Help
 Examples:
  help
  help sc
  help sm
  help watch

 OPTIONS:
 -h, --help                                      this help
 <cmd>                                           command name
```

#### 2.2.3 dashboard

dashboard 命令可以总览 JVM 状况（默认 5 秒刷新一次）：

![arthas_dashboard](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_dashboard.png)

#### 2.2.4 jvm

jvm 可以查看当前 JVM 的运行时信息，比如机器信息、JVM 版本、启动参数、ClassPath：

![arthas_jvm_1](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_jvm_1.png)

还有类加载信息、编译信息、垃圾收集器、内存相关信息：

![arthas_jvm_2](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_jvm_2.png)

以及操作系统信息、线程等：

![arthas_jvm_3](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_jvm_3.png)

#### 2.2.5 thread

线程信息、线程堆栈：

![arthas_thread](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_thread.png)

#### 2.2.6 sc & sm

sc 可以查看类加载信息：

```bash
[arthas@36934]$ sc com.jaxer.*
com.jaxer.jvm.arthas.ArthasTest
com.jaxer.jvm.arthas.Hello
Affect(row-cnt:2) cost in 36 ms.
```

sm 可以查看类的方法信息：

```bash
[arthas@36934]$ sm com.jaxer.jvm.arthas.ArthasTest
com.jaxer.jvm.arthas.ArthasTest <init>()V
com.jaxer.jvm.arthas.ArthasTest main([Ljava/lang/String;)V
Affect(row-cnt:2) cost in 14 ms.
```

#### 2.2.7 jad

jad 可以对一个类进行反编译，例如：

```bash
[arthas@36934]$ jad com.jaxer.jvm.arthas.Hello

ClassLoader:
+-sun.misc.Launcher$AppClassLoader@18b4aac2
  +-sun.misc.Launcher$ExtClassLoader@14612fee

Location:
/Users/jaxer/GitHub-JiaoXR/Java-Learning/jvm-learning/target/classes/

/*
 * Decompiled with CFR.
 */
package com.jaxer.jvm.arthas;

import java.time.LocalDateTime;

public class Hello {
    public void sayHello() {
        System.out.println(LocalDateTime.now() + " hello");
    }
}

Affect(row-cnt:1) cost in 260 ms.
```

#### 2.2.8 redefine

redefine 就是热部署，通俗来讲就是「开着飞机换引擎」。比如上述代码的运行结果是：

```bash
2020-05-04T16:07:04.242 hello
2020-05-04T16:07:09.247 hello
...
```

在不停止该程序的情况下，可以改变输出内容吗？答案是肯定的！

怎么做呢？

首先在本地新建一个与 Hello.java 内容完全一样的文件（名字也完全一样），然后修改方法内容，新的 Hello.java 文件内容如下：

```java
package com.jaxer.jvm.arthas;

import java.time.LocalDateTime;

public class Hello {
	public void sayHello() {
		System.out.println(LocalDateTime.now() + " I'm hungry");
	}
}
```

然后在本地执行 javac 将其编译为 class 文件（此处的路径为 /Users/jaxer/Desktop/Hello.class），然后运行 redefine 命令如下：

```bash
[arthas@36934]$ redefine /Users/jaxer/Desktop/Hello.class
redefine success, size: 1
```

这时候去观察输出结果，神奇的事情发生了：

![arthas_redefine](https://github.com/JiaoXR/Java-Learning/blob/master/jvm-learning/pics/arthas/arthas_redefine.png)

> 注意：直到现在，原先的程序还是一直在运行的，并没有停下来。

有木有开着飞机换引擎的感觉？nubility！



其他还有很多命令，小伙伴们可以自己尝试。不知道怎么用时别忘了 -help。


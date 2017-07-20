<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#org34ca4b8">1. 前言</a></li>
<li><a href="#orgbb6a9ba">2. 环境搭建步骤</a></li>
<li><a href="#org2331959">3. POM文件修改</a></li>
<li><a href="#orga664d2d">4. 坑一： 运行了zookeeper启动命令，但是状态却是没有启动</a>
<ul>
<li><a href="#org4de5227">4.1. 解决方法：使用前置启动：sh zkServer.sh start-foreground</a></li>
</ul>
</li>
<li><a href="#orgae19e86">5. 坑二： ModuleConfig.module为null</a>
<ul>
<li><a href="#org0577042">5.1. 解决方法：在application.properties当中添加spring.dubbo.module.default=false</a></li>
</ul>
</li>
<li><a href="#org9cb58a5">6. 坑三：启动Client报错</a>
<ul>
<li><a href="#org30f1d39">6.1. 解决方法：查看服务器端发布服务的地址是否可能ping通；若ping不通，在application.properties当中添加spring.dubbo.protocol.host=&lt;你本机IP&gt;，重启server</a></li>
</ul>
</li>
</ul>
</div>
</div>


<a id="org34ca4b8"></a>

# 前言

   最近比较寂寞，想学dubbo，又不想按照官网那种方式进行配置，想集成于spring boot；所以上网找了一下有没有spring boot dubbo这样的玩意；于是就发现了[spring-boot-starter-dubbo](https://github.com/teaey/spring-boot-starter-dubbo)  并且还有一个[spring-boot-starter-dubbo-sample](https://github.com/teaey/spring-boot-starter-dubbo-sample);
欣喜若狂地下载下来，竟然启动不起来；于是改巴改巴，将坑都给填填


<a id="orgbb6a9ba"></a>

# 环境搭建步骤

-   下载zookeeper，解压并启动
-   下载sample代码，分别启动server和client
-   启动client成功后，client和server和console里面分别打印abccc即可成功


<a id="org2331959"></a>

# POM文件修改

-   pom文件里面的spring boot starter-parent的1.3.6.RELEASE版本号在maven中央仓库里面没有，改成了1.3.5.RELEASE
-   pom文件里面的spring-boot-starter-dubbo的1.0.0-SNAPSHOT在maven中央仓库里面也没有，改成了1.0.0


<a id="orga664d2d"></a>

# 坑一： 运行了zookeeper启动命令，但是状态却是没有启动

    ➜  bin sh zkServer.sh start
    ZooKeeper JMX enabled by default
    Using config: /Users/admin/projects/zookeeper-3.4.8/bin/../conf/zoo.cfg
    -n Starting zookeeper ...
    STARTED
    ➜  bin sh zkServer.sh status
    ZooKeeper JMX enabled by default
    Using config: /Users/admin/projects/zookeeper-3.4.8/bin/../conf/zoo.cfg
    Error contacting service. It is probably not running.


<a id="org4de5227"></a>

## 解决方法：使用前置启动：sh zkServer.sh start-foreground

    ➜  bin sh zkServer.sh start-foreground
    ZooKeeper JMX enabled by default
    Using config: /Users/admin/projects/zookeeper-3.4.8/bin/../conf/zoo.cfg
    2017-07-20 11:23:58,873 [myid:] - INFO  [main:QuorumPeerConfig@103] - Reading configuration from: /Users/admin/projects/zookeeper-3.4.8/bin/../conf/zoo.cfg
    2017-07-20 11:23:58,877 [myid:] - INFO  [main:DatadirCleanupManager@78] - autopurge.snapRetainCount set to 3
    2017-07-20 11:23:58,878 [myid:] - INFO  [main:DatadirCleanupManager@79] - autopurge.purgeInterval set to 0
    2017-07-20 11:23:58,878 [myid:] - INFO  [main:DatadirCleanupManager@101] - Purge task is not scheduled.
    2017-07-20 11:23:58,878 [myid:] - WARN  [main:QuorumPeerMain@113] - Either no config or no quorum defined in config, running  in standalone mode
    2017-07-20 11:23:58,890 [myid:] - INFO  [main:QuorumPeerConfig@103] - Reading configuration from: /Users/admin/projects/zookeeper-3.4.8/bin/../conf/zoo.cfg
    2017-07-20 11:23:58,890 [myid:] - INFO  [main:ZooKeeperServerMain@95] - Starting server
    2017-07-20 11:24:03,905 [myid:] - INFO  [main:Environment@100] - Server environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
    2017-07-20 11:24:03,905 [myid:] - INFO  [main:Environment@100] - Server environment:host.name=localhost
    2017-07-20 11:24:03,906 [myid:] - INFO  [main:Environment@100] - Server environment:java.version=1.7.0_79
    2017-07-20 11:24:03,906 [myid:] - INFO  [main:Environment@100] - Server environment:java.vendor=Oracle Corporation
    2017-07-20 11:24:03,906 [myid:] - INFO  [main:Environment@100] - Server environment:java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home/jre
    2017-07-20 11:24:03,906 [myid:] - INFO  [main:Environment@100] - Server environment:java.class.path=/Users/admin/projects/zookeeper-3.4.8/bin/../build/classes:/Users/admin/projects/zookeeper-3.4.8/bin/../build/lib/*.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../lib/slf4j-log4j12-1.6.1.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../lib/slf4j-api-1.6.1.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../lib/netty-3.7.0.Final.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../lib/log4j-1.2.16.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../lib/jline-0.9.94.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../zookeeper-3.4.8.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../src/java/lib/*.jar:/Users/admin/projects/zookeeper-3.4.8/bin/../conf:
    2017-07-20 11:24:03,907 [myid:] - INFO  [main:Environment@100] - Server environment:java.library.path=/Users/admin/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
    2017-07-20 11:24:03,907 [myid:] - INFO  [main:Environment@100] - Server environment:java.io.tmpdir=/var/folders/5k/92yp5fn10cg0dw2gzyrjynqh0000gn/T/
    2017-07-20 11:24:03,907 [myid:] - INFO  [main:Environment@100] - Server environment:java.compiler=<NA>
    2017-07-20 11:24:03,909 [myid:] - INFO  [main:Environment@100] - Server environment:os.name=Mac OS X
    2017-07-20 11:24:03,909 [myid:] - INFO  [main:Environment@100] - Server environment:os.arch=x86_64
    2017-07-20 11:24:03,910 [myid:] - INFO  [main:Environment@100] - Server environment:os.version=10.12
    2017-07-20 11:24:03,910 [myid:] - INFO  [main:Environment@100] - Server environment:user.name=admin
    2017-07-20 11:24:03,910 [myid:] - INFO  [main:Environment@100] - Server environment:user.home=/Users/admin
    2017-07-20 11:24:03,910 [myid:] - INFO  [main:Environment@100] - Server environment:user.dir=/Users/admin/projects/zookeeper-3.4.8/bin
    2017-07-20 11:24:03,917 [myid:] - INFO  [main:ZooKeeperServer@787] - tickTime set to 2000
    2017-07-20 11:24:03,917 [myid:] - INFO  [main:ZooKeeperServer@796] - minSessionTimeout set to -1
    2017-07-20 11:24:03,917 [myid:] - INFO  [main:ZooKeeperServer@805] - maxSessionTimeout set to -1
    2017-07-20 11:24:03,936 [myid:] - INFO  [main:NIOServerCnxnFactory@89] - binding to port 0.0.0.0/0.0.0.0:2181


<a id="orgae19e86"></a>

# 坑二： ModuleConfig.module为null

    Caused by: java.lang.IllegalStateException: ModuleConfig.module == null
      at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:282) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:217) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.ServiceConfig.doExportUrlsFor1Protocol(ServiceConfig.java:357) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.ServiceConfig.doExportUrls(ServiceConfig.java:281) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.ServiceConfig.doExport(ServiceConfig.java:242) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.ServiceConfig.export(ServiceConfig.java:143) ~[dubbo-2.5.3.jar:2.5.3]
      at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessAfterInitialization(AnnotationBean.java:195) ~[dubbo-2.5.3.jar:2.5.3]
      at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(AbstractAutowireCapableBeanFactory.java:422) ~[spring-beans-4.2.4.RELEASE.jar:4.2.4.RELEASE]
      at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1583) ~[spring-beans-4.2.4.RELEASE.jar:4.2.4.RELEASE]
      at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545) ~[spring-beans-4.2.4.RELEASE.jar:4.2.4.RELEASE]
      ... 14 common frames omitted
    Caused by: java.lang.IllegalStateException: ModuleConfig.module == null
      at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:267) ~[dubbo-2.5.3.jar:2.5.3]
      ... 23 common frames omitted


<a id="org0577042"></a>

## 解决方法：在application.properties当中添加spring.dubbo.module.default=false


<a id="org9cb58a5"></a>

# 坑三：启动Client报错

    failed to connect to server /10.3.52.85:20880, error message is:connection timed out


<a id="org30f1d39"></a>

## 解决方法：查看服务器端发布服务的地址是否可能ping通；若ping不通，在application.properties当中添加spring.dubbo.protocol.host=<你本机IP>，重启server


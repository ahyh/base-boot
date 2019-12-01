NIO概念：
NIO: No-Blocking IO, 同步非阻塞IO
NIO的三大核心组件：Channel、Buffer、Selector
NIO是面向缓冲区或者面向块编程的，数据读取到它稍后处理的缓冲区，需要时可在缓冲区中前后移动，这就增加了处理过程中的灵活性，使用NIO可以提供非阻塞式的高伸缩性的网络

BIO与NIO的区别：
1-BIO以流的方式处理数据，NIO以块的方式处理数据，块IO的效率比流IO的效率高很多
2-BIO是阻塞的，NIO则是非阻塞的
3-BIO基于字节流的字符流进行操作，而NIO基于Channel和Buffer进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入通道，Selector用于监听多个通道的事件，使用单个线程就可以监听多个客户端通道

Channel和Buffer的关系
1-每个Channel都对应一个Buffer
2-每个Selector对应一个线程，一个线程对应多个Channel(连接)
3-程序切换到哪个Channel是由事件决定的，Event(事件)
4-Selector会根据不同的事件，在Channel之间切换
5-Buffer的本质是一个内存块，底层是一个数组，数据的读取和写入是通过Buffer进行的，Buffer可读可写，flip切换读写状态
6-Channel是双向的，可以返回底层操作系统的情况，底层操作系统的通道就是双向的


Buffer的属性
capacity：容量，即可以容纳的最大数据量，在缓冲区创建时被设定且不能改变
limit：缓冲区当前终点，不能对缓冲区超过极限的位置进行读写操作，且limit值是可以修改的
position：位置，下一个要被读/写的元素的索引，每次读写缓冲区数据时都会改变的值，为下次读写做准备
mark：标记
mark<=position<=limit<=capacity


Channel的属性
1-Channel可以同时进行读写，而流只能读或者只能写
2-通道可以实现异步读写数据
3-通道可以从缓冲读数据，也可以写数据到缓冲


Selector的属性
1-Selector可以检测多个注册的通道上是否有事件发生，多个Channel可以以事件的方式注册到同一个Selector上，如果有事件发生便可以获取事件然后针对每一个事件进行相应的处理
2-只有在Channel真正有读写事件发生的时候才真正进行IO操作


NIO非阻塞网络编程原理
1-当客户端连接时，会通过ServerSocketChannel得到SocketChannel
1.1-selector开始监听
2-将SocketChannel注册到Selector上，register(Selector,ops)，一个Selector上可以注册多个Channel
3-注册后返回一个SelectionKey，会和该Selector关联（集合）
4-Selector进行监听 select()方法返回有事件发生的通道的个数
5-进一步得到各个SelectionKey(有事件发生)
6-在通过SelectionKey反向获取SocketChannel channel()
7-可以通过得到的channel完成业务处理

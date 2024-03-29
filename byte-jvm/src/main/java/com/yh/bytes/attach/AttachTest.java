package com.yh.bytes.attach;

import com.sun.tools.attach.VirtualMachine;

import java.util.concurrent.TimeUnit;

/**
 * attach客户端
 */
public class AttachTest {

    /**
     * 调用attach API，connect到目标JVM，然后loadAgent来动态加载agent jar包从而完成更新运行中的JVM已加载class
     * 字节码的功能
     */
    public static void main(String[] args) throws Exception {
        // 每次运行之前都要通过jps获取到pid
        VirtualMachine virtualMachine = VirtualMachine.attach("2128");
        // TimeUnit.SECONDS.sleep(10);
        // 需要替换成自己的jar地址
        virtualMachine.loadAgent("D:\\myCode\\base-boot\\byte-jvm\\target\\byte-jvm-jar-with-dependencies.jar");
    }

}

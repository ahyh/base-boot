package com.yh.bytes.attach;


import com.sun.tools.attach.VirtualMachine;


public class AttachTest {

    public static void main(String[] args) throws Exception {
        // 每次运行之前都要通过jps获取到pid
        VirtualMachine virtualMachine = VirtualMachine.attach("63020");
        // 需要替换成自己的jar地址
        virtualMachine.loadAgent("D:\\myCode\\base-boot\\byte-jvm\\target\\byte-jvm-jar-with-dependencies.jar");
    }

}

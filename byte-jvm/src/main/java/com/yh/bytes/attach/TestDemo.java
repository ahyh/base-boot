package com.yh.bytes.attach;

public class TestDemo {

    public static void main(String[] args) throws Exception{
        while(true){
            Thread.sleep(5000);
            testMethod();
        }
    }

    public static void testMethod(){
        System.out.println("test method");
    }
}

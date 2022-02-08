package com.yh.bytes.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * 通过Instrumentation方式的Agent
 *
 * @author yanhuan
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) throws Throwable{
        System.out.println("loading instrumentation");
        MyTransformer myTransformer = new MyTransformer();
        instrumentation.addTransformer(myTransformer);
    }
}

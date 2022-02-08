package com.yh.bytes.attach;

import java.lang.instrument.Instrumentation;

public class MyAttachAgent {

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("Loading attach agent......");

        MyAttachTransformer myAttachTransformer = new MyAttachTransformer();

        instrumentation.addTransformer(myAttachTransformer, true);

        if (instrumentation.isRedefineClassesSupported()) {
            Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
            for (Class<?> clazz : allLoadedClasses) {
                if (clazz.getName().contains("TestDemo")) {
                    try {
                        instrumentation.retransformClasses(clazz);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

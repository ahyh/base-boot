package com.yh.bytes.bytekit;

import com.alibaba.bytekit.utils.Decompiler;

public class AtExceptionExitTest {

    public static void main(String[] args) throws Exception {
        // 指定Interceptor和目标方法, 设置reTransform为true
        ByteKitHelper helper = ByteKitHelper.builder().interceptorClass(ExceptionExitInterceptor.class).methodMatcher("testThrowException")
                .reTransform(true);

        // 处理Sample类
        byte[] bytes = helper.process(Sample.class);

        // 调用增强后的方法
        new Sample().testThrowException(null);

        // 查看增强后的字节码
        System.err.println(Decompiler.decompile(bytes));
    }
}

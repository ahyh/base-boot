package com.yh.zk.jute;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * 序列化组件Jute测试
 *
 * @author yanhuan
 */
public class JuteMain {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
        //将对象序列化到tag中
        new MyRequest(234L, "yanhuan").serialize(boa, "header");
        ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());
        ByteBufferInputStream bbis = new ByteBufferInputStream(buffer);
        BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbis);
        MyRequest myRequest = new MyRequest();
        //从tag中反序列化
        myRequest.deserialize(bbia, "header");
        bbis.close();
        baos.close();
    }
}

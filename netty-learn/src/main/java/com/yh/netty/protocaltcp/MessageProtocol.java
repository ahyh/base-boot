package com.yh.netty.protocaltcp;

/**
 * 协议包，解决粘包拆包问题
 *
 * @author yanhuan
 */
public class MessageProtocol {

    private int len;

    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

package com.yh.boot.boot.mail.test;

import com.yh.boot.boot.mail.BootMailApplicationTests;
import com.yh.boot.boot.mail.service.EmailService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JavaMailTest extends BootMailApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testMailSend(){
        String htmlStr = "<table class=\"t1\">\n" +
                "<tbody>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p4\">\n" +
                "<span class=\"s1\">案由</span>\n" +
                "</p>\n" +
                "</td><td class=\"td2\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">民间借贷纠纷</span>\n" +
                "</p>\n" +
                "</td><td class=\"td3\">\n" +
                "<p class=\"p4\">\n" +
                "<span class=\"s1\">案号</span>\n" +
                "</p>\n" +
                "</td><td class=\"td4\">\n" +
                "<p class=\"p6\">\n" +
                "<span class=\"s1\">(2014)虹民一(民)初字第462号</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">送达文书</span>\n" +
                "</p>\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">名称和件数</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">民事判决书</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">受送达人</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p4\">\n" +
                "<span class=\"s1\">赵传文</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">送达地址</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p7\">\n" +
                "<span class=\"s1\">上海市奉贤区金田路47弄35号</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p4\">\n" +
                "<span class=\"s1\">受送达人</span>\n" +
                "</p>\n" +
                "<p class=\"p4\">\n" +
                "<span class=\"s1\">签名或盖章</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">年月日</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">代收人及</span>\n" +
                "</p>\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">代收理由</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">年月日</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"r1\">\n" +
                "<td class=\"td1\">\n" +
                "<p class=\"p5\">\n" +
                "<span class=\"s1\">备考</span>\n" +
                "</p>\n" +
                "</td><td class=\"td5\" colspan=\"3\">\n" +
                "<p class=\"p8\">\n" +
                "<span class=\"s1\">见41-42公送达材料。</span>\n" +
                "</p>\n" +
                "<p class=\"p8\">\n" +
                "<span class=\"s1\">现20年月8日</span>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";
        emailService.sendEmail("huanyan2@iflytek.com","Test Mail",htmlStr);
        Assert.assertTrue(true);
    }
}

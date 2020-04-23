package com.xxq.filemanager.util;

import com.sun.mail.util.MailSSLSocketFactory;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/**
 * @ClassName :MailUtil
 * @Description:
 * @Author xxq
 * @Date 2020/4/9  19:50
 * @Version V1.0
 **/
public class MailUtil {
    /**
     *
     *
     * @param receiverMail 管理员的邮箱
     * @param lender 借阅者的姓名
     */
    public static void sendMail(String receiverMail,String lender) throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp"); // 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com"); // 主机名
        properties.put("mail.smtp.port", 465);  // 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("916885684@qq.com"));
        // 设置收件人地址
        message.setRecipients( RecipientType.TO, new InternetAddress[] { new InternetAddress(receiverMail) });
        // 设置邮件标题
        message.setSubject("由用户"+lender+"发出的借阅邮件");
        // 设置邮件内容
        message.setText("内容为：有新的借阅请求。");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("916885684@qq.com", "jodfgikgmgppbcaf");// 密码为刚才得到的授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }
    public static void sendAlarmMail() throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp"); // 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com"); // 主机名
        properties.put("mail.smtp.port", 465);  // 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("916885684@qq.com"));
        // 设置收件人地址
        message.setRecipients( RecipientType.TO, new InternetAddress[] { new InternetAddress("728832542@qq.com") });
        // 设置邮件标题
        message.setSubject("借阅提醒邮件");
        // 设置邮件内容
        message.setText("内容为：请尽快归还档案。");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("916885684@qq.com", "jodfgikgmgppbcaf");// 密码为刚才得到的授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }


    public  static void backMail(String receiverMail) throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp"); // 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com"); // 主机名
        properties.put("mail.smtp.port", 465);  // 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("916885684@qq.com"));
        // 设置收件人地址
        message.setRecipients( RecipientType.TO, new InternetAddress[] { new InternetAddress(receiverMail) });
        // 设置邮件标题
        message.setSubject("借阅已批准");
        // 设置邮件内容
        message.setText("内容为：借阅请求已批准");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("916885684@qq.com", "jodfgikgmgppbcaf");// 密码为刚才得到的授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }
    public static void main(String[] args)  {
//        String lender = "xxq";
//        String receiverMail = "728832542@qq.com";
//        try {
//            sendMail(receiverMail,lender);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}

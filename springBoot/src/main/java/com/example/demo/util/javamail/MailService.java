package com.example.demo.util.javamail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <b>功能描述：</b>Java Mail 工具类 <br>
 * <b>修订记录：</b><br>
 */
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    /**
     * 邮件协议服务器
     */
    private String host;

    /**
     * 是否需要验证码
     */
    private Boolean auth;

    /**
     * 端口号 qq邮箱需要使用SSL 端口号465或587
     */
    private Integer port;

    /**
     * 指定发送邮件的协议
     */
    private String protocol;

    /**
     * Cardif设置为true，使用SSL连接，默认使用SSL端口。默认FALSE“SMTP协议和真正的“smtps”协议。
     */
    private Boolean enable;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码/授权码
     */
    private String password;
    /**
     * 发送人
     */
    private String from;
    /**
     * 发件人昵称
     */
    private String nick;

    /**
     * 收件人
     */
    private String to;

    public MailService(MailConfig mailConfig) {
        host = mailConfig.getHost();
        auth = mailConfig.getAuth();
        port = mailConfig.getPort();
        protocol = mailConfig.getProtocol();
        enable = mailConfig.getEnable();
        username = mailConfig.getUsername();
        password = mailConfig.getPassword();
        from = mailConfig.getFrom();
        nick = mailConfig.getNick();
        to = mailConfig.getTo();
    }

    /**
     * 发送邮件
     *
     * @param subject  标题
     * @param body     内容
     * @param filepath 附件列表,无附件传递null
     * @return
     * @throws MessagingException           消息异常
     * @throws AddressException             地址异常
     * @throws UnsupportedEncodingException 未知异常
     */
    public boolean sendMail(String subject, String body,
                            List<String> filepath) throws AddressException, MessagingException,
            UnsupportedEncodingException {
        // 参数修饰
        if (body == null) {
            body = "";
        }
        if (subject == null) {
            subject = "无主题";
        }
        // 创建Properties对象
        Properties props = System.getProperties();
        // 创建信件服务器
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.port", port);
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.ssl.enable", enable);
        //props.put("mail.debug", "true");
        // 得到默认的对话对象
        Session session = Session.getDefaultInstance(props, null);
        // 创建一个消息，并初始化该消息的各项元素
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(nick + "<" + from + ">"));
        // 创建收件人列表
        if (to != null && to.trim().length() > 0) {
            String[] arr = to.split(",");
            int receiverCount = arr.length;
            if (receiverCount > 0) {
                InternetAddress[] address = new InternetAddress[receiverCount];
                for (int i = 0; i < receiverCount; i++) {
                    address[i] = new InternetAddress(arr[i]);
                }
                msg.addRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                // 后面的BodyPart将加入到此处创建的Multipart中
                Multipart mp = new MimeMultipart();
                // 附件操作
                if (filepath != null && filepath.size() > 0) {
                    for (String filename : filepath) {
                        MimeBodyPart mbp = new MimeBodyPart();
                        // 得到数据源
                        FileDataSource fds = new FileDataSource(filename);
                        // 得到附件本身并至入BodyPart
                        mbp.setDataHandler(new DataHandler(fds));
                        // 得到文件名同样至入BodyPart
                        mbp.setFileName(fds.getName());
                        mp.addBodyPart(mbp);
                    }
                    MimeBodyPart mbp = new MimeBodyPart();
                    mbp.setText(body);
                    mp.addBodyPart(mbp);
                    // 移走集合中的所有元素
                    filepath.clear();
                    // Multipart加入到信件
                    msg.setContent(mp);
                } else {
                    // 设置邮件正文
                    msg.setText(body);
                }
                // 设置信件头的发送日期
                msg.setSentDate(new Date());
                msg.saveChanges();
                // 发送信件
                Transport transport = session.getTransport(protocol);
                transport.connect(username, password);
                transport.sendMessage(msg,
                        msg.getRecipients(Message.RecipientType.TO));
                transport.close();
                return true;
            } else {
                System.out.println("None receiver!");
                return false;
            }
        } else {
            System.out.println("None receiver!");
            return false;
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static void main(String[] args) throws AddressException,
            UnsupportedEncodingException, MessagingException {
        //sendMail("308216184@qq.com,631285268@qq.com", "注册信息邮件", "注册邮件，有附件", null);
        System.out.println("sendMail success!");
    }

}

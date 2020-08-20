package com.example.demo.util.javamail;

import lombok.Data;

/**
 * <b>功能：</b>JAVA发送邮件配置信息类<br>
 */
@Data
public class MailConfig {
    /**
     * 邮件协议服务器
     */
    private  String host;

    /**
     * 是否需要验证码
     */
    private  Boolean auth;

    /**
     * 端口号 qq邮箱需要使用SSL 端口号465或587
     */
    private  Integer port;

    /**
     * 指定发送邮件的协议
     */
    private  String protocol;

    /**
     * Cardif设置为true，使用SSL连接，默认使用SSL端口。默认FALSE“SMTP协议和真正的“smtps”协议。
     */
    private  Boolean enable;

    /**
     * 用户名
     */
    private  String username;
    /**
     * 密码/授权码
     */
    private  String password;
    /**
     * 发送人
     */
    private  String from;
    /**
     * 发件人昵称
     */
    private  String nick;

    /**
     * 收件人 多个收件人用“,”分割
     */
    private String to;

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
}

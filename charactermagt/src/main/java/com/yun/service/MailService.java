package com.yun.service;

import org.omg.CORBA.INTERNAL;

import java.util.Map;

/**
 * @ fileName:MailService
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/8 15:47
 * @ version:1.0.0
 */
public interface MailService {
    void send(String to, String subject, Map<String, Integer> map);
}

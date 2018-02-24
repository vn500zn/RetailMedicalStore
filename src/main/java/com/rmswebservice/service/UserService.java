package com.rmswebservice.service;

public interface UserService {
public String login(String jsonUserAuth);

public String changePassword(String userName, String oldPassword, String newPassword);
public String sendSms(String phoneNumber,String msg);
}

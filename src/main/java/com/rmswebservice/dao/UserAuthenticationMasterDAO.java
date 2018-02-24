package com.rmswebservice.dao;

import com.rmsutil.domain.UserAuthentication;

public interface UserAuthenticationMasterDAO {
public UserAuthentication login(UserAuthentication userAuthentication);

public int changePassword(String userName, String oldPassword, String newPassword);
}

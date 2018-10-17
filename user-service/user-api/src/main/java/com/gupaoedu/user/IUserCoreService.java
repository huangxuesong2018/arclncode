package com.gupaoedu.user;

import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;

public interface IUserCoreService {
    /**
     * 用户登录
     * @param request
     * @return
     */
    public UserLoginResponse login(UserLoginRequest request);

    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}

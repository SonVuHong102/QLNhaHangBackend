package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.user.GetListUserReponse;
import com.hitex.yousim.dto.response.user.UserResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface UserService {
    UserResponse addUser(BaseRequestData userRequest) throws ApplicationException;
    UserResponse createUser(BaseRequestData userRequest) throws ApplicationException;
    UserResponse login(BaseRequestData userRequest) throws ApplicationException;
    UserResponse updateUser(BaseRequestData userRequest) throws  ApplicationException;
    UserResponse selfUpdatedUser(BaseRequestData userRequest) throws  ApplicationException;
    UserResponse detailUser(BaseRequestData userRequest) throws  ApplicationException;
    GetListUserReponse getListUser(BaseRequestData userRequest) throws  ApplicationException;
    UserResponse getAdminInfo(BaseRequestData requestData) throws ApplicationException;
    boolean changePassUser(BaseRequestData userRequest) throws ApplicationException;
    boolean selfChangePassUser(BaseRequestData userRequest) throws ApplicationException;
}

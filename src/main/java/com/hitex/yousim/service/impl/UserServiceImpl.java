package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.user.GetListUserReponse;
import com.hitex.yousim.dto.response.user.UserResponse;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.UserService;
import com.hitex.yousim.utils.GenCodeUtils;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.PasswordEncryption;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public UserResponse addUser(BaseRequestData request) throws ApplicationException {
        UserResponse userResponse = new UserResponse();
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            User userLogin = userRepository.findUserBySessionAngToken(request.getSessionId(), request.getToken());
            if (ObjectUtils.isEmpty(userLogin)) {
                throw new ApplicationException("ERR_0000003");
            }
            if (StringUtils.isEmpty(userRequest.getUserName())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("username"));
            }
            if (StringUtils.isEmpty(userRequest.getPassword())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("password"));
            }
            User userr = userRepository.findByUsername(userRequest.getUserName());
            if (ObjectUtils.isEmpty(userr)) {
                User user = new User();
                BeanUtils.copyProperties(userRequest, user);
                user.setStatusUser(Constant.ACTIVE_USER);
                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                user.setPassword(PasswordEncryption.encryteBCryptPassword(userRequest.getPassword()));
                userRepository.save(user);
                BeanUtils.copyProperties(user, userResponse);
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return userResponse;
    }

    @Override
    public UserResponse createUser(BaseRequestData request) throws ApplicationException {
        UserResponse userResponse = new UserResponse();
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            if (StringUtils.isEmpty(userRequest.getUserName())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("username"));
            }
            if (StringUtils.isEmpty(userRequest.getPassword())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("password"));
            }
            User userr = userRepository.findByUsername(userRequest.getUserName());
            if (ObjectUtils.isEmpty(userr)) {
                User user = new User();
                BeanUtils.copyProperties(userRequest, user);
                user.setRoleId(Constant.ROLE_CUSTOMER);
                user.setStatusUser(Constant.ACTIVE_USER);
                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                user.setPassword(PasswordEncryption.encryteBCryptPassword(userRequest.getPassword()));
                userRepository.save(user);
                BeanUtils.copyProperties(user, userResponse);
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return userResponse;
    }

    @Override
    public UserResponse login(BaseRequestData request) throws ApplicationException {
        UserResponse userResponse = null;
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            User user = userRepository.findByUsername(userRequest.getUserName());
            boolean checkUser = PasswordEncryption.bCryptPasswordEncoder(userRequest.getPassword(), user.getPassword());
            if (checkUser) {
//                if (user.getRoleId() == Constant.ROLE_ADMIN) {
                userResponse = new UserResponse();
                BeanUtils.copyProperties(user, userResponse);
                String tokenData = GenCodeUtils.encrypt(user.getUserName() + "_" + user.getRoleId() + "_" + user.getUserId(), Constant.KEY, Constant.SECRET_KEY);
//                    Constant.userToken = tokenData;
                userResponse.setToken(tokenData);
                user.setToken(tokenData);
                HttpSession session = httpServletRequest.getSession();
                session.setMaxInactiveInterval(60 * 60 * 24);
                userResponse.setSession(session.getId());
                userRepository.save(user);
                BeanUtils.copyProperties(user, userResponse);
                userResponse.setPassword(null);
//                }
            } else {
                throw new ApplicationException("ERR_0000002");
            }
        } catch (ApplicationException e) {
            throw e;
        }
        return userResponse;
    }

    @Override
    public UserResponse updateUser(BaseRequestData request) {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        User checkMail = userRepository.findByEmail(userRequest.getEmail());
//        User checkPhone = userRepository.findByPhone(userRequest.getPhone());
        UserResponse userResponse = new UserResponse();
        if (!ObjectUtils.isEmpty(checkMail)) {
            User userUpdate = userRepository.findUserByUserId(userRequest.getUserId());
            userUpdate.setRoleId(userRequest.getRoleId());
            userUpdate.setEmail(userRequest.getEmail());
            userUpdate.setStatusUser(userRequest.getStatusUser());
            userUpdate.setUserName(userRequest.getUserName());
            userUpdate.setPhone(userRequest.getPhone());
            userUpdate.setUpdateTime(LocalDateTime.now());
            userRepository.save(userUpdate);
            BeanUtils.copyProperties(userUpdate, userResponse);
        }
        return userResponse;
    }

    @Override
    public UserResponse selfUpdatedUser(BaseRequestData request) throws ApplicationException {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        UserResponse userResponse = new UserResponse();
        try {
            User user = userRepository.findByUserId(userRequest.getUserId());
            if (!ObjectUtils.isEmpty(user)) {
                user.setAvatar(userRequest.getAvatar());
                user.setEmail(userRequest.getEmail());
                user.setPhone(userRequest.getPhone());
                user.setUpdateTime(LocalDateTime.now());
                userRepository.save(user);
                BeanUtils.copyProperties(user, userResponse);
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
            throw new ApplicationException("error");
        }
        return userResponse;
    }

    @Override
    public UserResponse detailUser(BaseRequestData request) {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        User user = userRepository.findUserByUserId(userRequest.getUserId());
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    @Override
    public GetListUserReponse getListUser(BaseRequestData request) throws ApplicationException {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            GetListUserReponse getListUserReponse = new GetListUserReponse();
            List<User> userList = userRepository.getListUser(userRequest.getUserName(), userRequest.getStatusUser(),
                    PageRequest.of(userRequest.getPage(), userRequest.getPageSize()));
            int totalItem = userRepository.countUserByUserNameAndStatusUser(userRequest.getUserName(), userRequest.getStatusUser());
            int totalPage = (int) Math.ceil((double) totalItem / (double) userRequest.getPageSize());
            getListUserReponse.setTotalItem(totalItem);
            getListUserReponse.setTotalPage(totalPage);
            getListUserReponse.setUserList(userList);
            return getListUserReponse;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }

    @Override
    public UserResponse getAdminInfo(BaseRequestData requestData) throws ApplicationException {
        UserResponse userResponse = new UserResponse();
        try {
            String userRequest = GenCodeUtils.decrypt(requestData.getToken(), Constant.KEY, Constant.SECRET_KEY);
            String userId = userRequest.split("_")[2];
            User user = userRepository.findByUserId(Integer.parseInt(userId));
            user.setPassword(null);
            BeanUtils.copyProperties(user, userResponse);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            throw new ApplicationException("error");
        }
        return userResponse;
    }

    @Override
    public boolean changePassUser(BaseRequestData request) throws ApplicationException {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            String newPass = userRequest.getPassword();
            User user = userRepository.findByUserId(userRequest.getUserId());
            user.setPassword(PasswordEncryption.encryteBCryptPassword(newPass));
            user.setUpdateTime(LocalDateTime.now());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new ApplicationException("ERR_0000001");
        }
    }

    @Override
    public boolean selfChangePassUser(BaseRequestData request) throws ApplicationException {
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            String newPass = userRequest.getNewPass();
            User user = userRepository.findByUserId(userRequest.getUserId());
            if (!PasswordEncryption.bCryptPasswordEncoder(userRequest.getPassword(), user.getPassword())) {
                return false;
            } else {
                user.setPassword(PasswordEncryption.encryteBCryptPassword(newPass));
                user.setUpdateTime(LocalDateTime.now());
                userRepository.save(user);
                return true;
            }
        } catch (Exception e) {
            throw new ApplicationException("ERR_0000001");
        }
    }
}

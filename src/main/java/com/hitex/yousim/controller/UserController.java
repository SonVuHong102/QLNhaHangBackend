package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.user.GetListUserReponse;
import com.hitex.yousim.dto.response.user.UserResponse;
import com.hitex.yousim.service.UserService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/api/")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping(value = "addUser")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserResponse userResponse = userService.addUser(requestData);
            return success(userResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "createUser")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserResponse userResponse = userService.createUser(requestData);
            return success(userResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "login")
    @ResponseBody
    public ResponseEntity login(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserResponse userResponse = userService.login(requestData);
            return success(userResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getListUser")
    @ResponseBody
    public ResponseEntity getListUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            GetListUserReponse listUser = userService.getListUser(requestData);
            return success(listUser);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "updateUser")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserResponse userResponse = userService.updateUser(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}

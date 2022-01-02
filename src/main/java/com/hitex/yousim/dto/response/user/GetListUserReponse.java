package com.hitex.yousim.dto.response.user;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.User;
import lombok.Data;

import java.util.List;

@Data
public class GetListUserReponse implements IResponseData {
    private int totalItem;
    private int totalPage;
    private List<User> userList;
}

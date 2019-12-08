package cn.edu.hdu.passbook.controller;

import cn.edu.hdu.passbook.log.LogConstants;
import cn.edu.hdu.passbook.log.LogGenerator;
import cn.edu.hdu.passbook.service.IUserService;
import cn.edu.hdu.passbook.vo.CreateUserRequest;
import cn.edu.hdu.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>创建用户服务</h1>
 * @author samy
 * @date 2019/12/6 18:59
 */
@RestController
@Slf4j
@RequestMapping("/passbook")
public class CreateUserController {

    /** 创建用户服务 */
    private final IUserService userService;

    /** HttpServletRequest */
    private final HttpServletRequest request;

    public CreateUserController(IUserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    /**
     * <h2>创建用户</h2>
     * @param user {@link CreateUserRequest}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/createUser")
    Response createUser(CreateUserRequest user) throws Exception{
        LogGenerator.genLog(request,-1, LogConstants.ActionName.CREATE_USER,user);
        return userService.createUser(user);
    }


}

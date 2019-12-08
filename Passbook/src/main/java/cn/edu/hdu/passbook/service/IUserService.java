package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.CreateUserRequest;
import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>用户服务：创建 User 服务</h1>
 * @author samy
 * @date 2019/12/1 15:56
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * @param user {@link CreateUserRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response createUser(CreateUserRequest user) throws Exception;
}

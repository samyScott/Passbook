package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.entity.UserDO;
import cn.edu.hdu.passbook.constant.ErrorCode;
import cn.edu.hdu.passbook.dao.UserDao;
import cn.edu.hdu.passbook.service.IUserService;
import cn.edu.hdu.passbook.vo.Response;
import cn.edu.hdu.passbook.vo.CreateUserRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <h1>创建用户服务实现</h1>
 * @author samy
 * @date 2019/12/1 16:13
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    /** UserDao 接口服务 */
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Response createUser(CreateUserRequest request) {
        if (request.validate() != ErrorCode.SUCCESS){
            log.error("create user failed,{}",JSON.toJSONString(request));
            return Response.failure("create user failed");
        }


        UserDO user = new UserDO();
        BeanUtils.copyProperties(request,user);

        user = userDao.save(user);
        log.info("insert user successfully ,{}", JSON.toJSONString(user));

        return new Response(user);
    }
}

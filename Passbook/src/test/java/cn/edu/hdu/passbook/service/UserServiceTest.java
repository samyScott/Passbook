package cn.edu.hdu.passbook.service;


import cn.edu.hdu.passbook.vo.CreateUserRequest;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户服务测试</h1>
 * @author samy
 * @date 2019/12/6 21:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() throws Exception{
        CreateUserRequest user = new CreateUserRequest();

        user.setUserAddress("beijing");
        user.setUsername("sam");
        user.setUserPhone("123456");

        user.setUserAge(18);
        user.setUserSex(1);

        System.out.println(JSON.toJSONString(userService.createUser(user)));
    }
}

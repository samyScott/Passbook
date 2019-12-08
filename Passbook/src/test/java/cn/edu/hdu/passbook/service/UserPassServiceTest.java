package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.Pass;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户优惠劵服务测试</h1>
 * @author samy
 * @date 2019/12/8 19:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPassServiceTest extends AbstractServiceTest{

    @Autowired
    private IUserPassService userPassService;

    @Test
    public void testGetUserPassInfo() throws Exception{
        System.out.println(
                JSON.toJSONString(
                        userPassService.getUserPassInfo(userId),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );
    }

    @Test
    public void testGetUserUsedPassInfo() throws Exception{
        System.out.println(
                JSON.toJSONString(
                        userPassService.getUserUsedPassInfo(userId),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );
    }

    @Test
    public void testGetUserAllPassInfo() throws Exception{
        System.out.println(
                JSON.toJSONString(
                        userPassService.getUserAllPassInfo(userId),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );
    }

    @Test
    public void testUserUsePass(){
        Pass pass = new Pass();
        pass.setTemplateId(2);
        pass.setUserId(1);

        System.out.println(
                JSON.toJSONString(
                        userPassService.userUsePass(pass),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );

    }
}

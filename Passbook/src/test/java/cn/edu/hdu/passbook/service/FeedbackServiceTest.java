package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.constant.FeedBackType;
import cn.edu.hdu.passbook.vo.CreateFeedbackRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户反馈服务测试</h1>
 * @author samy
 * @date 2019/12/8 20:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbackServiceTest extends AbstractServiceTest{

    @Autowired
    private IFeedbackService feedbackService;

    @Test
    public void testCreateFeedback(){
        CreateFeedbackRequest appRequest = new CreateFeedbackRequest();
        appRequest.setUserId(userId);
        appRequest.setFeedbackType(FeedBackType.APP.getCode());
        appRequest.setTemplateId(0);
        appRequest.setFeedbackComment("good");

        System.out.println(
                JSON.toJSONString(
                        feedbackService.createFeedback(appRequest),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );
    }

    @Test
    public void getFeedback(){
        System.out.println(
                JSON.toJSONString(
                        feedbackService.getFeedback(userId),
                        SerializerFeature.DisableCircularReferenceDetect
                )
        );
    }
}

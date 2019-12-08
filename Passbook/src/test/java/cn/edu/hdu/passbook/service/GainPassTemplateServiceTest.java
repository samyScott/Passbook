package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.vo.GainPassTemplateRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户领取优惠劵功能测试</h1>
 * @author samy
 * @date 2019/12/8 19:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GainPassTemplateServiceTest {

    @Autowired
    private IGainPassTemplateService gainPassTemplateService;

    @Test
    public void testGainPassTemplate() throws Exception{

        GainPassTemplateRequest request = new GainPassTemplateRequest();

        PassTemplateDO passTemplateDO = new PassTemplateDO();
        passTemplateDO.setMerchantsId(20);
        passTemplateDO.setPassTemplateId(2);
        passTemplateDO.setPassTemplateTitle("hdu-2");
        passTemplateDO.setPassTemplateHasToken(true);

        request.setPassTemplate(passTemplateDO);
        request.setUserId(1);

        System.out.println(JSON.toJSONString(gainPassTemplateService.gainPassTemplate(request), SerializerFeature.DisableCircularReferenceDetect));
    }
}

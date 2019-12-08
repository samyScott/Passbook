package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.constant.TemplateColor;
import cn.edu.hdu.passbook.vo.CreateMerchantsRequest;
import cn.edu.hdu.passbook.vo.PassTemplate;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <h1>商户服务测试类</h1>
 * @author samy
 * @date 2019/11/23 19:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {

    @Autowired
    private IMerchantsServ merchantsServ;

    @Test
    @Transactional
    public void testCreateMerchantServ(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("hdu");
        request.setAddress("HangZhou Zhejiang");
        request.setBusinessLicenseUrl("hdu.edu.cn");
        request.setPhone("15088669134");
        request.setLogoUrl("123.com");

        Response response = merchantsServ.createMerchants(request);

        System.out.println(JSON.toJSON(response));
    }

    @Test
    public void testBuildMerchantsInfoById(){
        Response response = merchantsServ.buildMerchantsInfoById(21);
        System.out.println(JSON.toJSON(response));
    }

    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setPassTemplateTitle("hdu-2");
        passTemplate.setMerchantsId(20);
        passTemplate.setPassTemplateBackground(TemplateColor.RED.getCode());
        Date date = new Date();
        passTemplate.setPassTemplateStart(date);
        passTemplate.setPassTemplateEnd(DateUtils.addDays(date,10));
        passTemplate.setPassTemplateHasToken(true);
        passTemplate.setPassTemplateLimit(100L);
        passTemplate.setPassTemplateDesc("Java");

        Response response = merchantsServ.dropPassTemplate(passTemplate);

        System.out.println(JSON.toJSON(response));
    }
}

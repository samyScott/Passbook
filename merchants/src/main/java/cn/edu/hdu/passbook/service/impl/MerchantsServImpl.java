package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.constant.Constants;
import cn.edu.hdu.passbook.constant.ErrorCode;
import cn.edu.hdu.passbook.dao.MerchantsDao;
import cn.edu.hdu.passbook.entity.Merchants;
import cn.edu.hdu.passbook.service.IMerchantsServ;
import cn.edu.hdu.passbook.vo.CreateMerchantsRequest;
import cn.edu.hdu.passbook.vo.CreateMerchantsResponse;
import cn.edu.hdu.passbook.vo.PassTemplate;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <h1>商户服务接口实现</h1>
 * @author samy
 * @date 2019/11/23 17:03
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {

    /** Merchants 数据库连接 */
    private final MerchantsDao merchantsDao;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public MerchantsServImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        //验证request内容的正确性
        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS){    //出错，添加错误信息
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else { //正确，添加商户，并在merchantsResponse设置id
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }

        response.setData(merchantsResponse);

        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();

        Optional<Merchants> optional = merchantsDao.findById(id); //构造商户信息
        if (!optional.isPresent()){ //不存在
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }else { //存在
            Merchants merchants = optional.get();
            response.setData(merchants);
        }

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();

        ErrorCode errorCode = template.validate(merchantsDao); //验证商户是否入住
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorMsg(errorCode.getDesc());
            response.setErrorCode(errorCode.getCode());
        }else {
            String message = JSON.toJSONString(template);
            kafkaTemplate.send(Constants.TEMPLATE_TOPIC,Constants.TEMPLATE_TOPIC,message);
            log.info("DropPassTemplates: {}",template);

        }

        return response;
    }
}

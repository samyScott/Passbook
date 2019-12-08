package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.bo.PassTemplateBO;
import cn.edu.hdu.passbook.constant.Constans;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * <h1>消费 Kafka 中的 PassTemplate </h1>
 * @author samy
 * @date 2019/12/1 15:07
 */
@Slf4j
@Service
public class ConsumePassTemplate {

    /** pass 相关的 Mysql 服务 */
    private final IMysqlPassService passService;

    public ConsumePassTemplate(IMysqlPassService passService){
        this.passService = passService;
    }

    @KafkaListener(topics = {Constans.TEMPLATE_TOPIC})
    public void receive(@Payload String passTemplate,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("Consumer Receive PassTemplate: {}",passTemplate);

        PassTemplateBO pt;
        try {
            pt = JSON.parseObject(passTemplate, PassTemplateBO.class);
        }catch (Exception ex){
            log.error("Parse PassTemplate Error: {}",ex.getMessage());
            return;
        }

        log.info("DropPassTemplateToHBase: {}",passService.dropPassTemplateToMysql(pt));
    }
}

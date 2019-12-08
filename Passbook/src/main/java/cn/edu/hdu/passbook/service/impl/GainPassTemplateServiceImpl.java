package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.entity.PassDO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.vo.GainPassTemplateRequest;
import cn.edu.hdu.passbook.constant.Constans;
import cn.edu.hdu.passbook.dao.PassDao;
import cn.edu.hdu.passbook.dao.PassTemplateDao;
import cn.edu.hdu.passbook.service.IGainPassTemplateService;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

/**
 * <h1>用户领取优惠劵功能实现</h1>
 * @author samy
 * @date 2019/12/3 20:16
 */
@Service
@Slf4j
public class GainPassTemplateServiceImpl implements IGainPassTemplateService {

    /** PassTemplateDao 接口 */
    private final PassTemplateDao passTemplateDao;

    /** PassDao 接口 */
    private final PassDao passDao;

    /** Redis 客户端 */
    private final StringRedisTemplate redisTemplate;

    public GainPassTemplateServiceImpl(PassTemplateDao passTemplateDao, PassDao passDao, StringRedisTemplate redisTemplate) {
        this.passTemplateDao = passTemplateDao;
        this.passDao = passDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    public Response gainPassTemplate(GainPassTemplateRequest request) throws Exception {

        // 获取优惠劵模板
        Integer passTemplateId = request.getPassTemplate().getPassTemplateId();
        PassTemplateDO passTemplate = passTemplateDao.findByPassTemplateId(passTemplateId);

        if (passTemplate == null){
            log.error("Gain PassTemplate Error: {}", passTemplateId);
            return Response.failure("Gain PassTemplate Error");
        }

        //校验数量是否已经为0
        if (passTemplate.getPassTemplateLimit() < 1){
            log.error("PassTemplate Limit Max: {}",JSON.toJSONString(passTemplate));
            return Response.failure("PassTemplate Limit Max");
        }

        //优惠劵时间校验
        Date cur = new Date();
        if (!(cur.getTime() >= passTemplate.getPassTemplateStart().getTime()
                && cur.getTime() <= passTemplate.getPassTemplateEnd().getTime())){
            log.error("PassTemplate ValidTime Error: {}",JSON.toJSONString(passTemplate));
            return Response.failure("PassTemplate ValidTime Error");
        }

        passTemplate.setPassTemplateLimit(passTemplate.getPassTemplateLimit() - 1);
        passTemplateDao.save(passTemplate);

        if (!addPassForUser(request)){
            return Response.failure("GainPassTemplate Failure");
        }
        return null;
    }


    /**
     * <h2>添加优惠劵到用户</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return true/false
     */
    private boolean addPassForUser(GainPassTemplateRequest request) throws IOException {

        PassDO pass = new PassDO();
        PassTemplateDO passTemplate = request.getPassTemplate();

        if (passTemplate.getPassTemplateHasToken()){
            String token = redisTemplate.opsForSet().pop(String.valueOf(passTemplate.getPassTemplateId()));
            if (null == token){
                log.error("Token not exist : {}",passTemplate.getPassTemplateId());
                return false;
            }
            recordTokenToFile(passTemplate.getMerchantsId(),
                    String.valueOf(passTemplate.getPassTemplateId()),token);
            pass.setPassToken(token);
        }else {
            pass.setPassToken("-1");
        }
        pass.setPassAssignedDate(new Date());
        pass.setUserId(request.getUserId());
        pass.setTemplateId(passTemplate.getPassTemplateId());

        passDao.save(pass);
        return true;
    }

    /**
     * <h2>将已领取的 token 记录到文件中</h2>
     * @param merchantsId 商户id
     * @param passTemplateId 优惠劵id
     * @param token 分配的优惠劵 token
     */
    private void recordTokenToFile(Integer merchantsId,String passTemplateId,String token) throws IOException {

        Files.write(Paths.get(Constans.TOKEN_DIR,String.valueOf(merchantsId),passTemplateId + Constans.USED_TOKEN_SUFFIX),
                (token + "\n").getBytes(),
                StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }
}

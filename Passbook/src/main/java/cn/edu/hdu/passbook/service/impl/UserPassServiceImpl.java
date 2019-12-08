package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.bo.PassInfo;
import cn.edu.hdu.passbook.entity.MerchantsDO;
import cn.edu.hdu.passbook.entity.PassDO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.constant.PassStatus;
import cn.edu.hdu.passbook.dao.MerchantsDao;
import cn.edu.hdu.passbook.dao.PassDao;
import cn.edu.hdu.passbook.dao.PassTemplateDao;
import cn.edu.hdu.passbook.service.IUserPassService;
import cn.edu.hdu.passbook.vo.Pass;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>用户优惠劵相关功能实现</h1>
 * @author samy
 * @date 2019/12/3 14:23
 */
@Slf4j
@Service
public class UserPassServiceImpl implements IUserPassService {

    /** MerchantsDao 客户端 */
    private final MerchantsDao merchantsDao;

    /** PassTemplateDao 客户端 */
    private final PassTemplateDao passTemplateDao;

    /** PassDao 客户端 */
    private final PassDao passDao;

    public UserPassServiceImpl(MerchantsDao merchantsDao, PassTemplateDao passTemplateDao, PassDao passDao) {
        this.merchantsDao = merchantsDao;
        this.passTemplateDao = passTemplateDao;
        this.passDao = passDao;
    }

    @Override
    public Response getUserPassInfo(Integer userId) throws Exception {
        return getPassInfoByStatus(userId,PassStatus.UNUSED);
    }

    @Override
    public Response getUserUsedPassInfo(Integer userId) throws Exception {
        return getPassInfoByStatus(userId,PassStatus.USED);
    }

    @Override
    public Response getUserAllPassInfo(Integer userId) throws Exception {
        return getPassInfoByStatus(userId,PassStatus.ALL);
    }

    @Override
    @Transactional
    public Response userUsePass(Pass pass) {
        //检查是否有该优惠劵模板
        PassTemplateDO passTemplate = passTemplateDao.findByPassTemplateId(pass.getTemplateId());
        if (passTemplate == null){
            log.error("UserUsePass Error : {}", JSON.toJSONString(pass));
            return Response.failure("UserUsePass Error");
        }

        //用户优惠劵中是否有该优惠卷
        List<PassDO> passes = passDao.findByUserIdAndTemplateId(pass.getUserId(),pass.getTemplateId());
        if (passes == null || passes.size() != 1){
            log.error("UserUsePass Error : {}", JSON.toJSONString(pass));
            return Response.failure("UserUsePass Error");
        }
        PassDO passDO = passes.get(0);
        if (passDO.getPassIsCon()){ //优惠劵是否已使用
            log.error("UserUsePass Error : {}", JSON.toJSONString(passDO));
            return Response.failure("UserUsePass Error");
        }

        //使用优惠劵
        passDO.setPassIsCon(true);
        passDO = passDao.save(passDO);

        return Response.success();
    }


    /**
     * <h2>根据优惠劵状态获取优惠劵信息</h2>
     * @param userId 用户id
     * @param status {@link PassStatus}
     * @return {@link Response}
     */
    private Response getPassInfoByStatus(Integer userId, PassStatus status) throws Exception {

        List<PassDO> passes;

        if (status == PassStatus.USED){
            passes = passDao.findByUserIdAndPassIsCon(userId,true);
        }else if (status == PassStatus.UNUSED){
            passes = passDao.findByUserIdAndPassIsCon(userId,false);
        }else {
            passes = passDao.findByUserId(userId);
        }

        Map<Integer, PassTemplateDO> passTemplateMap = buildPassTemplate(passes);
        Map<Integer, MerchantsDO> merchantsMap = buildMerchantsMap(new ArrayList<>(passTemplateMap.values()));

        List<PassInfo> result = new ArrayList<>();
        for (PassDO pass : passes){
            PassTemplateDO passTemplate = passTemplateMap.getOrDefault(pass.getTemplateId(), null);
            if (passTemplate == null){
                log.error("PassTemplate Null : {}",pass.getTemplateId());
                continue;
            }
            MerchantsDO merchants = merchantsMap.getOrDefault(passTemplate.getMerchantsId(), null);
            if (merchants == null){
                log.error("Merchants Null : {}",passTemplate.getPassTemplateId());
                continue;
            }

            Pass temp = new Pass();
            BeanUtils.copyProperties(pass,temp);

            result.add(new PassInfo(temp,passTemplate,merchants));
        }

        return new Response(result);
    }

    /**
     * <h2>通过获取的 Passes 对象构造 PassTemplate Map</h2>
     * @param passes {@link Pass}
     * @return {@link PassTemplateDO}
     * @throws Exception
     */
    private Map<Integer,PassTemplateDO> buildPassTemplate(List<PassDO> passes) throws Exception{
        //templateIds
        List<Integer> templateIds = passes.stream().map(PassDO::getTemplateId).collect(Collectors.toList());
        //获取passTemplates
        List<PassTemplateDO> passTemplates = passTemplateDao.findByPassTemplateIdIn(templateIds);

        //构建passTemplateMap
        Map<Integer,PassTemplateDO> passTemplateMap = new HashMap<>(passes.size());
        passTemplates.forEach(pt -> passTemplateMap.put(pt.getPassTemplateId(),pt));

        return passTemplateMap;
    }

    /**
     * <h2>通过获取的 PassTemplate 对象构造 Merchants Map</h2>
     * @param passTemplates {@link PassTemplateDO}
     * @return {@link MerchantsDO}
     */
    private Map<Integer, MerchantsDO> buildMerchantsMap(List<PassTemplateDO> passTemplates){
        Map<Integer,MerchantsDO> map = new HashMap<>(passTemplates.size());

        //获取所有的merchantId
        List<Integer> merchantIds = passTemplates
                .stream()
                .map(PassTemplateDO::getMerchantsId)
                .collect(Collectors.toList());
        List<MerchantsDO> merchants = merchantsDao.findByIdIn(merchantIds);
        merchants.forEach(merchant -> map.put(merchant.getId(),merchant));
        return map;
    }
}

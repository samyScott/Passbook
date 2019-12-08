package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.bo.InventoryInfo;
import cn.edu.hdu.passbook.entity.MerchantsDO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.bo.PassInfo;
import cn.edu.hdu.passbook.bo.PassTemplateInfo;
import cn.edu.hdu.passbook.dao.MerchantsDao;
import cn.edu.hdu.passbook.dao.PassTemplateDao;
import cn.edu.hdu.passbook.service.IInventoryService;
import cn.edu.hdu.passbook.service.IUserPassService;
import cn.edu.hdu.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>获取库存信息，只返回用户没有领取的</h1>
 * @author samy
 * @date 2019/12/3 16:51
 */
@Service
@Slf4j
public class InventoryServiceImpl implements IInventoryService {

    /** MerchantsDao 客户端 */
    private final MerchantsDao merchantsDao;

    /** PassTemplateDao 客户端 */
    private final PassTemplateDao passTemplateDao;

    private final IUserPassService userPassService;

    public InventoryServiceImpl(MerchantsDao merchantsDao, PassTemplateDao passTemplateDao, IUserPassService userPassService) {
        this.merchantsDao = merchantsDao;
        this.passTemplateDao = passTemplateDao;
        this.userPassService = userPassService;
    }

    @Override
    public Response getInventoryInfo(Integer userId) throws Exception {
        //获取已领取的优惠劵模板
        List<PassInfo> passInfos = (List<PassInfo>)userPassService.getUserPassInfo(userId).getData();
        List<PassTemplateDO> excludePassTemplates = passInfos.stream().map(PassInfo::getPassTemplate).collect(Collectors.toList());

        //设置排除集合
        List<Integer> excludeIds = new ArrayList<>();
        excludePassTemplates.forEach(passTemplate -> excludeIds.add(passTemplate.getPassTemplateId()));

        //获取可用优惠劵模板集合
        List<PassTemplateDO> availablePassTemplates = getAvailablePassTemplate(excludeIds);

        //构造data
        List<PassTemplateInfo> passTemplateInfos = buildPassTemplateInfo(availablePassTemplates);

        return new Response(new InventoryInfo(userId,passTemplateInfos));
    }

    /**
     * <h2>构造优惠劵信息</h2>
     * @param passTemplates {@link PassTemplateDO}
     * @return {@link PassTemplateInfo}
     */
    private List<PassTemplateInfo> buildPassTemplateInfo(List<PassTemplateDO> passTemplates){
        Map<Integer, MerchantsDO> merchantsMap = new HashMap<>();

        //构造merchantsMap
        List<Integer> merchantsIds = passTemplates.stream().map(PassTemplateDO::getMerchantsId).collect(Collectors.toList());
        merchantsIds.forEach(id -> merchantsMap.put(id,merchantsDao.getOne(id)));

        //构造PassTemplateInfo集合
        List<PassTemplateInfo> result = new ArrayList<>(merchantsMap.size());
        for (PassTemplateDO passTemplate : passTemplates){
            MerchantsDO merchants = merchantsMap.getOrDefault(passTemplate.getMerchantsId(), null);
            if (merchants == null){
                log.error("Merchants Error: {}",passTemplate.getMerchantsId());
                continue;
            }

            result.add(new PassTemplateInfo(passTemplate,merchants));
        }

        return result;
    }

    /**
     * <h2>获取系统中可领取的优惠劵</h2>
     * @param excludeIds 需要排除的优惠劵 ids
     * @return {@link PassTemplateDO}
     */
    private List<PassTemplateDO> getAvailablePassTemplate(List<Integer> excludeIds){

        List<PassTemplateDO> validPassTemplate = passTemplateDao.findAll();
        List<PassTemplateDO> availablePassTemplate = new ArrayList<>();

        Long curTime = new Date().getTime();
        for (PassTemplateDO passTemplate : validPassTemplate) {
            // 排除已经领取的优惠劵
            if (excludeIds.contains(passTemplate.getPassTemplateId())){
                continue;
            }

            // 时间比较，优惠劵是否可以领取
            if (passTemplate.getPassTemplateStart().getTime() <= curTime
                    && passTemplate.getPassTemplateEnd().getTime() >= curTime){
                availablePassTemplate.add(passTemplate);
            }
        }

        return availablePassTemplate;

    }
}

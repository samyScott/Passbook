package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.bo.PassTemplateBO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.dao.PassTemplateDao;
import cn.edu.hdu.passbook.service.IMysqlPassService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * <h1>Pass HBase 服务</h1>
 * @author samy
 * @date 2019/12/1 15:33
 */
@Slf4j
@Service
public class MysqlPassServiceImpl implements IMysqlPassService {

    /** PassTemplateDao 接口 */
    private final PassTemplateDao passTemplateDao;

    public MysqlPassServiceImpl(PassTemplateDao passTemplateDao) {
        this.passTemplateDao = passTemplateDao;
    }

    @Override
    public boolean dropPassTemplateToMysql(PassTemplateBO passTemplate) {
        if (passTemplate == null){
            log.error("drop passTemplate failed,passTemplate is null");
            return false;
        }

        PassTemplateDO passTemplateDO = new PassTemplateDO();
        BeanUtils.copyProperties(passTemplate,passTemplateDO);

        passTemplateDao.save(passTemplateDO);
        return true;
    }
}

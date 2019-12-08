package cn.edu.hdu.passbook.dao;

import cn.edu.hdu.passbook.entity.PassTemplateDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>PassTemplate Dao 接口</h1>
 * @author samy
 * @date 2019/12/7 19:23
 */
public interface PassTemplateDao extends JpaRepository<PassTemplateDO,Integer> {

    /**
     * <h2>根据passTemplateId获取优惠劵模板</h2>
     * @param passTemplateId 优惠卷模板id
     * @return {@link PassTemplateDO}
     */
    PassTemplateDO findByPassTemplateId(Integer passTemplateId);

    /**
     * <h2>获取passTemplate集合</h2>
     * @param passTemplateIds 优惠劵模板ids
     * @return {@link PassTemplateDO}
     */
    List<PassTemplateDO> findByPassTemplateIdIn(List<Integer> passTemplateIds);

    /**
     * <h2>获取所有优惠劵模板</h2>
     * @return {@link PassTemplateDO}
     */
    List<PassTemplateDO> findAll();
}

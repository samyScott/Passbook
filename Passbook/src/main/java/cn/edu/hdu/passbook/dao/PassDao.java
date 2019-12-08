package cn.edu.hdu.passbook.dao;

import cn.edu.hdu.passbook.entity.PassDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * <h1>Pass Dao 接口</h1>
 * @author samy
 * @date 2019/12/7 19:03
 */
public interface PassDao extends JpaRepository<PassDO,Integer> {

    /**
     * 领取优惠劵/使用优惠劵
     * @param pass {@link PassDO}
     * @return {@link PassDO}
     */
    PassDO save(PassDO pass);

    /**
     * <h2>获取未使用的优惠劵</h2>
     * @param userId 用户id
     * @param passIsCon true/false
     * @return {@link PassDO}
     */
    List<PassDO> findByUserIdAndPassIsCon(Integer userId, Boolean passIsCon);

    /**
     * <h2>获取用户所有的优惠劵</h2>
     * @param userId
     * @return
     */
    List<PassDO> findByUserId(Integer userId);


    /**
     *  <h2>获取用户卡包固定的优惠劵</h2>
     * @param userId 用户id
     * @param templateId 优惠劵模板id
     * @return {@link PassDO}
     */
    List<PassDO> findByUserIdAndTemplateId(Integer userId,Integer templateId);


}

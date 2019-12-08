package cn.edu.hdu.passbook.dao;

import cn.edu.hdu.passbook.entity.MerchantsDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * <h1>Merchants Dao 接口</h1>
 * @author samy
 * @date 2019/11/30 16:43
 */

public interface MerchantsDao extends JpaRepository<MerchantsDO,Integer> {

    /**
     * <h2>通过 id 获取商户对象</h2>
     * @param id 商户id
     * @return {@link MerchantsDO}
     */
    Optional<MerchantsDO> findById(Integer id);

    /**
     * <h2>根据商户名称获取商户对象</h2>
     * @param name 商户名称
     * @return {@link MerchantsDO}
     */
    MerchantsDO findByName(String name);

    /**
     * <h2>根据商户 ids 获取商户对象</h2>
     * @param ids 商户ids
     * @return {@link MerchantsDO}
     */
    List<MerchantsDO> findByIdIn(List<Integer> ids);
}

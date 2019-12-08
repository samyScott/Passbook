package cn.edu.hdu.passbook.bo;

import cn.edu.hdu.passbook.entity.MerchantsDO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import cn.edu.hdu.passbook.vo.Pass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取的优惠劵信息</h1>
 * @author samy
 * @date 2019/12/1 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassInfo {

    /** 优惠劵 */
    private Pass pass;

    /** 优惠劵模板 */
    private PassTemplateDO passTemplate;

    /** 优惠劵对应的商户 */
    private MerchantsDO merchants;
}

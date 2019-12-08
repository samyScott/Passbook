package cn.edu.hdu.passbook.bo;

import cn.edu.hdu.passbook.entity.MerchantsDO;
import cn.edu.hdu.passbook.entity.PassTemplateDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>优惠劵模板信息</h1>
 * @author samy
 * @date 2019/12/1 19:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo{

    /** 优惠劵模板 */
    private PassTemplateDO passTemplate;

    /** 优惠劵所属商户 */
    private MerchantsDO merchants;
}

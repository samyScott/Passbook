package cn.edu.hdu.passbook.vo;

import cn.edu.hdu.passbook.entity.PassTemplateDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取优惠劵的请求对象</h1>
 * @author samy
 * @date 2019/12/1 19:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GainPassTemplateRequest {

    /** 用户id */
    private Integer userId;

    /** PassTemplate 对象 */
    private PassTemplateDO passTemplate;
}

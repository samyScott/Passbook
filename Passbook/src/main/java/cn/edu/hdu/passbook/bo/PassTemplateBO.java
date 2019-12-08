package cn.edu.hdu.passbook.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author samy
 * @date 2019/12/8 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplateBO {
    /** 商户 id */
    private Integer merchantsId;

    /** 优惠劵模板标题 */
    private String passTemplateTitle;

    /** 优惠劵模板详细信息 */
    private String passTemplateDesc;

    /** 优惠劵模板是否含有token，默认false */
    private Boolean passTemplateHasToken = Boolean.FALSE; // token 存储于 Redis Set中，每次领取从 Redis 中获取

    /** 优惠劵模板背景颜色 1：红色 2：绿色 3：蓝色 */
    private Integer passTemplateBackground;

    /** 优惠劵限制数量 */
    private Long passTemplateLimit;

    /** 优惠劵开始时间 */
    private Date passTemplateStart;

    /** 优惠劵结束时间 */
    private Date passTemplateEnd;
}

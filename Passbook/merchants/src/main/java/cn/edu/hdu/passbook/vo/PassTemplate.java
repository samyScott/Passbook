package cn.edu.hdu.passbook.vo;

import cn.edu.hdu.passbook.constant.ErrorCode;
import cn.edu.hdu.passbook.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>投放的优惠卷对象定义</h1>
 * @author samy
 * @date 2019/11/23 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

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

    /**
     * <h2>校验优惠卷对象的有效性</h2>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){

        if (!merchantsDao.findById(merchantsId).isPresent()){
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}

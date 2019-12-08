package cn.edu.hdu.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>用户领取的优惠劵</h1>
 * @author samy
 * @date 2019/11/30 19:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pass {

    /** 用户 id */
    private Integer userId;

    /** 优惠劵模板 id */
    private Integer templateId;

    /** 优惠劵 token，有可能是 null，则填充 -1 */
    private String passToken;

    /** 领取日期 */
    private Date passAssignedDate;

    /** 是否消费  */
    private Boolean passIsCon;
}

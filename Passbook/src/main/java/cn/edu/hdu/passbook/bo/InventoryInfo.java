package cn.edu.hdu.passbook.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>库存请求响应</h1>
 * @author samy
 * @date 2019/12/1 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInfo {

    /** 用户 id */
    private Integer userId;

    /** 还未领取的优惠劵模板信息 */
    private List<PassTemplateInfo> passTemplateInfos;
}

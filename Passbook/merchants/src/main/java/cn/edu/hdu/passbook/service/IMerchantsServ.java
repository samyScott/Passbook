package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.CreateMerchantsRequest;
import cn.edu.hdu.passbook.vo.PassTemplate;
import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>商户服务接口定义</h1>
 * @author samy
 * @date 2019/11/23 16:52
 */
public interface IMerchantsServ {

    /**
     * <h2>创建商户服务</h2>
     * @param request {@link CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * <h2>根据 id 构造商户信息</h2>
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * <h2>投放优惠卷</h2>
     * @param template {@link PassTemplate} 优惠卷对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);
}

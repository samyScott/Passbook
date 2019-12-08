package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.GainPassTemplateRequest;
import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>用户领取优惠劵接口</h1>
 * @author samy
 * @date 2019/12/1 19:41
 */
public interface IGainPassTemplateService {

    /**
     * <h2>用户领取优惠劵</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}

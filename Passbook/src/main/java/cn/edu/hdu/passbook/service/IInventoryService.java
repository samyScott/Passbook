package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>获取库存信息：只返回用户没有领取的，即优惠劵库存功能的实现接口定义</h1>
 * @author samy
 * @date 2019/12/1 19:38
 */
public interface IInventoryService {

    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    Response getInventoryInfo(Integer userId) throws Exception;
}

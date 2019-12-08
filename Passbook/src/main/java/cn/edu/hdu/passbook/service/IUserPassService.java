package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.Pass;
import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>获取用户个人优惠劵信息</h1>
 * @author samy
 * @date 2019/12/1 19:50
 */
public interface IUserPassService {

    /**
     * <h2>获取用户个人优惠劵信息，即我的优惠劵功能实现</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    Response getUserPassInfo(Integer userId) throws Exception;

    /**
     * <h2>获取用户已经消费的优惠劵，即已使用优惠劵功能实现</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    Response getUserUsedPassInfo(Integer userId) throws Exception;

    /**
     * <h2>获取用户所有的优惠劵</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    Response getUserAllPassInfo(Integer userId) throws Exception;


    /**
     * <h2>用户使用优惠劵</h2>
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    Response userUsePass(Pass pass);
}

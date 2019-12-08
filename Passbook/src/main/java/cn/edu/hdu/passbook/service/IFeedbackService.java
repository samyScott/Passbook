package cn.edu.hdu.passbook.service;

import cn.edu.hdu.passbook.vo.CreateFeedbackRequest;
import cn.edu.hdu.passbook.vo.Response;

/**
 * <h1>评论功能：即用户评论相关功能实现</h1>
 * @author samy
 * @date 2019/12/1 16:40
 */
public interface IFeedbackService {

    /**
     * <h2>创建评论</h2>
     * @param feedback {@link CreateFeedbackRequest}
     * @return {@link Response}
     */
    Response createFeedback(CreateFeedbackRequest feedback);

    /**
     * <h2>获取用户评论</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    Response getFeedback(Integer userId);
}

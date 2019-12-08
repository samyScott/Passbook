package cn.edu.hdu.passbook.service.impl;

import cn.edu.hdu.passbook.entity.FeedbackDO;
import cn.edu.hdu.passbook.dao.FeedbcakDao;
import cn.edu.hdu.passbook.service.IFeedbackService;
import cn.edu.hdu.passbook.vo.CreateFeedbackRequest;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>评论功能实现</h1>
 * @author samy
 * @date 2019/12/1 16:43
 */
@Slf4j
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    /** Feedback Dao 接口 */
    private final FeedbcakDao feedbcakDao;

    public FeedbackServiceImpl(FeedbcakDao feedbcakDao) {
        this.feedbcakDao = feedbcakDao;
    }

    @Override
    public Response createFeedback(CreateFeedbackRequest feedback) {
        if (!feedback.validate()){
            log.error("Feedback Error: {}", JSON.toJSONString(feedback));
            return Response.failure("Feedback Error");
        }

        FeedbackDO fb = new FeedbackDO();
        BeanUtils.copyProperties(feedback,fb);

        feedbcakDao.save(fb);
        return Response.success();
    }

    @Override
    public Response getFeedback(Integer userId) {
        List<FeedbackDO> feedbacks = feedbcakDao.findByUserId(userId);
        return new Response(feedbacks);
    }
}

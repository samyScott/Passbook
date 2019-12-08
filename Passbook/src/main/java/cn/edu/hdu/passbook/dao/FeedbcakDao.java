package cn.edu.hdu.passbook.dao;

import cn.edu.hdu.passbook.entity.FeedbackDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>Feedback Dao 接口</h1>
 * @author samy
 * @date 2019/12/7 18:55
 */
public interface FeedbcakDao extends JpaRepository<FeedbackDO,Integer> {

    /**
     * <h2>创建新的评论</h2>
     * @param feedback {@link FeedbackDO}
     * @return {@link FeedbackDO}
     */
    FeedbackDO save(FeedbackDO feedback);

    /**
     * <h2>获取用户的所有评论</h2>
     * @param userId 用户id
     * @return {@link FeedbackDO}
     */
    List<FeedbackDO> findByUserId(Integer userId);


}

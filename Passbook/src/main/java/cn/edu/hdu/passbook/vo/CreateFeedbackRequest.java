package cn.edu.hdu.passbook.vo;

import cn.edu.hdu.passbook.constant.FeedBackType;
import com.google.common.base.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户评论</h1>
 * @author samy
 * @date 2019/11/30 19:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedbackRequest {

    /** 用户 id */
    private Integer userId;

    /** 评论类型 "pass" "app" */
    private String feedbackType;

    /** PassTemplate RowKey,如果是 app 类型的评论，则为0 */
    private Integer templateId;

    /** 评论内容 */
    private String feedbackComment;

    public boolean validate(){
        FeedBackType feedBackType = Enums.getIfPresent(
                FeedBackType.class,this.feedbackType.toUpperCase()
        ).orNull();

        return !(null == feedBackType || null == feedbackComment);
    }
}

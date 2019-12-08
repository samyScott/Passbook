package cn.edu.hdu.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>评论model</h1>
 * @author samy
 * @date 2019/12/7 15:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class FeedbackDO {

    /** 评论 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id",nullable = false)
    private Integer feedbackId;

    /** 用户 id */
    @Basic
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    /** 优惠劵模板 id ,如果为app评论默认为0 */
    @Basic
    @Column(name = "template_id",nullable = false)
    private Integer templateId = 0;

    /** 评论类型 "pass" "app" */
    @Basic
    @Column(name = "feedback_type",nullable = false)
    private String feedbackType;

    /** 评论内容 */
    @Basic
    @Column(name = "feedback_comment",nullable = false)
    private String feedbackComment;

}

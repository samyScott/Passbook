package cn.edu.hdu.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * <h1>优惠劵 model</h1>
 * @author samy
 * @date 2019/12/7 16:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pass")
public class PassDO {

    /** 优惠劵id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id",nullable = false)
    private Integer passId;

    /** 用户 id */
    @Basic
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    /** 优惠劵模板 id */
    @Basic
    @Column(name = "template_id",nullable = false)
    private Integer templateId;

    /** 优惠劵token，如果没有就为-1 */
    @Basic
    @Column(name = "pass_token",nullable = false)
    private String passToken = "-1";

    /** 优惠劵领取日期 */
    @Basic
    @Column(name = "pass_assigned_date",nullable = false)
    private Date passAssignedDate;

    /** 优惠劵是否消费 0：未消费 1：已消费 */
    @Basic
    @Column(name = "pass_is_con",nullable = false)
    private Boolean passIsCon = Boolean.FALSE;
}

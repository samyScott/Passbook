package cn.edu.hdu.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * <h1>优惠劵模板 model</h1>
 * @author samy
 * @date 2019/12/7 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passtemplate")
public class PassTemplateDO {

    /** 优惠劵模板 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passtemplate_id",nullable = false)
    private Integer passTemplateId;

    /** 商户 id */
    @Basic
    @Column(name = "merchants_id",nullable = false)
    private Integer merchantsId;

    /** 优惠劵模板标题 */
    @Basic
    @Column(name = "passtemplate_title",nullable = false)
    private String passTemplateTitle;

    /** 优惠劵模板详细信息 */
    @Basic
    @Column(name = "passtemplate_desc",nullable = false)
    private String passTemplateDesc;

    /** 优惠劵模板是否含有token，默认false */
    @Basic
    @Column(name = "passtemplate_has_token",nullable = false)
    private Boolean passTemplateHasToken = Boolean.FALSE;

    /** 优惠劵模板背景颜色 1：红色 2：绿色 3：蓝色 */
    @Basic
    @Column(name = "passtemplate_background",nullable = false)
    private Integer passTemplateBackground;

    /** 优惠劵限制数量 */
    @Basic
    @Column(name = "passtemplate_limit",nullable = false)
    private Long passTemplateLimit;

    /** 优惠劵开始时间 */
    @Basic
    @Column(name = "passtemplate_start",nullable = false)
    private Date passTemplateStart;

    /** 优惠劵结束时间 */
    @Basic
    @Column(name = "passtemplate_end",nullable = false)
    private Date passTemplateEnd;


}

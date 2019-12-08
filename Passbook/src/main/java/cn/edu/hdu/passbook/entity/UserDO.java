package cn.edu.hdu.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>用户 model</h1>
 * @author samy
 * @date 2019/12/7 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserDO {

    /** 用户id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    /** 用户名称 */
    @Basic
    @Column(name = "user_name",nullable = false)
    private String username;

    /** 用户年龄 */
    @Basic
    @Column(name = "user_age",nullable = false)
    private Integer userAge;

    /** 用户性别 0：女 1：男 */
    @Basic
    @Column(name = "user_sex",nullable = false)
    private Integer userSex;

    /** 用户联系方式 */
    @Basic
    @Column(name = "user_phone",nullable = false)
    private String userPhone;

    /** 用户地址 */
    @Basic
    @Column(name = "user_address",nullable = false)
    private String userAddress;
}

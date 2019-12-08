package cn.edu.hdu.passbook.vo;

import cn.edu.hdu.passbook.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * <h1>User Object</h1>
 * @author samy
 * @date 2019/11/30 19:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    /** 用户名称 */
    private String username;

    /** 用户年龄 */
    private Integer userAge;

    /** 用户性别 0：女 1：男 */
    private Integer userSex;

    /** 用户联系方式 */
    private String userPhone;

    /** 用户地址 */
    private String userAddress;

    public ErrorCode validate(){
        if (StringUtils.isEmpty(username)){
            return ErrorCode.EMPTY_USER_NAME;
        }
        if (StringUtils.isEmpty(userPhone)){
            return ErrorCode.EMPTY_USER_PHONE;
        }
        if (StringUtils.isEmpty(userAddress)){
            return ErrorCode.EMPTY_USER_ADDRESS;
        }
        if (null == userSex){
            return ErrorCode.EMPTY_USER_SEX;
        }
        if (null == userAge){
            return ErrorCode.EMPTY_USER_AGE;
        }

        return ErrorCode.SUCCESS;

    }
}

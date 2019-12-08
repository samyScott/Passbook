package cn.edu.hdu.passbook.constant;

/**
 * <h1>校验字段枚举</h1>
 * @author samy
 * @date 2019/12/7 17:01
 */
public enum ErrorCode {

    /** User类 校验错误 */
    EMPTY_USER_NAME(1,"用户名称为空"),
    EMPTY_USER_PHONE(2,"用户联系方式为空"),
    EMPTY_USER_ADDRESS(3,"用户地址为空"),
    EMPTY_USER_SEX(4,"用户性别为空"),
    EMPTY_USER_AGE(5,"用户年龄为空"),

    SUCCESS(0,"无错误");

    /** 错误码 */
    private Integer errorCode;

    /** 错误信息 */
    private String errorMsg;

    ErrorCode(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

package cn.edu.hdu.passbook.constant;

/**
 * <h1>评论类型枚举</h1>
 * @author samy
 * @date 2019/11/30 15:49
 */
public enum FeedBackType {

    PASS("pass","针对优惠卷的评论"),
    APP("app","针对卡包 APP 的评论");

    /** 评论类型标号 */
    private String code;

    /** 评论类型描述 */
    private String desc;

    FeedBackType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

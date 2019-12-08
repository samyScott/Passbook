package cn.edu.hdu.passbook.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>日志对象</h1>
 * @author samy
 * @date 2019/11/30 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogObject {

    /** 日志动作类型 */
    private String action;

    /** 用户id */
    private Integer userId;

    /** 当前时间戳 */
    private Long timestamp;

    /** 客户端 ip 地址 */
    private String remoteIp;

    /** 日志记录 */
    private Object info = null;
}

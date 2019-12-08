package cn.edu.hdu.passbook.service;


import cn.edu.hdu.passbook.bo.PassTemplateBO;

/**
 * <h1>Pass Mysql 服务</h1>
 * @author samy
 * @date 2019/12/1 15:04
 */
public interface IMysqlPassService {

    /**
     * <h2>将 PassTemplate 写入 HBase</h2>
     * @param passTemplate {@link PassTemplateBO}
     * @return true/false
     */
    boolean dropPassTemplateToMysql(PassTemplateBO passTemplate);
}

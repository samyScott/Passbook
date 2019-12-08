package cn.edu.hdu.passbook.dao;

import cn.edu.hdu.passbook.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * <h1> User dao 接口 </h1>
 * @author samy
 * @date 2019/12/7 16:24
 */
public interface UserDao extends JpaRepository<UserDO,Integer> {

    /**
     * <h2>创建新的用户</h2>
     * @param user {@link UserDO}
     * @return {@link UserDO}
     */
    UserDO save(UserDO user);

    /**
     * <h2>根据用户id查找用户</h2>
     * @param userId 用户id
     * @return {@link UserDO}
     */
    Optional<UserDO> findById(Integer userId);
}

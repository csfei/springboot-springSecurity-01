package com.cuisf.mapper;

import com.cuisf.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cuisf
 * @since 2021-08-12
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> getRoleCodeByUserName(String username);
}

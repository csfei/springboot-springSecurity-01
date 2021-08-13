package com.cuisf.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuisf.entity.SysUser;
import com.cuisf.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new RuntimeException("用户名不能为空");
        }

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();


        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);

        if (ObjectUtil.isEmpty(sysUser)){
            throw new UsernameNotFoundException(String.format("%s这个用户不存在"));
        }

        List<GrantedAuthority> list = new ArrayList<>();
        //根据用户获取权限
        List<String> codeList =  sysUserMapper.getRoleCodeByUserName(username);

        codeList.forEach(code ->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
            list.add(simpleGrantedAuthority);
        });

        return new User(sysUser.getUsername(),sysUser.getPassword(),list);
    }
}

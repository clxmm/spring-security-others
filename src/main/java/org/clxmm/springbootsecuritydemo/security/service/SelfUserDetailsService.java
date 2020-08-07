package org.clxmm.springbootsecuritydemo.security.service;

import org.clxmm.springbootsecuritydemo.core.entry.SysUser;
import org.clxmm.springbootsecuritydemo.core.service.ISysUserService;
import org.clxmm.springbootsecuritydemo.security.entity.SelfUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author clx
 * @date 2020/8/7 13:34
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    ISysUserService sysUserService;





    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userByName = sysUserService.getUserByName(username);

        SelfUserEntity selfUserEntity = new SelfUserEntity();
        if (username != null) {

        } else {

            throw new UsernameNotFoundException("用户不存在");
        }

        return null;
    }
}

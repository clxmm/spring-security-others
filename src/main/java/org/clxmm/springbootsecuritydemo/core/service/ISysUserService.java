package org.clxmm.springbootsecuritydemo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.springbootsecuritydemo.core.entry.SysUser;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author clx
 * @since 2020-08-06
 */
public interface ISysUserService  {


    /**
     * 根据用户名查询出实体类
     * @param username
     * @return
     */
    SysUser getUserByName(String username);




}

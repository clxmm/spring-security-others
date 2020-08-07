package org.clxmm.springbootsecuritydemo.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.clxmm.springbootsecuritydemo.core.dao.SysUserMapper;
import org.clxmm.springbootsecuritydemo.core.entry.SysUser;
import org.clxmm.springbootsecuritydemo.core.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author clx
 * @since 2020-08-06
 */
@Service
public class SysUserServiceImpl implements ISysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByName(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }
}

package org.clxmm.springbootsecuritydemo.core.dao;

import org.clxmm.springbootsecuritydemo.core.entry.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}
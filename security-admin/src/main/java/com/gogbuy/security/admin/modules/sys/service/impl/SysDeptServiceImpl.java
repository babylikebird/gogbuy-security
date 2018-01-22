package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.repository.SysDeptMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import com.gogbuy.security.admin.modules.sys.service.SysUserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:37
 * ProjectName:gogbuy-security
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserDeptService userDeptService;
    @Autowired
    private SysDeptRoleService deptRoleService;

    @Override
    public int deleteById(String deptId) {
        //当有子部门的时候，需要删除子部门，否则不允许删除
        List<SysDept> deptList = findByParentId(deptId);
        if (deptList != null && deptList.size() > 0){
            return -1;
        }
        //1.删除部门
        deptMapper.deleteByPrimaryKey(deptId);
        //2.用户部门
        userDeptService.deleteByDeptId(deptId);
        //3.删除部门权限
        deptRoleService.deleteByDeptId(deptId);
        return 1;
    }

    @Override
    public int save(SysDept record) {
        return deptMapper.insert(record);
    }

    @Override
    public SysDept findById(String id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysDept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysDept record) {
        return deptMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysDept> getDeptByUserId(String userId) {
        return deptMapper.getDeptByUserId(userId);
    }

    @Override
    public List<SysDept> findList(Map<String, Object> map) {
        return deptMapper.findList(map);
    }

    @Override
    public List<SysDept> findByParentId(String parentId) {
        return deptMapper.findByParentId(parentId);
    }
}

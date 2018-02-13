package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.repository.SysDeptMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import com.gogbuy.security.admin.modules.sys.service.SysUserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public R deleteById(String deptId) {
        //当有子部门的时候，需要删除子部门，否则不允许删除
        List<SysDept> deptList = findByParentId(deptId);
        if (deptList != null && deptList.size() > 0){
            return R.failure(StatusCode.FAILURE,"需要先删除下属部门");
        }
        //1.删除部门
        deptMapper.deleteByPrimaryKey(deptId);
        //2.用户部门
        userDeptService.deleteByDeptId(deptId);
        //3.删除部门权限
        deptRoleService.deleteByDeptId(deptId);
        return R.ok();
    }

    @Override
    public int save(SysDept record) {
        return deptMapper.insertSelective(record);
    }

    @Override
    public SysDept findById(String id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysDept record) {
        record.setUpdateTime(new Date());
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
    public List<SysDept> list(Integer pageNum, Integer pageSize, SysDept dept) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return findByEntity(dept);
    }

    @Override
    public List<SysDept> findByParentId(String parentId) {
        return deptMapper.findByParentId(parentId);
    }

    @Override
    public List<SysDept> findByEntity(SysDept dept) {
        return deptMapper.findByEntity(dept);
    }

    @Override
    public SysDept findByOrgCode(String orgCode) {
        SysDept dept = new SysDept();
        dept.setOrgCode(orgCode);
        List<SysDept> list = deptMapper.findByEntity(dept);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SysDept findByName(String name) {
        SysDept dept = new SysDept();
        dept.setDeptName(name);
        List<SysDept> list = deptMapper.findByEntity(dept);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}

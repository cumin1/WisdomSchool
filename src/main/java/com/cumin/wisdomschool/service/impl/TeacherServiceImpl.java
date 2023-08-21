package com.cumin.wisdomschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cumin.wisdomschool.mapper.TeacherMapper;
import com.cumin.wisdomschool.pojo.LoginForm;
import com.cumin.wisdomschool.pojo.Student;
import com.cumin.wisdomschool.pojo.Teacher;
import com.cumin.wisdomschool.service.TeacherService;
import com.cumin.wisdomschool.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(Long userId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public IPage<Teacher> getTeacherByPro(Page<Teacher> page, Teacher teacher) {
        String name = teacher.getName();
        String clazzName = teacher.getClazzName();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(clazzName)){
            queryWrapper.like("clazz_name",clazzName);
        }
        queryWrapper.orderByDesc("id");
        Page<Teacher> pageRs = baseMapper.selectPage(page,queryWrapper);
        return pageRs;
    }
}

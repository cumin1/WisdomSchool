package com.cumin.wisdomschool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cumin.wisdomschool.pojo.Grade;
import com.cumin.wisdomschool.service.GradeService;
import com.cumin.wisdomschool.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;


    // 获取全部年级
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grages =  gradeService.getGrades();
        return Result.ok(grages);
    }

    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@PathVariable("pageNo") Integer pageNo,
                            @PathVariable("pageSize") Integer pageSize,
                             String gradeName
    ) {
        // 分页 带条件查询
        Page<Grade> page = new Page<>(pageNo,pageSize);
        // 通过Service查询
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page,gradeName);

        // 封装Result对象并返回
        return Result.ok(pageRs);
    }

    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade){
        // 接受参数调用服务层方法 完成增加或者修改
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }
}

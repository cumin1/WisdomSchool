package com.cumin.wisdomschool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cumin.wisdomschool.pojo.Student;
import com.cumin.wisdomschool.service.StudentService;
import com.cumin.wisdomschool.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        studentService.saveOrUpdate(student);
        return Result.ok();
    }


    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids){
        studentService.removeByIds(ids);
        return  Result.ok();
    }


    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@PathVariable("pageNo") Integer pageNo,
                                  @PathVariable("pageSize")Integer pageSize,
                                  Student student

    ){
        Page<Student> page = new Page<>(pageNo,pageSize);
        IPage<Student> pageRs = studentService.getStudentByOpr(page,student);
        return Result.ok(pageRs);
    }
}

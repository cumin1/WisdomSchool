package com.cumin.wisdomschool.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cumin.wisdomschool.pojo.Clazz;
import com.cumin.wisdomschool.pojo.Grade;
import com.cumin.wisdomschool.service.ClazzService;
import com.cumin.wisdomschool.service.GradeService;
import com.cumin.wisdomschool.util.Result;
import com.fasterxml.jackson.databind.ser.std.ClassSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzList = clazzService.getClazzs();
        return Result.ok(clazzList);
    }

    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz){
        clazzService.save(clazz);
        return Result.ok();
    }

    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable("pageNo") Integer pageNo,
                                @PathVariable("pageSize") Integer pageSize,
                                Clazz clazz){
        Page<Clazz> page =new Page<>(pageNo,pageSize);

        IPage<Clazz> iPage=clazzService.getClazzsByOpr(page,clazz);

        return Result.ok(iPage);
    }

}

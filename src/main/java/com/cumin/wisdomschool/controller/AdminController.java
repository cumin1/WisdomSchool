package com.cumin.wisdomschool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cumin.wisdomschool.pojo.Admin;
import com.cumin.wisdomschool.service.AdminService;
import com.cumin.wisdomschool.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String name
    ){
        Page<Admin> page = new Page<>(pageNo,pageSize);
        IPage<Admin> pageRs = adminService.getAdminByPro(page,name);

        return  Result.ok(pageRs);
    }
}

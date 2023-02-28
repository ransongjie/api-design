package com.xcrj.apidesignme.controller;


import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xcrj.apidesignme.generated.dao.entity.Student;
import com.xcrj.apidesignme.unified.ErrorCode;
import com.xcrj.apidesignme.unified.PageReq;
import com.xcrj.apidesignme.unified.PageRes;
import com.xcrj.apidesignme.unified.ResultMe;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import com.xcrj.apidesignme.generated.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private IStudentService iStudentService;

    //增加一个
    @PostMapping("/one")
    public ResultMe<Object> addOne(@RequestBody Student reqVO) {
        return iStudentService.save(reqVO) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //批量增加
    @PostMapping("/batch")
    public ResultMe<Object> addBatch(@RequestBody List<Student> reqVOList) {
        return iStudentService.saveBatch(reqVOList) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //根据ID删除
    @DeleteMapping("/one/{id}")
    public ResultMe<Object> delById(@PathVariable("id") Long id) {
        return iStudentService.removeById(id) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //根据ID列表批量删除
    @DeleteMapping("/batch/{ids}")
    public ResultMe<Object> delBatch(@PathVariable("ids") List<Long> ids) {
        return iStudentService.removeByIds(ids) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //根据ID修改
    @PutMapping("/one")
    public ResultMe<Object> putOne(@RequestBody Student reqVO) {
        return iStudentService.updateById(reqVO) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //根据ID列表批量修改
    @PutMapping("/batch")
    public ResultMe<Object> putBatch(@RequestBody List<Student> reqVOList) {
        return iStudentService.updateBatchById(reqVOList) ? ResultMe.<Object>success() : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    //根据ID查询
    @GetMapping("/{id}")
    public ResultMe getById(@PathVariable("id") Long id) {
        Student utStudent = iStudentService.getById(id);
        return utStudent == null ? ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001) : ResultMe.<Student>success(utStudent);
    }

    //分页查询
    //pageHelper分页
    @GetMapping("/page/{pageNum}/{pageSize}")
    public PageRes<Student> getPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        Page<Student> page = PageHelper.<Student>startPage(pageNum, pageSize);

        List<Student> list = iStudentService.list();

        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return PageRes.<Student>success(page.getTotal(), list);
    }

    //mybatis-plus分页
    @PostMapping("/page2")
    public PageRes<Student> page2(@RequestBody PageReq pageReq) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Student> page=
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReq.getPageNum(),pageReq.getPageSize());

        QueryWrapper<Student> queryWrapper= Wrappers.<Student>query().orderBy(pageReq.getOrdered(), pageReq.getAsc(), pageReq.getOrderColumn());
        IPage<Student> iPage=iStudentService.page(page, queryWrapper);

        return PageRes.<Student>success(iPage.getTotal(),iPage.getRecords());
    }

    //查询所有
    @GetMapping("/all")
    public ResultMe<List<Student>> getAll() {
        List<Student> list = iStudentService.lambdaQuery().list();
        return ResultMe.<List<Student>>success(list);
    }

    //根据条件查询
    @GetMapping("/list")
    public ResultMe<List<Student>> getList(Student reqVO) {
        List<Student> list = iStudentService.lambdaQuery()
                .eq(reqVO.getCode() != null, Student::getCode, reqVO.getCode())
                .eq(reqVO.getGrade() != null, Student::getGrade, reqVO.getGrade())
                .eq(reqVO.getName() != null, Student::getName, reqVO.getName())
                .list();

        return ResultMe.<List<Student>>success(list);
    }
}

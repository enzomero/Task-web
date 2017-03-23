package com.home.webapp.controller;

import com.home.webapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model){
        service.create(model);
        return "department/departmentForm";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String readAll(ModelMap modelMap){
        service.readAll(modelMap);
        return "department/all";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name){
        service.save(Integer.valueOf(id), name);
        return "redirect:all";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id") int id){
        service.delete(id);
        return "redirect:all";
    }

    @RequestMapping(value = "avgsalary", method = RequestMethod.GET)
    public String getAvgSalary(ModelMap modelMap){
        service.readAvgSalary(modelMap);
        return "department/avgsalary";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = false) String id, Model model){
        service.edit(Integer.valueOf(id), model);
        return "department/departmentForm";
    }
}

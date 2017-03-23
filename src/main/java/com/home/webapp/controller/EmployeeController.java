package com.home.webapp.controller;

import com.home.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model){
        service.create(model);
        return "employee/employeeForm";
    }

    @RequestMapping(value = "all")
    public String readAll(ModelMap modelMap){
        service.readAll(modelMap);
        return "employee/all";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "lName") String lName,
            @RequestParam(value = "mName") String mName,
            @RequestParam(value = "sName") String sName,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "salary") String salary,
            @RequestParam(value = "department") String department
    ){
        if(id.equals(""))
            id="0";
        service.save(Integer.valueOf(id),lName,mName,sName, Date.valueOf(date),Double.valueOf(salary),department);
        return "redirect:all";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id") String id, Model model){
        service.edit(Integer.valueOf(id), model);
        return "employee/employeeForm";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id") int id){
        service.delete(id);
        return "redirect:all";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(){
        return "employee/find";
    }

    @RequestMapping(value = "findByDateRange")
    public String find(@RequestParam(value = "earlyDate") String earlyDate, @RequestParam(value = "oldDate") String oldDate, ModelMap modelMap){
        service.findByDateRange(earlyDate, oldDate, modelMap);
        return "employee/all";
    }
}

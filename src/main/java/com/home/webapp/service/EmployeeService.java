package com.home.webapp.service;

import com.home.webapp.model.Employee;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Service
public class EmployeeService {
    private final RestTemplate rest;

    private EmployeeService(final RestTemplateBuilder builder){
        this.rest = builder.setConnectTimeout(2000)
                .setReadTimeout(2000)
                .build();
    }

    public void create(Model model){
        model.addAttribute("employee", new Employee());
    }

    public void readAll(ModelMap modelMap){
        Employee[] dep = rest.getForObject("http://localhost:9000/employee/all", Employee[].class);
        modelMap.put("all", dep);
    }

    public void save(int id, String lName, String mName, String sName, Date date, double salary, String department){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setlName(lName);
        employee.setmName(mName);
        employee.setsName(sName);
        employee.setDate(date);
        employee.setSalary(salary);
        employee.setDepartment(department);
        if(id==0)
            rest.postForLocation("http://localhost:9000/employee/create", employee);
        if(id!=0)
            rest.put("http://localhost:9000/employee/update", employee);
    }

    public void delete(int id){
        Employee employee = new Employee();
        employee.setId(id);
        rest.postForLocation("http://localhost:9000/employee/delete", employee);
    }

    public void edit(int id, Model model) {
        model.addAttribute("employee", readById(id));
    }

    private Employee readById(int id) {
        return rest.getForObject("http://localhost:9000/employee/{id}", Employee.class, id);
    }

    public void findByDateRange(String earlyDate, String oldDate, ModelMap modelMap) {
        Employee[] emp = new Employee[0];
        if(!earlyDate.equals("") && oldDate.equals("")){
            Date date = Date.valueOf(earlyDate);
            emp = rest.getForObject("http://localhost:9000/employee/findByDate/{date}", Employee[].class, date);
        }
        if(!earlyDate.equals("") && !oldDate.equals("")) {
            Date eDate = Date.valueOf(earlyDate);
            Date oDate = Date.valueOf(oldDate);
            emp = rest.getForObject("http://localhost:9000/employee/findByDateRange/{eDate},{oDate}", Employee[].class, eDate, oDate);
        }
        modelMap.put("all", emp);
    }
}

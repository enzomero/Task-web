package com.home.webapp.controller;

import com.home.webapp.model.Department;
import com.home.webapp.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

    public static final int VALID_ID = 1;
    public static final int DELETE_ID = 2;
    public static final int ZERO_ID = 0;
    public static final int OVER_ID = 999;

    public static final Department EMPTY_DEP = new Department();
    public static final Department NULL_DEP = null;
    public static final Department VALID_DEP = new Department(
            VALID_ID,
            "ValidName");
    public static final Department OVER_ID_DEP = new Department(
            OVER_ID,
            "OverIdValidName");
    public static final Department CREATE_DEP = new Department(
            15,
            "NewName");
    public static final Department INVALID_NAME_DEP = new Department(
            4,
            "");
    public static final Department ZERO_ID_DEP = new Department(
            0,
            "ZeroIdName");


    @Mock
    private DepartmentService mock;

    @InjectMocks
    private DepartmentController sut;

    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void create() throws Exception {
        ModelMap argModel = new ModelMap();
        ModelMap resModel = new ModelMap();
        Department[] dep = new Department[]{VALID_DEP};
        resModel.put("all", dep);

        when(mock.readAll(argModel)).thenReturn(resModel);

        mvc.perform(get("http://localhost:9000/department/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("department/all"));

        verify(mock, times(1)).readAll(argModel);
    }

    @Test
    public void readAll() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getAvgSalary() throws Exception {
    }

    @Test
    public void edit() throws Exception {
    }
}

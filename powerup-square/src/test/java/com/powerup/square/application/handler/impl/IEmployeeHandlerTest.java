package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.mapper.IEmployeeRequestMapper;
import com.powerup.square.application.mapper.IEmployeeResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class IEmployeeHandlerTest {

    @InjectMocks
    EmployeeHandler employeeHandler;


    @Mock
    IEmployeeServicePort iEmployeeServicePort;

    @Mock
    IEmployeeRequestMapper iEmployeeRequestMapper;

    @Mock
    IEmployeeResponseMapper iEmployeeResponseMapper;

    @Test
    void mustSaveEmployee() {
     //Given
        EmployeeRequest employeeRequest= new EmployeeRequest() ;

        Employee employee = iEmployeeRequestMapper.ToEmployee(employeeRequest);

        iEmployeeServicePort.saveEmployee(employee);


    }

    @Test
    void getEmployee() {
    }
}
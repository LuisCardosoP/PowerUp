package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.application.mapper.IEmployeeRequestMapper;
import com.powerup.square.application.mapper.IEmployeeResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.model.Employee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {

    private final IEmployeeServicePort iEmployeeServicePort;
    private final IEmployeeRequestMapper iEmployeeRequestMapper;

    private final IEmployeeResponseMapper iEmployeeResponseMapper;

    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort, IEmployeeRequestMapper iEmployeeRequestMapper, IEmployeeResponseMapper iEmployeeResponseMapper) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.iEmployeeRequestMapper = iEmployeeRequestMapper;
        this.iEmployeeResponseMapper = iEmployeeResponseMapper;
    }

    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = iEmployeeRequestMapper.ToEmployee(employeeRequest);
       // employee.setIdRestaurant(employee.getIdRestaurant());
        // Setting the same idUser from the Users table
      //  employee.setIdUser(employee.getIdUser());

        iEmployeeServicePort.saveEmployee(employee);
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        return null;
    }
}

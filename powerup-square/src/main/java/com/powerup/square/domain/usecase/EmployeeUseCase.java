package com.powerup.square.domain.usecase;

import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class EmployeeUseCase implements IEmployeePersistencePort {

    private final IEmployeePersistencePort employeePersistencePort;

    public EmployeeUseCase(IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }
    @Override
    public void saveEmployee(Employee employee) {
        employeePersistencePort.saveEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public Employee getEmployee(Long id) {
        return null;
    }
}

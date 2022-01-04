package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    public EmployeeDAOJpaImpl() {}

    @Override
    public List<Employee> findAll() {
        Query theQuery =
                entityManager.createQuery("from Employee");

        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int id) {
        Query theQuery = entityManager.createQuery(
                "delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", id);
        theQuery.executeUpdate();
    }
}

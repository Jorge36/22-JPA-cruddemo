package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
				
		// create a query
		Query query = entityManager.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = query.getResultList();
		
		// return the results
		return employees;
			
	}

	@Override
	public Employee findById(int id) {
		// get the employee and return the employee
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void save(Employee employee) {
		// save the employee
		Employee employeeTemp = entityManager.merge(employee);
		
		// update with id from db, so we can get generated id for save/insert
		employee.setId(employeeTemp.getId());
		
	}

	@Override
	public void deleteById(int id) {
		
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		// set parameter
		query.setParameter("employeeId", id);
		// execute query#
		query.executeUpdate();
		
	}

}

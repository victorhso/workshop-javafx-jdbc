package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

public class DepartmentService {
	
	//Criando e injentando a dependencia
	private DepartmentDAO dao = DaoFactory.createDepartmentDao(); 
	
	public List<Department> findAll(){
		return dao.findAll();
	}
}

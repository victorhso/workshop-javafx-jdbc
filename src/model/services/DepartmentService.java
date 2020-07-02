package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll(){
		//Dados "mocados". MOCK = dados de mentira.
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Livros"));
		list.add(new Department(2, "Música"));
		list.add(new Department(3, "Desenhos"));
		return list;
	}
}

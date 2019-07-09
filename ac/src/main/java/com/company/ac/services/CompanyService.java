package com.company.ac.services;

import java.util.List;

import com.company.ac.models.company.Company;

public interface CompanyService {
	
	Company create(Company company);
	boolean delete(long id);
	List<Company> getCompanyList();
	
}

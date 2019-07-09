package com.company.ac.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.models.company.Company;
import com.company.ac.services.CompanyService;
import com.company.ac.services.impl.CompanyServiceImpl;

@Path("company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {
	
	private CompanyService companySerive = new CompanyServiceImpl();
	
	@POST	
	public Company create(Company company) {
		Company comp = companySerive.create(company);
		 
		return comp;
	}
	
	@GET
	public List<Company> getCompanies(){
		return companySerive.getCompanyList();
	}
	
	@DELETE
	@Path("/{id}")
	public boolean delete(@PathParam("id") int id) {
		
		return companySerive.delete(id);
	}
}

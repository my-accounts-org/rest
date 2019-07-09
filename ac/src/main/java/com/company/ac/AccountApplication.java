package com.company.ac;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.company.ac.resources.AuthenticationResource;
import com.company.ac.resources.CompanyResource;

@ApplicationPath("api")
public class AccountApplication extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features 
        //classes.add(MultiPartFeature.class);       
        classes.add(AuthenticationResource.class);
        classes.add(CompanyResource.class);
        return classes;
    }
}

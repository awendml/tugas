package org.acme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.agroal.api.AgroalDataSource;

@Path("/employee")
public class ExampleResource {

	@Inject
	AgroalDataSource agroalDataSource;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String employee() throws SQLException {
		Connection conn= agroalDataSource.getConnection();
		Statement statement =conn.createStatement();
		statement.execute("SELECT * FROM employee WHERE (id IN (SELECT manager_id FROM employee))ORDER BY employee.id");
		return "success";
         
    }
}
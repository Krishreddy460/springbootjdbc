package com.springboot.workers.jdbcexample.repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.workers.jdbcexample.dao.WorkerDAO;
import com.springboot.workers.jdbcexample.model.Worker;
import com.springboot.workers.jdbcexample.util.DatabaseConnection;
@Repository
public class WorkerRepository implements WorkerDAO{


	Connection connection;
	public WorkerRepository() throws SQLException
	{
		this.connection=DatabaseConnection.getConnection();
	}
	@Override
	public int add(Worker worker) throws SQLException {
		int workerId = worker.getWorkerId();
		String firstName = worker.getFirstName();
		String lastName = worker.getLastName();
		int salary = worker.getSalary();
		Date date = worker.getJoiningDate();
		String department = worker.getDepartment();
		String email = worker.getEmail();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String joiningDate = sdf.format(date);
		String query = String.format("INSERT INTO worker VALUES(%d,'%s','%s',%d,'%s','%s','%s');", workerId,
				firstName,
				lastName, salary, joiningDate, department, email);
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(query);
		
		}}

		@Override
		public void delete(int workerId) throws SQLException 
	  	{
	  		String str="delete from worker where worker_id="+workerId;
	  		Statement st=connection.createStatement();
	  		boolean b=st.execute(str);
	  		System.out.print("is deletion done ?"+b);		
	  		
	  	}
		 public Worker getWorker(int workerId) throws SQLException {
	         // String query = "SELECT * FROM worker WHERE worker_id=?";
	          
	          String query="select * from worker where worker_id="+workerId;
	          Worker res = null;
	        // PreparedStatement ps = connection.prepareStatement(query); 
	          //    ps.setInt(1, workerId);
	            //  ResultSet result = ps.executeQuery();
	          Statement st=connection.createStatement();
	          ResultSet result =st.executeQuery(query);
	          
	              while (result.next()) {
	                  int workerId1 = result.getInt("worker_id");
	                  String firstName = result.getString("first_name");
	                  String lastName = result.getString("last_name");
	                  int salary = result.getInt("salary");
	                  Date date = result.getDate("joining_date");
	                  String department = result.getString("department");
	                  String email = result.getString("email");
	                  res = new Worker(workerId1, firstName, lastName, salary, date, department, email);
	              }
	          
	          return res;
	      }
		@Override

		public List<Worker> getWorkers() throws SQLException
		{
			// TODO Auto-generated method stub

			List<Worker> list=new ArrayList<>();
			String s="select * from worker";
			Statement st=connection.createStatement();
			ResultSet result=st.executeQuery(s);
			while(result.next())
			{
				int workerId1 = result.getInt("worker_id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				int salary = result.getInt("salary");
				Date date = result.getDate("joining_date");
				String department = result.getString("department");
				String email = result.getString("email");
				list.add( new Worker(workerId1, firstName, lastName, salary, date, department, email));

			}
			return list;
		}
		
		
		
		@Override
		public void update(Worker worker) throws SQLException {
	  		// TODO Auto-generated method stub
	  		
	  		
	  		 int workerId = worker.getWorkerId();
	         String firstName = worker.getFirstName();
	         String lastName = worker.getLastName();
	         int salary = worker.getSalary();
	         Date date = worker.getJoiningDate();
	         String department = worker.getDepartment();
	         String email = worker.getEmail();
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         String joiningDate = sdf.format(date);
	         String query=String.format("update worker set worker_id=%d,first_name='%s',last_name='%s',salary=%d,joining_date='%s',department='%s',email='%s' where worker_id=%d",workerId,firstName,
	        		 lastName,salary,joiningDate,department,email,workerId);
	         Statement statement = connection.createStatement();
	             int res = statement.executeUpdate(query);
	             System.out.println(res + " row get Updated");
	  	}
}

	  	

package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(" INSERT INTO department (Name) VALUES (?)", 
					java.sql.Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id  = rs.getInt(1);
					obj.setId(id);
					
					DB.closeResutSet(rs);
				}else {
					throw new DbException("Unexpected error! No rows affected");
				}
				
			}
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(" UPDATE department SET Name = ? WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate();
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(" DELETE FROM department Name WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			
			if (rows == 0) {
				throw new DbException("Non-existent Id! No Id was deleted!");
			}
			
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new DbException("Error! You are probably trying to delete an Id that contains employees, "
					+ "this is not possible. Remove the employees from the department and try again!");
		}catch (SQLException e1) {
			throw new DbException(e1.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Department dep = null;
		try {
			
			st = conn.prepareStatement("SELECT * FROM department WHERE department.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
			 dep = instantiateDepartment(rs);
			}
			
			return dep;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResutSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		Department dep = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM department ");
			rs = st.executeQuery();
			
			while (rs.next()) {
			 dep = instantiateDepartment(rs);
			 list.add(dep);
			}
			
			return list;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResutSet(rs);
		}
		
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}



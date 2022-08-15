package acetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acetest.dto.StudentRequestDTO;
import acetest.dto.StudentResponseDTO;

public class StudentDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public void insertData(StudentRequestDTO dto) {
		String sql="INSERT INTO students(id,name,dob,gender,phone,education,course)"+
				"values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentId());
			ps.setString(2,dto.getStudentName());
			ps.setString(3,dto.getStudentBirthday());
			ps.setString(4,dto.getStudentGender());
			ps.setString(5,dto.getStudentPhone());
			ps.setString(6,dto.getStudentEducation());
			ps.setInt(7,dto.getStudentAttend());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<StudentResponseDTO> selectAll(){
		ArrayList<StudentResponseDTO> list=new ArrayList<>();
		String sql="select * from students";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(sql);
			while (rs.next()) {
				StudentResponseDTO dto=new StudentResponseDTO();
				dto.setStudentId(rs.getString("id"));
				dto.setStudentName(rs.getString("name"));
				dto.setStudentBirthday(rs.getString("dob"));
				dto.setStudentGender(rs.getString("gender"));
				dto.setStudentPhone(rs.getString("phone"));
				dto.setStudentEducation(rs.getString("education"));
				dto.setStudentAttend(rs.getInt("course"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteData(StudentRequestDTO dto) {
		String sql="DELETE FROM students WHERE id=?";			
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("database error");
		}
	}
	
	public void updateData(StudentRequestDTO dto) {
		String sql="UPDATE students SET name=?,dob=?,gender=?,phone=?,education=?,course=?"+
						"WHERE id=?";			
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentName());
			ps.setString(2,dto.getStudentBirthday());
			ps.setString(3,dto.getStudentGender());
			ps.setString(4,dto.getStudentPhone());
			ps.setString(5,dto.getStudentEducation());
			ps.setInt(6,dto.getStudentAttend());
			ps.setString(7,dto.getStudentId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("database error");
		}
	}
	
	public StudentResponseDTO selectOne(StudentRequestDTO dto) {
		StudentResponseDTO res=new StudentResponseDTO();
		String sql="SELECT * FROM students WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentId());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res.setStudentId(rs.getString("id"));
				res.setStudentName(rs.getString("name"));
				res.setStudentBirthday(rs.getString("dob"));
				res.setStudentGender(rs.getString("gender"));
				res.setStudentPhone(rs.getString("phone"));
				res.setStudentEducation(rs.getString("education"));
				res.setStudentAttend(rs.getInt("course"));
				
			}
		} catch (SQLException e) {
			System.out.println("database error");
		}
		return res;
	}
	
	public List<StudentResponseDTO> searchData(StudentRequestDTO dto) {
		List<StudentResponseDTO> list=new ArrayList<>();
		String sql="SELECT * FROM students WHERE id=? OR name=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentId());
			ps.setString(2,dto.getStudentName());
			//ps.setInt(3,dto.getStudentAttend());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res=new StudentResponseDTO();
				res.setStudentId(rs.getString("id"));
				res.setStudentName(rs.getString("name"));
				res.setStudentBirthday(rs.getString("dob"));
				res.setStudentGender(rs.getString("gender"));
				res.setStudentPhone(rs.getString("phone"));
				res.setStudentEducation(rs.getString("education"));
				res.setStudentAttend(rs.getInt("course"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("database error");
		}
		return list;
	}

	
}

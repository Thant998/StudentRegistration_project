package acetest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import acetest.dao.StudentDAO;
import acetest.dto.StudentRequestDTO;
import acetest.dto.StudentResponseDTO;
import acetest.model.StudentBean;

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UpdateStudentServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentRequestDTO dto=new StudentRequestDTO();
		dto.setStudentId(request.getParameter("id"));
		StudentDAO dao=new StudentDAO();
		StudentResponseDTO res=dao.selectOne(dto);
		request.setAttribute("res",res);
		request.getRequestDispatcher("STU002.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean bean=new StudentBean();
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String education=request.getParameter("education");
		int course=Integer.parseInt(request.getParameter("attend"));
		bean.setId(id);
		bean.setName(name);
		bean.setBirthday(dob);
		bean.setGender(gender);
		bean.setPhone(phone);
		bean.setEducation(education);
		bean.setCourse(course);
		if (  bean.getName().equals("") || bean.getBirthday().equals("")  ||
				bean.getGender().equals("")  || bean.getPhone().equals("") || bean.getEducation().equals("") ||
		bean.getCourse()<0 ) {
			request.setAttribute("bean",bean);
			request.setAttribute("error", "Fill cannot be blank!!!");
			request.getRequestDispatcher("STU002.jsp").forward(request,response);
		}else {
			StudentDAO dao=new StudentDAO();
			StudentRequestDTO dto=new StudentRequestDTO();
			dto.setStudentId(bean.getId());
			dto.setStudentName(bean.getName());
			dto.setStudentBirthday(bean.getBirthday());
			dto.setStudentGender(bean.getGender());
			dto.setStudentPhone(bean.getPhone());
			dto.setStudentEducation(bean.getEducation());
			dto.setStudentAttend(bean.getCourse());
			dao.updateData(dto);
			response.sendRedirect("DisplayStudentServlet");
		}
	}
}

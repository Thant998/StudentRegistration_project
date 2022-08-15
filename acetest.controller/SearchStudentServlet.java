package acetest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acetest.dao.StudentDAO;
import acetest.dto.StudentRequestDTO;
import acetest.dto.StudentResponseDTO;
import acetest.model.StudentBean;

@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao=new StudentDAO();
		StudentBean bean=new StudentBean();
		StudentRequestDTO req=new StudentRequestDTO();
		if (request.getParameter("id").equals("")) {
			bean.setId("0");
		}else {
			bean.setId(request.getParameter("id"));
		}
		bean.setName(request.getParameter("name"));
		//bean.setCourse(Integer.parseInt(request.getParameter("course")));
		req.setStudentId(bean.getId());
		req.setStudentName(bean.getName());
		//req.setStudentAttend(bean.getCourse());
		//if (dao.searchData(req).isEmpty()) {
			//request.setAttribute("error","Filled cannot be blank!!");
			//request.getRequestDispatcher("STU003.jsp").forward(request,response);
		//}else {
			request.setAttribute("list",dao.searchData(req));
			List<StudentResponseDTO> list=dao.searchData(req);
			list.forEach(m -> System.out.println(m));
			request.getRequestDispatcher("STU003.jsp").include(request,response);
			
		//}
	}
}

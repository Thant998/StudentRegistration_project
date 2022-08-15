package acetest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acetest.dao.StudentDAO;
import acetest.dto.StudentResponseDTO;


@WebServlet("/DisplayStudentServlet")
public class DisplayStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DisplayStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao=new StudentDAO();
		ArrayList<StudentResponseDTO> list=dao.selectAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("STU003.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

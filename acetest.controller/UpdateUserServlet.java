package acetest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acetest.dao.UserDAO;
import acetest.dto.UserRequestDTO;
import acetest.dto.UserResponseDTO;
import acetest.model.UserBean;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDTO dto=new UserRequestDTO();
		dto.setUserId(request.getParameter("id"));
		UserDAO dao=new UserDAO();
		UserResponseDTO res=dao.selectOne(dto);
		request.setAttribute("res",res);
		request.getRequestDispatcher("USR002.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean bean=new UserBean();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int role=Integer.parseInt(request.getParameter("role"));
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		bean.setUserEmail(email);
		bean.setUserPassword(password);
		bean.setUserRole(role);
		bean.setUserName(name);
		bean.setUserId(id);
		if (bean.getUserEmail().equals("") || bean.getUserName().equals("") || 
				bean.getUserPassword().equals("") || bean.getUserRole()<0 || bean.getUserId().equals("")) {
			request.setAttribute("bean",bean);
			request.setAttribute("error", "Fill cannot be blank!!!");
			request.getRequestDispatcher("USR001.html");
		}else {
			UserDAO dao=new UserDAO();
			UserRequestDTO dto=new UserRequestDTO();
			dto.setUserEmail(bean.getUserEmail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			dto.setUserId(bean.getUserId());
			dto.setUserName(bean.getUserName());
			dao.updateData(dto);
			response.sendRedirect("DisplayUserServlet");
		}
	}

}

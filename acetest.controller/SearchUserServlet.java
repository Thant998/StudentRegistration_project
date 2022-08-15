package acetest.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acetest.dao.UserDAO;
import acetest.dto.UserRequestDTO;
import acetest.dto.UserResponseDTO;
import acetest.model.UserBean;

@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao=new UserDAO();
		UserBean bean=new UserBean();
		if (request.getParameter("id").equals("")) {
			bean.setUserId("0");
		}else {
			bean.setUserId(request.getParameter("id"));
		}
		bean.setUserName(request.getParameter("name"));
		UserRequestDTO req=new UserRequestDTO();
		req.setUserId(bean.getUserId());
		req.setUserName(bean.getUserName());
		if (dao.searchData(req).isEmpty()) {
			request.setAttribute("error","Filled cannot be blank!!");
			request.getRequestDispatcher("USR003.jsp").forward(request,response);
		}else {
			request.setAttribute("list",dao.searchData(req));
			List<UserResponseDTO> list=dao.searchData(req);
			list.forEach(m -> System.out.println(m));
			request.getRequestDispatcher("USR003.jsp").include(request,response);
			
		}
		
	}

}

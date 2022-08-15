package acetest.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acetest.dao.UserDAO;
import acetest.dto.UserResponseDTO;
import acetest.model.LoginBean;

@WebServlet("/AddLoginServlet")
public class AddLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		LoginBean bean=new LoginBean();
		bean.setLoginName(request.getParameter("name"));
		bean.setLoginPassword(request.getParameter("password"));
		if (bean.getLoginName().equals("") || bean.getLoginPassword().equals("")) {
			request.getRequestDispatcher("LGN001.html").forward(request, response);
		}else {
			UserDAO dao=new UserDAO();
			ArrayList<UserResponseDTO> list = dao.selectUser(bean);
			if(list.size() > 0) {
				request.getRequestDispatcher("MNU001.html").forward(request,response);
			}else {
				out.println("<h1>Faliled</h2>");
				
			}
		}
	}

}

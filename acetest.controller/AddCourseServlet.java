package acetest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CourseBean bean=new CourseBean();
		//bean.setId(request.getParameter("id"));
		//bean.setName(request.getParameter("name"));
		//if (bean.getId().equals("") || bean.getName().equals("")) {
			//request.getRequestDispatcher("BUD003.jsp").forward(request,response);
		//}else {
			//List<CourseBean> courseList=(List<CourseBean>) request.getServletContext().getAttribute("list");
			//if (courseList==null) {
				//courseList=new ArrayList<>();
			//}
			//courseList.add(bean);
			//request.getServletContext().setAttribute("courselist",courseList);
			request.getRequestDispatcher("STU001.jsp").forward(request,response);
		//}
	}

}

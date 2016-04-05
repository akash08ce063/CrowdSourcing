package edu.crowdsource.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import edu.crowdsource.model.UserInformation;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response){
    		String user_name = request.getParameter("user_name");
    		String password = request.getParameter("password");
    		DAOUtility dao_utility = new DAOUtility();
    		Session ss = dao_utility.getHibernateSession();
    		try {
				PrintWriter out = response.getWriter();
				Criteria cr = ss.createCriteria(UserInformation.class);
				cr.add(Restrictions.eq("userName", user_name));
				cr.add(Restrictions.eq("password", password)); 
	    		List<UserInformation> results = cr.list();
	    		
	    		try {
	    			if(results.size() > 0){
	    				HttpSession session = request.getSession();
	    				session.setAttribute("user_information", results.get(0));
	    				
	    				ss.close();
	    				ss.getSessionFactory().close();
	    	    		RequestDispatcher rd = request.getRequestDispatcher("DashboardCrowdSource");
	    	    		rd.forward(request, response);
	    	    	} else {
	    	    		
	    	    		request.setAttribute("user_login_message", "Please Enter Correct Username and Password");
	    	    		RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/user_login.jsp");
			    		rd.forward(request, response);
			    	}
				} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
	    		
	    		
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    		
    		
    		
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/user_login.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleLogin(request, response);
	}

}

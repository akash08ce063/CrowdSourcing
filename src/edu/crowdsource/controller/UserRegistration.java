package edu.crowdsource.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;


import edu.crowdsource.model.UserInformation;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    private JSONObject handleUserRegistration(HttpServletRequest request, HttpServletResponse response, Session ss){
    		
    	JSONObject registration_obj  = new JSONObject();
    	
    	try{
    	
    		String user_name = request.getParameter("user_name");
    		String password = request.getParameter("password");
    		String user_address = request.getParameter("user_address");
    		int user_type = Integer.parseInt(request.getParameter("user_type"));
    		
    		UserInformation user_info = new UserInformation();
    		user_info.setUserName(user_name);
    		user_info.setPassword(password);
    		user_info.setAddress(user_address);
    		user_info.setGeoLocation("");
    		user_info.setType(user_type);
    		ss.save(user_info);
    		
    		registration_obj.put("error_code", 0);
    		registration_obj.put("message", "Successfully Registered !");
    		
    	} catch (Exception e){
    		
    		
    		System.out.println( "--" + e.getMessage());
    		
    		try {
				registration_obj.put("error_code", 1);
				registration_obj.put("message", "Error in Registering !");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    		
    		return registration_obj;
    		
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/user_registration.jsp");
		rd.forward(request, response);
		 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		
		  response.setContentType("application/json; charset=UTF-8");
		  DAOUtility dao_util = new DAOUtility();
 		  Session ss = dao_util.getHibernateSession();
 		  ss.beginTransaction(); 
		  JSONObject result_obj = handleUserRegistration(request, response, ss);
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  ss.getTransaction().commit();
	      ss.close();
		  ss.getSessionFactory().close();
		  out.close();
		  
		  
		
		
	}

}

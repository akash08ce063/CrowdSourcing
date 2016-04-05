package edu.crowdsource.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserInformation;

/**
 * Servlet implementation class UpdateTaskLog
 */
@WebServlet("/UpdateTaskLog")
public class UpdateTaskLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private JSONObject handleUpdatingTaskLog(HttpServletRequest request, HttpServletResponse response, Session ss){
    	
    	double given_rating = Double.valueOf(request.getParameter("given_rating"));
    	String task_status = request.getParameter("task_status");
    	int client_id =  Integer.parseInt(request.getParameter("client_id"));
    	int worker_id = Integer.parseInt(request.getParameter("worker_id"));
    	
    	
    	Criteria cr = ss.createCriteria(TaskLog.class);
		cr.add(Restrictions.eq("clientId", client_id));
		cr.add(Restrictions.eq("workerId", worker_id)); 
		List<TaskLog> results = cr.list();
		
		if(results.size() > 0){
			results.get(0).setGivenRating(given_rating);
			results.get(0).setTaskStatus(task_status);
			ss.save(results.get(0));
		}
		
		
		
		return new JSONResponse().createSuccessResponse();
		
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setContentType("application/json; charset=UTF-8");
		  DAOUtility dao_util = new DAOUtility();
		  Session ss = dao_util.getHibernateSession();
		  ss.beginTransaction(); 
		  JSONObject result_obj = handleUpdatingTaskLog(request, response, ss);
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  ss.close();
		  ss.getSessionFactory().close();
		  out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

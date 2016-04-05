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
import org.json.JSONException;
import org.json.JSONObject;

import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserInformation;

/**
 * Servlet implementation class FetchAllAssignedTasks
 */
@WebServlet("/FetchAllAssignedTasks")
public class FetchAllAssignedTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAllAssignedTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		  response.setContentType("application/json; charset=UTF-8");
		  DAOUtility dao_util = new DAOUtility();
		  Session ss = dao_util.getHibernateSession();
		  ss.beginTransaction(); 
		  JSONArray result_obj = handleFetchAllAssignedTasks(request, response, ss);
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  ss.close();
		  ss.getSessionFactory().close();
		  out.close();
		  
		  
		  
	}

	public JSONArray handleFetchAllAssignedTasks(HttpServletRequest request, HttpServletResponse response, Session ss){
		JSONArray notification_array  = new JSONArray();
		int client_id = Integer.parseInt(request.getParameter("client_id"));
		Criteria cr = ss.createCriteria(TaskLog.class);
		cr.add(Restrictions.eq("clientId", client_id)); 
		cr.add(Restrictions.eq("taskStatus", "progress"));
		List<TaskLog> results = cr.list();
		for(int i = 0 ; i < results.size() ; i++){
			JSONObject notification_obj = new JSONObject();
			try {
				UserInformation user_info = new DAOUtility().fetchUserByUserID(results.get(i).getWorkerId(), ss);
				notification_obj.put("task_log_id", results.get(i).getId());
				notification_obj.put("task_name", results.get(i).getTaskInformation().getTaskName());
				notification_obj.put("worker_id", results.get(i).getWorkerId());
				if(user_info == null)
					notification_obj.put("client_username", "");
				else
					notification_obj.put("client_username", user_info.getUserName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			notification_array.put(notification_obj);
		}
		
		
		return notification_array;
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

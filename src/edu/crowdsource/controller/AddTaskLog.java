package edu.crowdsource.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.crowdsource.model.TaskLog;

/**
 * Servlet implementation class AddTaskLog
 */
@WebServlet("/AddTaskLog")
public class AddTaskLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    private JSONObject createSuccessResponse(){
    	JSONObject result = new JSONObject();
    	try {
			result.put("error_code", 0);
			result.put("message" , "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return result;
    }
    
    private JSONObject handleAddingTaskLog(HttpServletRequest request, HttpServletResponse response, Session ss){
    	
    	int client_id = Integer.parseInt(request.getParameter("client_id"));
    	int worker_id = Integer.parseInt(request.getParameter("worker_id"));
    	int task_id = Integer.parseInt(request.getParameter("task_id"));
    	
    	TaskLog task_log = new TaskLog();
    	task_log.setClientId(client_id);
    	task_log.setWorkerId(worker_id);
    	task_log.setGivenRating(0);
    	task_log.setTaskStatus("connect");
    	task_log.setTaskInformation(new DAOUtility().fetchTaskInfoByID(task_id, ss));
    	ss.save(task_log);
    	
    	return createSuccessResponse();
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setContentType("application/json; charset=UTF-8");
		  DAOUtility dao_util = new DAOUtility();
		  Session ss = dao_util.getHibernateSession();
		  ss.beginTransaction(); 
		  JSONObject result_obj = handleAddingTaskLog(request, response, ss);
		  PrintWriter out = response.getWriter();
		  ss.getTransaction().commit();
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

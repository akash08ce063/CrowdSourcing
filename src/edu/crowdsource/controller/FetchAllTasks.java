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

import edu.crowdsource.model.TaskInformation;
import edu.crowdsource.model.UserInformation;

/**
 * Servlet implementation class FetchAllTasks
 */
@WebServlet("/FetchAllTasks")
public class FetchAllTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAllTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public JSONArray fetchAllTasks(HttpServletRequest request, HttpServletResponse response, Session ss){
    	JSONArray result_array = new JSONArray();
    	Criteria cr = ss.createCriteria(TaskInformation.class);
		List<TaskInformation> tasks_list = cr.list();
		for(int i = 0 ; i < tasks_list.size() ; i++){
			JSONObject task_info = new JSONObject();
			try {
				task_info.put("task_id", tasks_list.get(i).getTaskId());
				task_info.put("task_name", tasks_list.get(i).getTaskName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			result_array.put(task_info);
			
		}
		
		return result_array;
		
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/json; charset=UTF-8");
		  DAOUtility dao_util = new DAOUtility();
		  Session ss = dao_util.getHibernateSession();
		  ss.beginTransaction(); 
		  JSONArray result_obj = fetchAllTasks(request, response, ss);
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

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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserSkill;

/**
 * Servlet implementation class SearchPotentialWorker
 */
@WebServlet("/SearchPotentialWorker")
public class SearchPotentialWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPotentialWorker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/searching_potential_workers.jsp");
	     rd.forward(request, response);
		
		  
		
	}
	
	
	private JSONArray handleFindingPotentialWorker(HttpServletRequest request, HttpServletResponse response, Session ss){
		int num_workers = Integer.parseInt(request.getParameter("number_of_workers"));
		double total_budget = Double.valueOf(request.getParameter("budget"));
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		JSONArray final_array = new JSONArray();
		double per_budget = total_budget / num_workers;
		
		Criteria cr = ss.createCriteria(UserSkill.class);
		cr.add(Restrictions.le("charges", per_budget));
		List<UserSkill> results = cr.list();
		
		for(int i = 0 ; i < results.size() ; i++){
			
			UserSkill user_skill = results.get(i);
			if(user_skill.getTaskInformation().getTaskId() == task_id){
				JSONObject person_obj = new JSONObject();
				try {
					person_obj.put("user_name", user_skill.getUserInformation().getUserName());
					person_obj.put("user_id", user_skill.getUserInformation().getUserId());
					person_obj.put("budget", user_skill.getCharges());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				final_array.put(person_obj);
				
		   }
			
		}
		
		
		return final_array;
		
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
		  JSONArray result_obj = handleFindingPotentialWorker(request, response, ss);
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  ss.close();
		  ss.getSessionFactory().close();
		  out.close();
	}

}

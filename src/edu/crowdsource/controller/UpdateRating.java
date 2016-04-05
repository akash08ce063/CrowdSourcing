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
import org.json.JSONObject;

import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserInformation;
import edu.crowdsource.model.UserSkill;

/**
 * Servlet implementation class UpdateRating
 */
@WebServlet("/UpdateRating")
public class UpdateRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRating() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private JSONObject handleUpdateRating(HttpServletRequest request, HttpServletResponse response, Session ss){
    	int task_log_id = Integer.parseInt(request.getParameter("task_log_id"));
    	double rating = Double.valueOf(request.getParameter("rating"));
    	
    	System.out.println( "log_id----"  + task_log_id);
    	
    	
    	Criteria cr = ss.createCriteria(TaskLog.class);
		cr.add(Restrictions.eq("id", task_log_id)); 
		List<TaskLog> results = cr.list();
		if(results.size() > 0){
			results.get(0).setGivenRating(rating);
			ss.save(results.get(0));
			updateExperienceAndRatingOfWorker(results.get(0), rating, ss); 
		}
		
		
		
    	return new JSONResponse().createSuccessResponse();
    }
    
  
    private void updateExperienceAndRatingOfWorker( TaskLog task_log , double rating ,Session ss){
    	int worker_id  = task_log.getWorkerId();
    	int task_id = task_log.getTaskInformation().getTaskId();
    	Criteria cr = ss.createCriteria(TaskLog.class);
		cr.add(Restrictions.eq("workerId", worker_id)); 
		cr.add(Restrictions.eq("taskStatus", "complete"));
		List<TaskLog> results = cr.list();
		int total_exp = results.size();
		double credibility = 0;
		if(results.size() > 1){
			for(int i = 0 ; i < results.size() ; i++){
				credibility = credibility + results.get(i).getGivenRating();
			}
			
			credibility = credibility / results.size() ;
		}
		
	    UserInformation user_info =	new DAOUtility().fetchUserByUserID(worker_id, ss);
	    List<UserSkill> user_skill_list   = user_info.getUserSkills();
	    for(int i = 0 ; i < user_skill_list.size() ; i++){
	    	if( user_skill_list.get(i).getTaskInformation().getTaskId() == task_id){
	    		user_skill_list.get(i).setTaskExperience(total_exp);
	    		if(credibility > 1.5)
	    			user_skill_list.get(i).setTaskCredibility(credibility);
	    		ss.save(user_skill_list.get(i));
	    	}
	    }
    	
    }
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
		 
		
		 request.setAttribute("task_log_id", request.getParameter("task_log_id"));
		 RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/rating_assignment.jsp");
	     rd.forward(request, response);
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  DAOUtility dao_util = new DAOUtility();
		  Session ss = dao_util.getHibernateSession();
		  ss.beginTransaction(); 
		  JSONObject result_obj = handleUpdateRating(request, response, ss);
		  ss.getTransaction().commit();
		  ss.close();
		  ss.getSessionFactory().close();
		  
		  RequestDispatcher rd = request.getRequestDispatcher("edu.crowdsource.view/dashboard.jsp");
	      rd.forward(request, response);
	}

}

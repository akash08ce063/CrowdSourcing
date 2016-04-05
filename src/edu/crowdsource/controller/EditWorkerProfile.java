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

import edu.crowdsource.model.TaskInformation;
import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserSkill;

/**
 * Servlet implementation class EditWorkerProfile
 */
@WebServlet("/EditWorkerProfile")
public class EditWorkerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditWorkerProfile() {
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
		  JSONObject result_obj = handleWorkerProfileEdit(request, response, ss);
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  ss.close();
		  ss.getSessionFactory().close();
		  out.close();
	}
	
	private TaskInformation fetchTaskInformation(int task_id, Session ss){
		Criteria cr = ss.createCriteria(TaskInformation.class);
		cr.add(Restrictions.eq("taskId", task_id));
		List<UserSkill> user_skill_list = cr.list();
		
		return user_skill_list.get(0).getTaskInformation();
		
	}
	
	private JSONObject handleWorkerProfileEdit(HttpServletRequest request, HttpServletResponse response, Session ss){
			int worker_id = Integer.parseInt(request.getParameter("worker_id"));
			double charges = Double.valueOf(request.getParameter("charges"));
			int task_id = Integer.parseInt(request.getParameter("task_id"));
			double task_availability = Double.valueOf(request.getParameter("task_availability"));
			Criteria cr = ss.createCriteria(UserSkill.class);
			cr.add(Restrictions.eq("user_id", worker_id));
			List<UserSkill> user_skill_list = cr.list();
			if(user_skill_list.size() > 0){
				user_skill_list.get(0).setCharges(charges);
				user_skill_list.get(0).setTaskInformation(fetchTaskInformation(task_id, ss));
				user_skill_list.get(0).setTaskCredibility(0.5);
				user_skill_list.get(0).setTaskExperience(0);
				user_skill_list.get(0).setTaskAvailability(task_availability);
				ss.save(user_skill_list.get(0));
			}
			
			return new JSONResponse().createSuccessResponse();
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

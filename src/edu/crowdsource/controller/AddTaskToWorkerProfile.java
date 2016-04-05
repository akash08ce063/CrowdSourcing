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
import org.json.JSONObject;

import edu.crowdsource.model.TaskInformation;
import edu.crowdsource.model.UserInformation;
import edu.crowdsource.model.UserSkill;

/**
 * Servlet implementation class AddTaskToWorkerProfile
 */
@WebServlet("/AddTaskToWorkerProfile")
public class AddTaskToWorkerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskToWorkerProfile() {
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
		  ss.getTransaction().commit();
		  ss.close();
		  ss.getSessionFactory().close();
		  
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
		  out.close();
	}
	
	private TaskInformation fetchTaskInformation(int task_id, Session ss){
		Criteria cr = ss.createCriteria(TaskInformation.class);
		cr.add(Restrictions.eq("taskId", task_id));
		List<TaskInformation> task_list = cr.list();
		
		return task_list.get(0);
		
	}

	
	private JSONObject handleWorkerProfileEdit(HttpServletRequest request, HttpServletResponse response, Session ss){
		int worker_id = Integer.parseInt(request.getParameter("worker_id"));
		double charges = Double.valueOf(request.getParameter("charges"));
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		double task_availability = Double.valueOf(request.getParameter("task_availability"));
		UserSkill user_skill = new UserSkill();
		user_skill.setCharges(charges);
		user_skill.setTaskExperience(0);
		user_skill.setTaskAvailability(task_availability);
		user_skill.setTaskCredibility(1.5);
		user_skill.setTaskInformation(fetchTaskInformation(task_id, ss));
		user_skill.setUserInformation(new DAOUtility().fetchUserByUserID(worker_id, ss));
		ss.save(user_skill);
	
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

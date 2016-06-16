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

import edu.crowdsource.model.TaskLog;

/**
 * Servlet implementation class AcceptTasks
 */
@WebServlet("/AcceptTasks")
public class AcceptTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private JSONObject handleAcceptTask(HttpServletRequest request, HttpServletResponse response, Session ss){
    	int task_log_id = Integer.parseInt(request.getParameter("task_log_id"));
    	Criteria cr = ss.createCriteria(TaskLog.class);
		cr.add(Restrictions.eq("id", task_log_id)); 
		List<TaskLog> results = cr.list();
		if(results.size() > 0){
			results.get(0).setTaskStatus("progress");
			ss.save(results.get(0));
		}
		
    	return new JSONResponse().createSuccessResponse();
    }
    
    public void writeAcceptanceTest(){
    	System.out.println("This is very weired test");
    	System.out.println("Perfo This is big change ce test");

    	int a = 6;
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
		  JSONObject result_obj = handleAcceptTask(request, response, ss);
		  ss.getTransaction().commit();
		  ss.close();
		  ss.getSessionFactory().close();
		  
		  PrintWriter out = response.getWriter();
		  out.println(result_obj);
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

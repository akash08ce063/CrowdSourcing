package edu.crowdsource.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import edu.crowdsource.model.TaskInformation;
import edu.crowdsource.model.TaskLog;
import edu.crowdsource.model.UserInformation;

public class DAOUtility {
	
	public Session getHibernateSession(){
    	Configuration configuration=new Configuration();  
 		configuration.configure();  
 		ServiceRegistry sr= new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();  
 		SessionFactory sf=configuration.buildSessionFactory(sr);  
 		Session ss=sf.openSession();  
 		return ss;
    	
	}
	
	public UserInformation fetchUserByUserID( int user_id ,Session ss){
		Criteria cr = ss.createCriteria(UserInformation.class);
		cr.add(Restrictions.eq("userId", user_id)); 
		List<UserInformation> results = cr.list();
		if(results.size() > 0){
			return results.get(0);
		}
		
		return null;
	}
	
	public TaskInformation fetchTaskInfoByID(int task_id, Session ss){
		Criteria cr = ss.createCriteria(TaskInformation.class);
		cr.add(Restrictions.eq("taskId", task_id)); 
		List<TaskInformation> results = cr.list();
		if(results.size() > 0){
			return results.get(0);
		}
		
		return null;
	}
	
	
}

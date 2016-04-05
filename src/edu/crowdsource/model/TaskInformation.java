package edu.crowdsource.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the task_information database table.
 * 
 */
@Entity
@Table(name="task_information")
@NamedQuery(name="TaskInformation.findAll", query="SELECT t FROM TaskInformation t")
public class TaskInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private Integer taskId;

	@Column(name="task_name")
	private String taskName;

	//bi-directional many-to-one association to TaskLog
	@OneToMany(mappedBy="taskInformation")
	private List<TaskLog> taskLogs;

	//bi-directional many-to-one association to UserSkill
	@OneToMany(mappedBy="taskInformation")
	private List<UserSkill> userSkills;

	public TaskInformation() {
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(List<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	public TaskLog addTaskLog(TaskLog taskLog) {
		getTaskLogs().add(taskLog);
		taskLog.setTaskInformation(this);

		return taskLog;
	}

	public TaskLog removeTaskLog(TaskLog taskLog) {
		getTaskLogs().remove(taskLog);
		taskLog.setTaskInformation(null);

		return taskLog;
	}

	public List<UserSkill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public UserSkill addUserSkill(UserSkill userSkill) {
		getUserSkills().add(userSkill);
		userSkill.setTaskInformation(this);

		return userSkill;
	}

	public UserSkill removeUserSkill(UserSkill userSkill) {
		getUserSkills().remove(userSkill);
		userSkill.setTaskInformation(null);

		return userSkill;
	}

}
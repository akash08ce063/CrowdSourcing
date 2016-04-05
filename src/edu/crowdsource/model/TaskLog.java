package edu.crowdsource.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the task_log database table.
 * 
 */
@Entity
@Table(name="task_log")
@NamedQuery(name="TaskLog.findAll", query="SELECT t FROM TaskLog t")
public class TaskLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="client_id")
	private Integer clientId;

	@Column(name="given_rating")
	private double givenRating;

	@Column(name="worker_id")
	private Integer workerId;
	
	@Column(name="task_status")
	private String taskStatus;

	//bi-directional many-to-one association to TaskInformation
	@ManyToOne
	@JoinColumn(name="task_id")
	private TaskInformation taskInformation;

	public TaskLog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public double getGivenRating() {
		return this.givenRating;
	}

	public void setGivenRating(double givenRating) {
		this.givenRating = givenRating;
	}

	public Integer getWorkerId() {
		return this.workerId;
	}

	
	public String getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	
	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public TaskInformation getTaskInformation() {
		return this.taskInformation;
	}

	public void setTaskInformation(TaskInformation taskInformation) {
		this.taskInformation = taskInformation;
	}

}
package edu.crowdsource.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_skill database table.
 * 
 */
@Entity
@Table(name="user_skill")
@NamedQuery(name="UserSkill.findAll", query="SELECT u FROM UserSkill u")
public class UserSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private double charges;

	@Column(name="task_availability")
	private double taskAvailability;

	@Column(name="task_credibility")
	private double taskCredibility;

	@Column(name="task_experience")
	private double taskExperience;

	//bi-directional many-to-one association to TaskInformation
	@ManyToOne
	@JoinColumn(name="task_id")
	private TaskInformation taskInformation;

	//bi-directional many-to-one association to UserInformation
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInformation userInformation;

	public UserSkill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getCharges() {
		return this.charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public double getTaskAvailability() {
		return this.taskAvailability;
	}

	public void setTaskAvailability(double taskAvailability) {
		this.taskAvailability = taskAvailability;
	}

	public double getTaskCredibility() {
		return this.taskCredibility;
	}

	public void setTaskCredibility(double taskCredibility) {
		this.taskCredibility = taskCredibility;
	}

	public double getTaskExperience() {
		return this.taskExperience;
	}

	public void setTaskExperience(double taskExperience) {
		this.taskExperience = taskExperience;
	}

	public TaskInformation getTaskInformation() {
		return this.taskInformation;
	}

	public void setTaskInformation(TaskInformation taskInformation) {
		this.taskInformation = taskInformation;
	}

	public UserInformation getUserInformation() {
		return this.userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

}
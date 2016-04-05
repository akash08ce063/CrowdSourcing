package edu.crowdsource.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_information database table.
 * 
 */
@Entity
@Table(name="user_information")
@NamedQuery(name="UserInformation.findAll", query="SELECT u FROM UserInformation u")
public class UserInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;

	private String address;
	
	private Integer type;

	@Column(name="geo_location")
	private String geoLocation;

	private String password;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to UserSkill
	@OneToMany(mappedBy="userInformation")
	private List<UserSkill> userSkills;

	public UserInformation() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public List<UserSkill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public UserSkill addUserSkill(UserSkill userSkill) {
		getUserSkills().add(userSkill);
		userSkill.setUserInformation(this);

		return userSkill;
	}

	public UserSkill removeUserSkill(UserSkill userSkill) {
		getUserSkills().remove(userSkill);
		userSkill.setUserInformation(null);

		return userSkill;
	}

}
package com.ssafy.itda.itda_test.help;

import java.io.Serializable;
import java.util.List;

import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.User;

public class UserResult implements Serializable {

	// User Model
	private User user;

	// Stack Model
	private List<Stack> mystacks;

	// Scrap Model
	private List<WantedResult> myScrapWanteds;
	private List<WantedResult> myEndedScrapWanteds;

	// Response result
	private String msg;
	private String state;

	public UserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Stack> getMystacks() {
		return mystacks;
	}

	public void setMystacks(List<Stack> mystacks) {
		this.mystacks = mystacks;
	}

	public List<WantedResult> getMyEndedScrapWanteds() {
		return myEndedScrapWanteds;
	}

	public void setMyEndedScrapWanteds(List<WantedResult> myEndedScrapWanteds) {
		this.myEndedScrapWanteds = myEndedScrapWanteds;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<WantedResult> getMyScrapWanteds() {
		return myScrapWanteds;
	}

	public void setMyScrapWanteds(List<WantedResult> myScrapWanteds) {
		this.myScrapWanteds = myScrapWanteds;
	}

	@Override
	public String toString() {
		return "UserResult [user=" + user + ", mystacks=" + mystacks + ", myScrapWanteds=" + myScrapWanteds
				+ ", myEndedScrapWanteds=" + myEndedScrapWanteds + ", msg=" + msg + ", state=" + state + "]";
	}

}

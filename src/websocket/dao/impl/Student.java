package websocket.dao.impl;

import websocket.dao.biz.StudentBiz;
import websocket.dao.bo.StudentBO;

public class Student implements StudentBiz,StudentBO {
	
	private int rank;
	private int id;
	

	private String name;
	private String academy;
	private String major;
	private String sex;
	private String mobile;
	private String qq;
	private String wechat;
	private String advantages;
	private int isselect;
	
	public int getIsselect() {
		return isselect;
	}

	public void setIsselect(int isselect) {
		this.isselect = isselect;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student(){
		
	}
	
	public Student(String name,String academy,String major,String sex,String mobile,String qq,
			String wechat,String advantages){
		
		this.name = name;
		this.academy = academy;
		this.major = major;
		this.sex = sex;
		this.mobile = mobile;
		this.qq = qq;
		this.wechat = wechat;
		this.advantages = advantages;
	}
	
	public Student(int rank,int id,String name,String academy,String major,String sex,String mobile,String qq,
			String wechat,String advantages,int isselect){
		this.rank = rank; 
		this.id = id;
		this.name = name;
		this.academy = academy;
		this.major = major;
		this.sex = sex;
		this.mobile = mobile;
		this.qq = qq;
		this.wechat = wechat;
		this.advantages = advantages;
		this.isselect = isselect;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAcademy() {
		return academy;
	}



	public void setAcademy(String academy) {
		this.academy = academy;
	}



	public String getMajor() {
		return major;
	}



	public void setMajor(String major) {
		this.major = major;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getQq() {
		return qq;
	}



	public void setQq(String qq) {
		this.qq = qq;
	}



	public String getWechat() {
		return wechat;
	}



	public void setWechat(String wechat) {
		this.wechat = wechat;
	}



	public String getAdvantages() {
		return advantages;
	}



	public void setAdvantages(String advantages) {
		this.advantages = advantages;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "{\"name\":\""+getName()+"\",\"academy\":\""+getAcademy()+"\",\"major\":\""+getMajor()+
				"\",\"sex\":\""+getSex()+"\",\"mobile\":\""+getMobile()+
				"\",\"qq\":\""+getQq()+"\",\"wechat\":\""+getWechat()+"\",\"advantages\":\""+getAdvantages()+"\"}";
		
		
	}

	

}

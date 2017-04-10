package cn.ssha.bean;

import java.io.Serializable;

public class Template implements Serializable{
	private Integer id;
	private String name;
	private String pdKey;
	private String docFilePath;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPdKey() {
		return pdKey;
	}
	public void setPdKey(String pdKey) {
		this.pdKey = pdKey;
	}
	public String getDocFilePath() {
		return docFilePath;
	}
	public void setDocFilePath(String docFilePath) {
		this.docFilePath = docFilePath;
	}
	
}

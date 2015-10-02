package com.app.model.impl;

public class SingularAttribute<T,K> {
	
	private String coloumnName;
	private Class classObj;
	public SingularAttribute(String colName,Class clazz) {
	// TODO Auto-generated constructor stub
		this.setColoumnName(colName);
		this.setClassObj(clazz);
	}
	public void setClassObj(Class classObj) {
		this.classObj = classObj;
	}
	public Class getClassObj() {
		return classObj;
	}
	public void setColoumnName(String coloumnName) {
		this.coloumnName = coloumnName;
	}
	public String getColoumnName() {
		return coloumnName;
	}
	public String getClassName()
	{
		return classObj.getSimpleName();
	}
	

}

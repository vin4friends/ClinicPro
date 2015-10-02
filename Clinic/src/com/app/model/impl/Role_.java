package com.app.model.impl;

public abstract class Role_ {

	public static volatile SingularAttribute<Role, Long> id=new SingularAttribute<Role, Long>("id",Role.class);
	public static volatile SingularAttribute<Role, String> authority=new SingularAttribute<Role, String>("authority",Role.class);
	public static volatile SingularAttribute<Role, String> name=new SingularAttribute<Role, String>("name",Role.class);

}


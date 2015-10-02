package com.app.model.impl;


public abstract class LfUser_ {

	public static volatile SingularAttribute<LfUser, Long> id=new SingularAttribute<LfUser, Long>("id",LfUser.class);
	public static volatile SingularAttribute<LfUser, Role> grantedAuthorities=new SingularAttribute<LfUser, Role>("grantedAuthorities",LfUser.class);
	public static volatile SingularAttribute<LfUser, String> name=new SingularAttribute<LfUser, String>("name",LfUser.class);
	public static volatile SingularAttribute<LfUser, String> login=new SingularAttribute<LfUser, String>("login",LfUser.class);
	public static volatile SingularAttribute<LfUser, String> firstname=new SingularAttribute<LfUser, String>("firstname",LfUser.class);
	public static volatile SingularAttribute<LfUser, Country> countries=new SingularAttribute<LfUser, Country>("countries",LfUser.class);
	public static volatile SingularAttribute<LfUser, String> password=new SingularAttribute<LfUser, String>("password",LfUser.class);
	public static volatile SingularAttribute<LfUser, Boolean> isLocked=new SingularAttribute<LfUser, Boolean>("isLocked",LfUser.class);

}


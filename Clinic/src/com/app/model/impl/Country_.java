package com.app.model.impl;


public abstract class Country_ {

	public static volatile SingularAttribute<Country, String> countrycode=new SingularAttribute<Country, String>("countrycode",Country.class);
	public static volatile SingularAttribute<Country, String> name=new SingularAttribute<Country, String>("name",Country.class);;
	public static volatile SingularAttribute<Country, String> addressTemplate=new SingularAttribute<Country, String>("addressTemplate",Country.class);;
	public static volatile SingularAttribute<Country, Long> maxLocationResult=new SingularAttribute<Country, Long>("maxLocationResult",Country.class);;
	public static volatile SingularAttribute<Country, String> defaultLanguage=new SingularAttribute<Country, String>("defaultLanguage",Country.class);;

}


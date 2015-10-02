package com.app.model.impl;


public abstract class LocationType_ {

	public static volatile SingularAttribute<LocationType, Long> id=new SingularAttribute<LocationType, Long>("id",LocationType.class);
	public static volatile SingularAttribute<LocationType, String> locationType=new SingularAttribute<LocationType, String>("locationType",LocationType.class);
	public static volatile SingularAttribute<LocationType, String> language=new SingularAttribute<LocationType, String>("language",LocationType.class);
	public static volatile SingularAttribute<LocationType, Country> country=new SingularAttribute<LocationType, Country>("country",LocationType.class);

}
package com.app.model.impl;


public abstract class ProductCategory_ {

	public static volatile SingularAttribute<ProductCategory, Long> id=new SingularAttribute<ProductCategory, Long>("id",ProductCategory.class);
	public static volatile SingularAttribute<ProductCategory, String> name=new SingularAttribute<ProductCategory, String>("name",ProductCategory.class);
	public static volatile SingularAttribute<ProductCategory, String> language=new SingularAttribute<ProductCategory, String>("language",ProductCategory.class);
	public static volatile SingularAttribute<ProductCategory, Integer> theOrder=new SingularAttribute<ProductCategory, Integer>("theOrder",ProductCategory.class);
	public static volatile SingularAttribute<ProductCategory, Country> country=new SingularAttribute<ProductCategory, Country>("country",ProductCategory.class);

}


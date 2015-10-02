package com.app.model.impl;


public abstract class ImageSet_ {

	public static volatile SingularAttribute<ImageSet, Long> id=new SingularAttribute<ImageSet, Long>("id",ImageSet.class);
	public static volatile SingularAttribute<ImageSet, LocationTypeIcon> locationTypeIcons=new SingularAttribute<ImageSet, LocationTypeIcon>("locationTypeIcons",ImageSet.class);
	public static volatile SingularAttribute<ImageSet, String> name=new SingularAttribute<ImageSet, String>("name",ImageSet.class) ;
	public static volatile SingularAttribute<ImageSet, ProductCategoryIcon> productCategoryIcons=new SingularAttribute<ImageSet, ProductCategoryIcon>("productCategoryIcons",ImageSet.class);

}


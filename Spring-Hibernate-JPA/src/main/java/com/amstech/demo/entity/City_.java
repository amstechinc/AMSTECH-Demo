package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.524+0530")
@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Integer> id;
	public static volatile SingularAttribute<City, Date> createTime;
	public static volatile SingularAttribute<City, Integer> isDeleted;
	public static volatile SingularAttribute<City, String> name;
	public static volatile SingularAttribute<City, String> pincode;
	public static volatile SingularAttribute<City, String> status;
	public static volatile SingularAttribute<City, Date> updateTime;
	public static volatile SingularAttribute<City, State> state;
	public static volatile ListAttribute<City, Restaurant> restaurants;
	public static volatile ListAttribute<City, User> users;
}

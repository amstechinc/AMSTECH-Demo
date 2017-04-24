package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.531+0530")
@StaticMetamodel(Restaurant.class)
public class Restaurant_ {
	public static volatile SingularAttribute<Restaurant, Integer> id;
	public static volatile SingularAttribute<Restaurant, String> capacity;
	public static volatile SingularAttribute<Restaurant, Integer> categoryId;
	public static volatile SingularAttribute<Restaurant, Date> createTime;
	public static volatile SingularAttribute<Restaurant, Integer> isDeleted;
	public static volatile SingularAttribute<Restaurant, String> licenseNumber;
	public static volatile SingularAttribute<Restaurant, String> logo;
	public static volatile SingularAttribute<Restaurant, Double> maxCost;
	public static volatile SingularAttribute<Restaurant, Double> minCost;
	public static volatile SingularAttribute<Restaurant, String> name;
	public static volatile SingularAttribute<Restaurant, String> specialities;
	public static volatile SingularAttribute<Restaurant, String> status;
	public static volatile SingularAttribute<Restaurant, Date> updateTime;
	public static volatile ListAttribute<Restaurant, RestBranch> restBranches;
	public static volatile SingularAttribute<Restaurant, City> city;
}

package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.534+0530")
@StaticMetamodel(RestBranch.class)
public class RestBranch_ {
	public static volatile SingularAttribute<RestBranch, Integer> id;
	public static volatile SingularAttribute<RestBranch, String> address;
	public static volatile SingularAttribute<RestBranch, Date> createTime;
	public static volatile SingularAttribute<RestBranch, String> email;
	public static volatile SingularAttribute<RestBranch, Integer> isDeleted;
	public static volatile SingularAttribute<RestBranch, String> phoneNo;
	public static volatile SingularAttribute<RestBranch, String> status;
	public static volatile SingularAttribute<RestBranch, Date> updateTime;
	public static volatile SingularAttribute<RestBranch, Restaurant> restaurant;
}

package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.547+0530")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> aadharNo;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, String> choice;
	public static volatile SingularAttribute<User, String> contactOne;
	public static volatile SingularAttribute<User, String> contactTwo;
	public static volatile SingularAttribute<User, Date> createTime;
	public static volatile SingularAttribute<User, Date> dob;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> fname;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile SingularAttribute<User, String> image;
	public static volatile SingularAttribute<User, Integer> isDeleted;
	public static volatile SingularAttribute<User, String> lname;
	public static volatile SingularAttribute<User, String> mname;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> status;
	public static volatile SingularAttribute<User, Date> updateTime;
	public static volatile SingularAttribute<User, City> city;
}

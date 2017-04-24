package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.529+0530")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile SingularAttribute<Country, Date> createTime;
	public static volatile SingularAttribute<Country, Integer> isDeleted;
	public static volatile SingularAttribute<Country, String> name;
	public static volatile SingularAttribute<Country, String> status;
	public static volatile SingularAttribute<Country, Date> updateTime;
	public static volatile ListAttribute<Country, State> states;
}

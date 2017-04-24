package com.amstech.demo.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-24T21:23:11.543+0530")
@StaticMetamodel(State.class)
public class State_ {
	public static volatile SingularAttribute<State, Integer> id;
	public static volatile SingularAttribute<State, Date> createTime;
	public static volatile SingularAttribute<State, Integer> isDeleted;
	public static volatile SingularAttribute<State, String> name;
	public static volatile SingularAttribute<State, String> status;
	public static volatile SingularAttribute<State, Date> updateTime;
	public static volatile ListAttribute<State, City> cities;
	public static volatile SingularAttribute<State, Country> country;
}

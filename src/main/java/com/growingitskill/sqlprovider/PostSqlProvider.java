package com.growingitskill.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PostSqlProvider {

	public String delete(@Param("ids") final long[] ids) {
		StringBuffer inQuery = new StringBuffer();
		inQuery.append("(");

		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				inQuery.append(ids[i]);
			} else {
				inQuery.append(ids[i] + ", ");
			}
		}

		inQuery.append(")");

		return new SQL() {
			{
				DELETE_FROM("post");
				WHERE("id IN" + inQuery.toString());
			}
		}.toString();
	}

}

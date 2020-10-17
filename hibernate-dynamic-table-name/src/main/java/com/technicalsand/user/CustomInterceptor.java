package com.technicalsand.user;

import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Service;

@Service
public class CustomInterceptor extends EmptyInterceptor {
 @Override
 public String onPrepareStatement(String sql) {
  System.err.println("Before Modifying SQL =" + sql);
  sql = sql.replace("ATTENDANCE_1_2019 ", "ATTENDANCE_2_2019 ");
  System.err.println("After Modifying SQL =" + sql);
  return sql;
 }
}
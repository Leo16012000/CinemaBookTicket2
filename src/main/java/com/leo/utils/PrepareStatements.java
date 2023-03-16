package com.leo.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PrepareStatements {
  public static PreparedStatement setPreparedStatementParams(PreparedStatement ps, Object... params)
      throws SQLException {
    int i = 0;
    for (Object param : params) {
      i = i + 1;
      if (param instanceof Date) {
        ps.setTimestamp(i, new Timestamp(((Date) param).getTime()));
      } else if (param instanceof Integer) {
        ps.setInt(i, (Integer) param);
      } else if (param instanceof Long) {
        ps.setLong(i, (Long) param);
      } else if (param instanceof Double) {
        ps.setDouble(i, (Double) param);
      } else if (param instanceof Float) {
        ps.setFloat(i, (Float) param);
      } else {
        ps.setString(i, (String) param);
      }
    }
    return ps;
  }
}

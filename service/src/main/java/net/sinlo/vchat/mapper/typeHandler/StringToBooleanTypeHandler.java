package net.sinlo.vchat.mapper.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringToBooleanTypeHandler  extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        preparedStatement.setBoolean(i,aBoolean);
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s).equals("1");
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i).equals("1");
    }

    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getBoolean(i);
    }
}

package com.github.soilex.xshop.data.mybatis;

import com.github.soilex.xshop.utils.Enums;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, Enums.getEnumValue(parameter));
    }

    private E getNullableResult(int i) {
        try {
            return Enums.valueOf(type, i);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by value.");
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull())
            return null;
        return getNullableResult(i);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull())
            return null;
        return getNullableResult(i);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull())
            return null;
        return getNullableResult(i);
    }
}

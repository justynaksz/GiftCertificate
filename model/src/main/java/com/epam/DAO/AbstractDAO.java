package com.epam.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;

abstract public class AbstractDAO extends NamedParameterJdbcDaoSupport {
    @Autowired
    public void setDataSourceObject (DataSource ds) {
       super.setDataSource(ds);
    }
}

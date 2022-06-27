package com.epam.esm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;

/**
 * Supplies DataSource to the parent class.
 */
abstract public class AbstractDAO extends NamedParameterJdbcDaoSupport {

    /**
     * Injects DataSource to the parent class.
     * @param ds    dataSource object to be injected to the NamedParameterJdbcDaoSupport class
     */
    @Autowired
    public void setDataSourceObject (DataSource ds) {
       super.setDataSource(ds);
    }
}

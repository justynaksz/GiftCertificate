package com.epam.esm.rowmapper;

import com.epam.esm.model.Tag;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
* Implementation which maps each row to a result Tag object.
*/
public class TagRowMapper implements RowMapper<Tag> {
    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Maps each row of data in the ResultSet to Tag object.
     * @param rs        ResultSet to be mapped
     * @param rowNum    the number of current row
     * @return tag      Tag instance
     * @throws SQLException
     */
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        try {
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return tag;
    }
}


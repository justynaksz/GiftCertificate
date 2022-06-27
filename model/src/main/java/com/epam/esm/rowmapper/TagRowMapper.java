package com.epam.esm.rowmapper;

import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Implementation which maps each row to a result Tag object.
*/
public class TagRowMapper implements RowMapper<Tag> {
    Logger logger = Logger.getLogger(getClass().getName());

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
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return tag;
    }
}

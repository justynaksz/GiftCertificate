package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import com.epam.esm.rowmapper.TagRowMapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements CRUD operations for Tag entity.
 */
@Repository
public class TagDAOImpl extends AbstractDAO implements TagDAO {

    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Finds tag of given id value.
     * @param  id     int id value
     * @return tag    tag of given id value
     */
    @Override
    public Tag findById(int id) {
        String query = "SELECT id, name FROM tag WHERE id = ?";
        Tag tag = null;
        try {
            tag = getJdbcTemplate().queryForObject(query, new TagRowMapper(), id);
        } catch (Exception e) {
            logger.error("Selecting tag of id \"" + tag.getId() + "\" has failed");
        }
        return tag;
    }

    /**
     * Finds tags of given name.
     * @param  name    String name value
     * @return tags    tag of given name
     */
    @Override
    public Tag findByName(String name) {
        String query = "SELECT id, name FROM tag WHERE name = ?";
        Tag tag = null;
        try {
            tag = getJdbcTemplate().queryForObject(query, new TagRowMapper(), name);
        } catch (Exception e) {
            logger.error("Selecting tag of name \"" + tag.getName()+ "\" has failed");
        }
        return tag;
    }

    /**
     * Finds all tags.
     * @return tags    list of all tags
     */
    @Override
    public List<Tag> findAll() {
        String query = "SELECT id, name FROM tag";
        List<Tag> tags = null;
        try {
            tags = getJdbcTemplate().query(query, new TagRowMapper());
        } catch (Exception e) {
            logger.error("Finding all tags has failed");
        }
        return tags;
    }

    /**
     * Creates new tag entity.
     * @param  tag    Tag instance to be inserted into database
     * @return tag    Tag instance with specified id value that has been inserted into database
     */
    @Override
    public Tag createTag(Tag tag) {
        String query = "INSERT INTO tag (name) VALUES(:name)";
        Map <String, Object> map = new HashMap();
        try {
            map.put("name", tag.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = new MapSqlParameterSource(map);
            super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
            int tagId = keyHolder.getKey().intValue();
            tag.setId(tagId);
        } catch (Exception e) {
            logger.error("Creating tag of name \"" + tag.getName() + "\" has failed");
        } return tag;
    }

    /**
     * Deletes tag of given id value.
     * @param id     int id value of tag instance to be removed
     */
    @Override
    public void deleteTag(int id) {
        String query = "DELETE FROM tag WHERE id=?";
        try {
            getJdbcTemplate().update(query, id);
        } catch (Exception e) {
            logger.error("Deleting tag of id \"" + id + "\" has failed");
        }
    }
}

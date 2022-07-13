package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import com.epam.esm.rowmapper.TagRowMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements CRD operations for Tag entity.
 */
@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @See com.epam.esm.dao.TagDAO
     */
    @Override
    public Tag findById(int id) throws EmptyResultDataAccessException {
        String query = "SELECT id, name FROM tag WHERE id = ?";
        Tag tag;
        try {
            tag = jdbcTemplate.queryForObject(query, new TagRowMapper(), id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error("Selecting tag has failed");
            throw new EmptyResultDataAccessException(0);
        }
        return tag;
    }

    /**
     * @See com.epam.esm.dao.TagDAO
     */
    @Override
    public Tag findByName(String name) throws EmptyResultDataAccessException {
        String query = "SELECT id, name FROM tag WHERE UPPER (name) LIKE UPPER ( ? )" ;
        Tag tag = null;
        try {
            tag = jdbcTemplate.queryForObject(query, new TagRowMapper(), name);
        } catch (Exception e) {
            logger.error("Selecting tag has failed");
            throw new EmptyResultDataAccessException(0);
        }
        return tag;
    }

    /**
     * @See com.epam.esm.dao.TagDAO
     */
    @Override
    public List<Tag> findAll() {
        String query = "SELECT id, name FROM tag";
        List<Tag> tags = null;
        try {
            tags = jdbcTemplate.query(query, new TagRowMapper());
        } catch (Exception e) {
            logger.error("Finding all tags has failed");
        }
        return tags;
    }

    /**
     * @See com.epam.esm.dao.TagDAO
     */
    @Override
    public Tag createTag(Tag tag) {
        String query = "INSERT INTO tag (name) VALUES(:name)";
        Map <String, Object> map = new HashMap<>();
        try {
            map.put("name", tag.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = new MapSqlParameterSource(map);
            namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[] { "id" });
            int tagId = keyHolder.getKey().intValue();
            tag.setId(tagId);
        } catch (Exception e) {
            logger.error("Creating tag of name \"" + tag.getName() + "\" has failed");
        } return tag;
    }

    /**
     * @See com.epam.esm.dao.TagDAO
     */
    @Override
    public void deleteTag(int id) throws EmptyResultDataAccessException {
        String query = "DELETE FROM tag WHERE id=?";
        try {
            findById(id);
            jdbcTemplate.update(query, id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error("Deleting tag has failed");
            throw new EmptyResultDataAccessException(0);
        }
    }
}

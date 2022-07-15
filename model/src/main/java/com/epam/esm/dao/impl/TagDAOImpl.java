package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import com.epam.esm.rowmapper.TagRowMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import java.util.Objects;

/**
 * Implements CRD operations for {@code Tag} entity.
 */
@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final Logger logger = Logger.getLogger(getClass().getName());
    private static final String MESSAGE = "No tag of requested id has been found.";
    private static final String SELECT_MESSAGE = "Selecting tag has failed.";
    /**
     * {@inheritDoc}
     */
    @Override
    public Tag findById(int id) throws EmptyResultDataAccessException {
        String query = "SELECT id, name FROM tag WHERE id = ?";
        Tag tag = null;
        try {
            tag = jdbcTemplate.queryForObject(query, new TagRowMapper(), id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error(MESSAGE);
            throw new EmptyResultDataAccessException(MESSAGE, 0);
        } catch (DataAccessException exception) {
            logger.error(SELECT_MESSAGE);
        }
        return tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tag findByName(String name) throws EmptyResultDataAccessException {
        String query = "SELECT id, name FROM tag WHERE UPPER (name) LIKE UPPER ( ? )" ;
        Tag tag = null;
        try {
            tag = jdbcTemplate.queryForObject(query, new TagRowMapper(), name);
        } catch (EmptyResultDataAccessException exception) {
            logger.error(MESSAGE);
            throw new EmptyResultDataAccessException(MESSAGE, 0);
        } catch (DataAccessException exception) {
            logger.error(SELECT_MESSAGE);
        }
        return tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tag> findAll() {
        String query = "SELECT id, name FROM tag";
        List<Tag> tags = null;
        try {
            tags = jdbcTemplate.query(query, new TagRowMapper());
        } catch (DataAccessException exception) {
            logger.error("Finding all tags has failed");
        }
        return tags;
    }

    /**
     * {@inheritDoc}
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
            int tagId = Objects.requireNonNull(keyHolder.getKey()).intValue();
            tag.setId(tagId);
        } catch (DataAccessException exception) {
            logger.error("Creating tag has failed");
        } return tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTag(int id) throws EmptyResultDataAccessException {
        String query = "DELETE FROM tag WHERE id=?";
        try {
            findById(id);
            jdbcTemplate.update(query, id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error(MESSAGE);
            throw new EmptyResultDataAccessException(MESSAGE, 0);
        } catch (DataAccessException exception) {
            logger.error("Deleting tag has failed");
        }
    }
}

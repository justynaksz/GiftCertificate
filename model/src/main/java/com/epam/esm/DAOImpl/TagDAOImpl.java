package com.epam.esm.DAOImpl;

import com.epam.esm.DAO.AbstractDAO;
import com.epam.esm.DAO.TagDAO;
import com.epam.esm.model.Tag;
import com.epam.esm.rowMapper.TagRowMapper;
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

    /**
     * Finds tag of given id value.
     * @param  id     int id value
     * @return tag    tag of given id value
     */
    @Override
    public Tag findById(int id) {
        String query = "SELECT id, name FROM tag WHERE id = ?";
        Tag tag = getJdbcTemplate().queryForObject(query, new TagRowMapper(), id);
        return tag;
    }

    /**
     * Finds tags of given name.
     * @param  name    String name value
     * @return tags    list of tags of given name
     */
    @Override
    public List<Tag> findByName(String name) {
        String query = "SELECT id, name FROM tag WHERE name = ?";
        List<Tag> tags = getJdbcTemplate().query(query, new TagRowMapper(), name);
        return tags;
    }

    /**
     * Finds all tags.
     * @return tags    list of all tags
     */
    @Override
    public List<Tag> findAll() {
        String query = "SELECT id, name FROM tag";
        List<Tag> tags = getJdbcTemplate().query(query, new TagRowMapper());
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
        Map map = new HashMap();
        map.put("name", tag.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        int tagId = keyHolder.getKey().intValue();
        tag.setId(tagId);
        return tag;
    }

    /**
     * Deletes tag of given id value.
     * @param id     int id value of tag instance to be removed
     */
    @Override
    public void deleteTag(int id) {
        String query = "DELETE FROM tag WHERE id=?";
        getJdbcTemplate().update(query, id);
    }
}

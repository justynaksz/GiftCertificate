package com.epam.DAOImpl;

import com.epam.DAO.AbstractDAO;
import com.epam.DAO.TagDAO;
import com.epam.model.Tag;
import com.epam.rowMapper.TagRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagDAOImpl extends AbstractDAO implements TagDAO {

    @Override
    public Tag findById(int id) {
           String query = "SELECT id, name FROM tag WHERE id = ?";
           Tag tag = getJdbcTemplate().queryForObject(query, new TagRowMapper(), id);
           return tag;
    }

    @Override
    public List<Tag> findByName(String name) {
        String query = "SELECT id, name FROM tag WHERE name = ?";
        List<Tag> tags = getJdbcTemplate().query(query, new TagRowMapper(), name);
        return tags;
    }

    @Override
    public List<Tag> findAll() {
        String query = "SELECT id, name FROM tag";
        List<Tag> tags = getJdbcTemplate().query(query, new TagRowMapper());
        return tags;
    }

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

    @Override
    public void deleteTag(int id) {
        String query = "DELETE FROM tag WHERE id=?";
        getJdbcTemplate().update(query, id);
    }
}

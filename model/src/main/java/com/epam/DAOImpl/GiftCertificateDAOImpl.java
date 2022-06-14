package com.epam.DAOImpl;

import com.epam.DAO.AbstractDAO;
import com.epam.DAO.GiftCertificateDAO;
import com.epam.model.GiftCertificate;
import com.epam.rowMapper.GiftCertificateRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftCertificateDAOImpl extends AbstractDAO implements GiftCertificateDAO {
    @Override
    public GiftCertificate findById(int id) {
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate WHERE id = ?";
        GiftCertificate giftCertificate = getJdbcTemplate().queryForObject(query, new GiftCertificateRowMapper(), id);
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findByTag(String tagName) {
        String query = "SELECT tag.name, gift_certificate.id, gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, gift_certificate.create_date, gift_certificate.last_update_date " +
                "FROM gift_certificate " +
                "JOIN gift_certificate_tag ON gift_certificate.id = gift_certificate_tag.gift_certificate_id " +
                "JOIN tag ON tag.id = gift_certificate_tag.tag_id" +
                "WHERE tag.name = ?";
        List<GiftCertificate> giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper(), tagName);
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> findByNameOrDescription(String key) {
        String queryName = "SELECT id, name, description, price, duration, create_date, last_update_date WHERE name LIKE %?%";
        String queryDescription = "SELECT id, name, description, price, duration, createDate, lastUpdateDate WHERE description LIKE %?%";
        List<GiftCertificate> giftCertificatesNames = getJdbcTemplate().query(queryName, new GiftCertificateRowMapper(), key);
        List<GiftCertificate> giftCertificatesDescriptions = getJdbcTemplate().query(queryDescription, new GiftCertificateRowMapper(), key);
        List<GiftCertificate> giftCertificates = new ArrayList<>(giftCertificatesNames);
        for (GiftCertificate giftCertificate : giftCertificatesDescriptions) {
            if (!giftCertificates.contains(giftCertificate)) giftCertificates.add(giftCertificate);
        }
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> findAll() {
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate";
        List<GiftCertificate> giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> sortAscending() {
        String query = "SELECT tag.name, gift_certificate.id, gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, gift_certificate.create_date, gift_certificate.last_update_date " +
                "FROM gift_certificate " +
                "JOIN gift_certificate_tag ON gift_certificate.id = gift_certificate_tag.gift_certificate_id " +
                "JOIN tag ON tag.id = gift_certificate_tag.tag_id" +
                "ORDER BY gift_certificate.name ASC";
        List<GiftCertificate> giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> sortDescending() {
        String query = "SELECT tag.name, gift_certificate.id, gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, gift_certificate.create_date, gift_certificate.last_update_date " +
                "FROM gift_certificate " +
                "JOIN gift_certificate_tag ON gift_certificate.id = gift_certificate_tag.gift_certificate_id " +
                "JOIN tag ON tag.id = gift_certificate_tag.tag_id" +
                "ORDER BY gift_certificate.name DSC";
        List<GiftCertificate> giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        return giftCertificates;
    }

    @Override
    public GiftCertificate createGiftCertificate(GiftCertificate giftCertificate) {
        String query = "INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) " +
                "VALUES (:name, :description, :price, :duration, :creation_date, :last_update_date";
        Map map = new HashMap();
        map.put("name", giftCertificate.getName());
        map.put("description", giftCertificate.getDescription());
        map.put("price", giftCertificate.getPrice());
        map.put("duration", giftCertificate.getDuration());
        map.put("create_date", Timestamp.from(Instant.now()));
        map.put("last_update_date", null);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        int id = keyHolder.getKey().intValue();
        giftCertificate.setId(id);
        return giftCertificate;
    }

    @Override
    public void updateGiftCertificate(GiftCertificate giftCertificate) {
        String query = "UPDATE gift_certificate" +
                "SET name = :name, description = :description, price = :price, duration = :duration, last_update_date = :last_update_date" +
                "WHERE id = :id";
        Map map = new HashMap();
        map.put("name", giftCertificate.getName());
        map.put("description", giftCertificate.getDescription());
        map.put("price", giftCertificate.getPrice());
        map.put("duration", giftCertificate.getDuration());
        map.put("last_update_date", Timestamp.from(Instant.now()));
        map.put("id", giftCertificate.getId());
        getNamedParameterJdbcTemplate().update(query, map);
    }

    @Override
    public void deleteGiftCertificate(int id) {
        String query = "DELETE FROM gift_certificate WHERE id=?";
        getJdbcTemplate().update(query, id);
    }
}

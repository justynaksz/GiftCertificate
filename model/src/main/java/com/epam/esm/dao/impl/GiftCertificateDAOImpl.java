package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.rowmapper.GiftCertificateRowMapper;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implements CRUD operations for GiftCertificate entity.
 */
@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public GiftCertificate findById(int id) throws EmptyResultDataAccessException {
        GiftCertificate giftCertificate;
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate WHERE id = ?";
        try {
            giftCertificate = jdbcTemplate.queryForObject(query, new GiftCertificateRowMapper(), id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error("Selecting giftCertificate has failed");
            throw new EmptyResultDataAccessException(0);
        }
       return giftCertificate;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public List<GiftCertificate> findByTag(String tagName) {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT tag.name, gift_certificate.id, gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, gift_certificate.create_date, gift_certificate.last_update_date " +
                "FROM gift_certificate " +
                "JOIN gift_certificate_tag ON gift_certificate.id = gift_certificate_tag.gift_certificate_id " +
                "JOIN tag ON tag.id = gift_certificate_tag.tag_id " +
                "WHERE UPPER (tag.name) LIKE UPPER ( ? )";
        try {
            giftCertificates = jdbcTemplate.query(query, new GiftCertificateRowMapper(), tagName);
        } catch (Exception exception) {
            logger.error("Finding giftCertificate by tag name has failed");
        }
        return giftCertificates;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public List<GiftCertificate> findByNameOrDescription(String key) {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate WHERE UPPER (name) LIKE UPPER ( ? ) OR UPPER (description) LIKE UPPER ( ? )";
        try {
            giftCertificates = jdbcTemplate.query(query, new GiftCertificateRowMapper(), "%" + key + "%", "%" + key + "%");
        } catch (Exception exception) {
            logger.error("Finding giftCertificate by key word has failed");
        }
            return giftCertificates;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public List<GiftCertificate> findAll() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate";
        try {
            giftCertificates = jdbcTemplate.query(query, new GiftCertificateRowMapper());
        } catch (Exception exception) {
            logger.error("Finding all giftCertificates has failed");
        }
        return giftCertificates;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public List<GiftCertificate> sortAscending() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate ORDER BY name ASC";
        try {
            giftCertificates = jdbcTemplate.query(query, new GiftCertificateRowMapper());
        } catch (Exception exception) {
            logger.error("Sorting giftCertificates in ascending order has failed.");
        }
        return giftCertificates;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public List<GiftCertificate> sortDescending() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate ORDER BY name DESC";
        try {
            giftCertificates = jdbcTemplate.query(query, new GiftCertificateRowMapper());
        } catch (Exception exception) {
            logger.error("Sorting giftCertificates in descending order has failed.");
        }
        return giftCertificates;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public GiftCertificate createGiftCertificate(GiftCertificate giftCertificate) {
        String query = "INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) " +
                "VALUES (:name, :description, :price, :duration, :create_date, :last_update_date);";
        Map<String, Object> map = new HashMap<>();

        try {
            giftCertificate.setCreateDate(LocalDateTime.now());
            map.put("name", giftCertificate.getName());
            map.put("description", giftCertificate.getDescription());
            map.put("price", giftCertificate.getPrice());
            map.put("duration", giftCertificate.getDuration());
            map.put("create_date", giftCertificate.getCreateDate());
            map.put("last_update_date", giftCertificate.getLastUpdateDate());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = new MapSqlParameterSource(map);
            namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"id"});
            int id = keyHolder.getKey().intValue();
            giftCertificate.setId(id);
        } catch (Exception exception) {
            logger.error( "Creating tag of name has failed");
        }
        return giftCertificate;
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public void updateGiftCertificate(GiftCertificate giftCertificate) throws EmptyResultDataAccessException {
        String query = "UPDATE gift_certificate " +
                "SET name = :name, description = :description, price = :price, duration = :duration, last_update_date = :last_update_date " +
                "WHERE id = :id";
        try {
            findById(giftCertificate.getId());
            giftCertificate.setLastUpdateDate(LocalDateTime.now());
            Map<String, Object> map = new HashMap<>();
            map.put("name", giftCertificate.getName());
            map.put("description", giftCertificate.getDescription());
            map.put("price", giftCertificate.getPrice());
            map.put("duration", giftCertificate.getDuration());
            map.put("last_update_date", giftCertificate.getLastUpdateDate());
            map.put("id", giftCertificate.getId());
            namedParameterJdbcTemplate.update(query, map);
        } catch (Exception exception) {
            logger.error("Updating giftCertificate of id has failed");
            throw new EmptyResultDataAccessException(0);
        }
    }

    /**
     * @See com.epam.esm.dao.GiftCertificateDAO
     */
    @Override
    public void deleteGiftCertificate(int id) throws EmptyResultDataAccessException {
        String query = "DELETE FROM gift_certificate WHERE id=?";
        try {
            findById(id);
            jdbcTemplate.update(query, id);
        } catch (EmptyResultDataAccessException exception) {
            logger.error("Deleting giftCertificate of id has failed");
            throw new EmptyResultDataAccessException(0);
        }
    }
}

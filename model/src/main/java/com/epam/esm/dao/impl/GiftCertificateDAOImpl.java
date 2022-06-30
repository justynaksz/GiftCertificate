package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.rowmapper.GiftCertificateRowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements CRUD operations for GiftCertificate entity.
 */
@Repository
public class GiftCertificateDAOImpl extends AbstractDAO implements GiftCertificateDAO {

    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Finds giftCertificate of given id value.
     *
     * @param id int id value
     * @return giftCertificate     giftCertificate of given id value
     */
    @Override
    public GiftCertificate findById(int id) {
        GiftCertificate giftCertificate = null;
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate WHERE id = ?";
        try {
            giftCertificate = getJdbcTemplate().queryForObject(query, new GiftCertificateRowMapper(), id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Selecting giftCertificate of id \"" + giftCertificate.getId() + "\" has failed", e);
        }
       return giftCertificate;
    }

    /**
     * Finds giftCertificates assigned to given tagName value.
     *
     * @param tagName String value of tag's name
     * @return lists of giftCertificates     giftCertificates assigned to given tag's name
     */
    @Override
    public List<GiftCertificate> findByTag(String tagName) {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT tag.name, gift_certificate.id, gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, gift_certificate.create_date, gift_certificate.last_update_date " +
                "FROM gift_certificate " +
                "JOIN gift_certificate_tag ON gift_certificate.id = gift_certificate_tag.gift_certificate_id " +
                "JOIN tag ON tag.id = gift_certificate_tag.tag_id " +
                "WHERE tag.name = ?";
        try {
            giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper(), tagName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Finding giftCertificate by tag name \"" + tagName + "\" has failed", e);
        }
        return giftCertificates;
    }

    /**
     * Finds giftCertificates by part of name or description.
     *
     * @param key String value of desired name/description word
     * @return lists of giftCertificates    giftCertificates containing key word in their name or description
     */
    @Override
    public List<GiftCertificate> findByNameOrDescription(String key) {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate WHERE name LIKE ? OR description LIKE ?";
        try {
            giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper(), "%" + key + "%", "%" + key + "%");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Finding giftCertificate by key word \"" + key + "\" has failed", e);
        }
            return giftCertificates;
    }

    /**
     * Finds all giftCertificates.
     *
     * @return lists of giftCertificates    all giftcertificates
     */
    @Override
    public List<GiftCertificate> findAll() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate";
        try {
            giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Finding all giftCertificates has failed", e);
        }
        return giftCertificates;
    }

    /**
     * Sorts giftCertificates by ascending order.
     *
     * @return giftCertificates in ascending order
     */
    @Override
    public List<GiftCertificate> sortAscending() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate ORDER BY name ASC";
        try {
            giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Sorting giftCertificates in ascending order has failed.", e);
        }
        return giftCertificates;
    }

    /**
     * Sorts giftCertificates by descending order.
     *
     * @return giftCertificates in descending order
     */
    @Override
    public List<GiftCertificate> sortDescending() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String query = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificate ORDER BY name DESC";
        try {
            giftCertificates = getJdbcTemplate().query(query, new GiftCertificateRowMapper());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Sorting giftCertificates in descending order has failed.", e);
        }
        return giftCertificates;
    }

    /**
     * Creates new giftCertificate entity.
     *
     * @param giftCertificate GiftCertificate instance to be inserted into database
     * @return giftCertificate    GiftCertificate instance with specified id value that has been inserted into database
     */
    @Override
    public GiftCertificate createGiftCertificate(GiftCertificate giftCertificate) {
        String query = "INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) " +
                "VALUES (:name, :description, :price, :duration, :create_date, :last_update_date);";
        Map<String, Object> map = new HashMap();

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
            super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder, new String[]{"id"});
            int id = keyHolder.getKey().intValue();
            giftCertificate.setId(id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Creating tag of name \"" + giftCertificate.getName() + "\" has failed", e);
        }
        return giftCertificate;
    }

    /**
     * Updates giftCertificate contained in database.
     *
     * @param giftCertificate GiftCertificate instance to be updated in database
     */
    @Override
    public void updateGiftCertificate(GiftCertificate giftCertificate) {
        String query = "UPDATE gift_certificate " +
                "SET name = :name, description = :description, price = :price, duration = :duration, last_update_date = :last_update_date " +
                "WHERE id = :id";
        try {
            giftCertificate.setLastUpdateDate(LocalDateTime.now());
            Map<String, Object> map = new HashMap();
            map.put("name", giftCertificate.getName());
            map.put("description", giftCertificate.getDescription());
            map.put("price", giftCertificate.getPrice());
            map.put("duration", giftCertificate.getDuration());
            map.put("last_update_date", giftCertificate.getLastUpdateDate());
            map.put("id", giftCertificate.getId());
            getNamedParameterJdbcTemplate().update(query, map);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Updating giftCertificate of id \"" + giftCertificate.getId() + "\" has failed", e);
        }
    }

    /**
     * Deletes giftCertificate of given id value.
     *
     * @param id int id value of giftCertificate instance to be removed
     */
    @Override
    public void deleteGiftCertificate(int id) {
        String query = "DELETE FROM gift_certificate WHERE id=?";
        try {
            getJdbcTemplate().update(query, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Deleting giftCertificate of id \"" + id + "\" has failed", e);
        }
    }
}

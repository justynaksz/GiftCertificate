package com.epam.esm.DAOImpl;

import com.epam.esm.DAO.AbstractDAO;
import com.epam.esm.DAO.GiftCertificateTagDAO;
import com.epam.esm.model.GiftCertificateTag;
import com.epam.esm.rowMapper.GiftCertificateTagRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements CRUD operations for GiftCertificateTag entity.
 */
@Repository
public class GiftCertificateTagDAOImpl extends AbstractDAO implements GiftCertificateTagDAO {

    /**
     * Finds giftCertificateTag of given id value.
     * @param  id                     int id value
     * @return giftCertificateTag     giftCertificateTag of given id value
     */
    @Override
    public GiftCertificateTag findGiftCertificateTagById(int id) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE id = ?";
        GiftCertificateTag giftCertificateTag = getJdbcTemplate().queryForObject(query, new GiftCertificateTagRowMapper(), id);
        return giftCertificateTag;
    }

    /**
     * Finds giftCertificateTags of given giftCertificateId value.
     * @param  giftCertificateId                int giftCertificateId value
     * @return list of giftCertificateTags      giftCertificateTags of given giftCertificateId value
     */
    @Override
    public List<GiftCertificateTag> findGiftCertificateTagByGiftCertificateId(int giftCertificateId) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE gift_certificate_id = ?";
        List<GiftCertificateTag> giftCertificateTags = getJdbcTemplate().query(query, new GiftCertificateTagRowMapper(), giftCertificateId);
        return giftCertificateTags;
    }

    /**
     * Finds giftCertificateTags of given tagId value.
     * @param  tagId                            int tagId value
     * @return list of giftCertificateTags      giftCertificateTags of given tagId value
     */
    @Override
    public List<GiftCertificateTag> findGiftCertificateTagByTagId(int tagId) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE tag_id = ?";
        List<GiftCertificateTag> giftCertificateTags = getJdbcTemplate().query(query, new GiftCertificateTagRowMapper(), tagId);
        return giftCertificateTags;
    }

    /**
     * Finds all giftCertificateTags.
     *
     * @return lists of giftCertificateTags    all giftcertificateTags
     */
    @Override
    public List<GiftCertificateTag> findAllGiftCertificateTag() {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag";
        List<GiftCertificateTag> giftCertificateTags = getJdbcTemplate().query(query, new GiftCertificateTagRowMapper());
        return giftCertificateTags;
    }

    /**
     * Creates new giftCertificateTag entity.
     * @param  giftCertificateTag     GiftCertificateTag instance to be inserted into database
     * @return giftCertificateTag     GiftCertificateTag instance with specified id value that has been inserted into database
     */
    @Override
    public GiftCertificateTag createGiftCertificateTag(GiftCertificateTag giftCertificateTag) {
        String query = "INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) " +
             "VALUES (:gift_certificate_id, :tag_id)";
        Map map = new HashMap();
        map.put("gift_certificate_id", giftCertificateTag.getGiftCertificateId());
        map.put("tag_id", giftCertificateTag.getTagId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        int id = keyHolder.getKey().intValue();
        giftCertificateTag.setId(id);
        return giftCertificateTag;
    }

    /**
     * Deletes giftCertificateTag of given id value.
     * @param id     int id value of giftCertificateTag instance to be removed
     */
    @Override
    public void deleteGiftCertificateTag(int id) {
        String query = "DELETE FROM gift_certificate_tag WHERE id = ?";
        getJdbcTemplate().update(query, id);
    }
}

package com.epam.DAOImpl;

import com.epam.DAO.AbstractDAO;
import com.epam.DAO.GiftCertificate_TagDAO;
import com.epam.model.GiftCertificate_Tag;
import com.epam.rowMapper.GiftCertificateTagRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftCertificate_TagDAOImpl extends AbstractDAO implements GiftCertificate_TagDAO {
    @Override
    public GiftCertificate_Tag findGiftCertificate_TagById(int id) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE id = ?";
        GiftCertificate_Tag giftCertificateTag = getJdbcTemplate().queryForObject(query, new GiftCertificateTagRowMapper(), id);
        return giftCertificateTag;
    }

    @Override
    public List<GiftCertificate_Tag> findGiftCertificate_TagByGiftCertificateId(int giftCertificateId) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE gift_certificate_id = ?";
        List<GiftCertificate_Tag> giftCertificateTags = getJdbcTemplate().query(query, new GiftCertificateTagRowMapper(), giftCertificateId);
        return giftCertificateTags;
    }

    @Override
    public List<GiftCertificate_Tag> findGiftCertificate_TagByTagId(int tagId) {
        String query = "SELECT id, gift_certificate_id, tag_id FROM gift_certificate_tag WHERE tag_id = ?";
        List<GiftCertificate_Tag> giftCertificateTags = getJdbcTemplate().query(query, new GiftCertificateTagRowMapper(), tagId);
        return giftCertificateTags;
    }

    @Override
    public GiftCertificate_Tag createGiftCertificate_Tag(GiftCertificate_Tag giftCertificate_Tag) {
        String query = "INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) " +
             "VALUES (:gift_certificate_id, :tag_id)";
        Map map = new HashMap();
        map.put("gift_certificate_id", giftCertificate_Tag.getGiftCertificateId());
        map.put("tag_id", giftCertificate_Tag.getTagId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        int id = keyHolder.getKey().intValue();
        giftCertificate_Tag.setId(id);
        return giftCertificate_Tag;
    }

    @Override
    public void deleteGiftCertificate_Tag(int id) {
        String query = "DELETE FROM gift_certificate_tag WHERE id = ?";
        getJdbcTemplate().update(query, id);
    }
}

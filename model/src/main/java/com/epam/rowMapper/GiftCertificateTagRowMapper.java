package com.epam.rowMapper;

import com.epam.model.GiftCertificate_Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateTagRowMapper implements RowMapper<GiftCertificate_Tag> {
    @Override
    public GiftCertificate_Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate_Tag giftCertificateTag = new GiftCertificate_Tag();
        giftCertificateTag.setId(rs.getInt("id"));
        giftCertificateTag.setGiftCertificateId(rs.getInt("gift_certificate_id"));
        giftCertificateTag.setTagId(rs.getInt("tag_id"));
        return giftCertificateTag;
    }
}

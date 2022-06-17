package com.epam.esm.rowMapper;

import com.epam.esm.model.GiftCertificateTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation which maps each row to a result GiftCertificateTag object.
 */
public class GiftCertificateTagRowMapper implements RowMapper<GiftCertificateTag> {

    /**
     * Maps each row of data in the ResultSet to GiftCertificateTag object.
     * @param rs                    ResultSet to be mapped
     * @param rowNum                the number of current row
     * @return giftCertificateTag   GiftCertificateTag instance
     */
    @Override
    public GiftCertificateTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificateTag giftCertificateTag = new GiftCertificateTag();
        giftCertificateTag.setId(rs.getInt("id"));
        giftCertificateTag.setGiftCertificateId(rs.getInt("gift_certificate_id"));
        giftCertificateTag.setTagId(rs.getInt("tag_id"));
        return giftCertificateTag;
    }
}

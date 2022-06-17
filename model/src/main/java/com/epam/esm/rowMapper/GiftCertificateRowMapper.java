package com.epam.esm.rowMapper;

import com.epam.esm.model.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

/**
 * Implementation which maps each row to a result GiftCertificateTag object.
 */
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

    /**
     * Maps each row of data in the ResultSet to GiftCertificate object.
     * @param rs                 ResultSet to be mapped
     * @param rowNum             the number of current row
     * @return giftCertificate   GiftCertificate instance
     */
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getInt("id"));
        giftCertificate.setName(rs.getString("name"));
        giftCertificate.setDescription(rs.getString("description"));
        giftCertificate.setPrice(rs.getDouble("price"));
        giftCertificate.setDuration((Duration) rs.getObject("duration"));
        return giftCertificate;
        // TODO Logger to add
    }
}

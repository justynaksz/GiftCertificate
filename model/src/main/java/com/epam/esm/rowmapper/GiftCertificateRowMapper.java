package com.epam.esm.rowmapper;

import com.epam.esm.model.GiftCertificate;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation which maps each row to a result GiftCertificateTag object.
 */
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Maps each row of data in the ResultSet to GiftCertificate object.
     * @param rs                 ResultSet to be mapped
     * @param rowNum             the number of current row
     * @return giftCertificate   GiftCertificate instance
     * @throws SQLException
     */
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        try {
            giftCertificate.setId(rs.getInt("id"));
            giftCertificate.setName(rs.getString("name"));
            giftCertificate.setDescription(rs.getString("description"));
            giftCertificate.setPrice(rs.getDouble("price"));
            giftCertificate.setDuration(rs.getLong("duration"));
            giftCertificate.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            if (rs.getTimestamp("last_update_date") != null) {
                giftCertificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());
            } else {
                giftCertificate.setLastUpdateDate(null);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return giftCertificate;
    }
}

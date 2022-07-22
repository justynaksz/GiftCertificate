package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.stereotype.Component;

/**
 * Mapper class to transform {@code GiftCertificate} and {@code GiftCertificateDTO} types.
 */
@Component
public class GiftCertificateMapper {

    /**
     * Migrate {@code GiftCertificateDTO} object to {@code GiftCertificate} type.
     * @param giftCertificateDTO        object to transform into {@code GiftCertificate}
     * @return giftCertificate          transformed {@code GiftCertificate}
     */
    public GiftCertificate toModel(GiftCertificateDTO giftCertificateDTO) {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(giftCertificateDTO.getName());
        giftCertificate.setDescription(giftCertificateDTO.getDescription());
        giftCertificate.setPrice(giftCertificateDTO.getPrice());
        giftCertificate.setDuration(giftCertificateDTO.getDuration());
        giftCertificate.setCreateDate(giftCertificateDTO.getCreateDate());
        giftCertificate.setLastUpdateDate(giftCertificateDTO.getLastUpdateDate());
        return giftCertificate;
    }

    /**
     * Migrate {@code GiftCertificate} object to {@code GiftCertificateDTO} type.
     * @param giftCertificate       object to transform into {@code GiftCertificateDTO}
     * @return giftCertificateDTO   transformed {@code GiftCertificateDTO}
     */
    public GiftCertificateDTO toDTO (GiftCertificate giftCertificate) {
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setName(giftCertificate.getName());
        giftCertificateDTO.setDescription(giftCertificate.getDescription());
        giftCertificateDTO.setPrice(giftCertificate.getPrice());
        giftCertificateDTO.setDuration(giftCertificate.getDuration());
        giftCertificateDTO.setCreateDate(giftCertificate.getCreateDate());
        giftCertificateDTO.setLastUpdateDate(giftCertificate.getLastUpdateDate());
        return giftCertificateDTO;
    }
}

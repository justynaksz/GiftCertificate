package com.epam.DAO;

import com.epam.model.GiftCertificate_Tag;

import java.util.List;

public interface GiftCertificate_TagDAO {

    GiftCertificate_Tag findGiftCertificate_TagById(int id);

    List<GiftCertificate_Tag> findGiftCertificate_TagByGiftCertificateId(int giftCertificateId);

    List<GiftCertificate_Tag> findGiftCertificate_TagByTagId(int tagId);

    GiftCertificate_Tag createGiftCertificate_Tag(GiftCertificate_Tag giftCertificate_Tag);

    void deleteGiftCertificate_Tag(int id);
}

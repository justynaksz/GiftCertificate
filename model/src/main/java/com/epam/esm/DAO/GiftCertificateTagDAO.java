package com.epam.esm.DAO;

import com.epam.esm.model.GiftCertificateTag;

import java.util.List;

public interface GiftCertificateTagDAO {

    GiftCertificateTag findGiftCertificateTagById(int id);

    List<GiftCertificateTag> findGiftCertificateTagByGiftCertificateId(int giftCertificateId);

    List<GiftCertificateTag> findGiftCertificateTagByTagId(int tagId);

    List<GiftCertificateTag> findAllGiftCertificateTag();

    GiftCertificateTag createGiftCertificateTag(GiftCertificateTag giftCertificate_Tag);

    void deleteGiftCertificateTag(int id);
}

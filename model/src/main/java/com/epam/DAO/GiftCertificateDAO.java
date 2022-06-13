package com.epam.DAO;

import com.epam.model.GiftCertificate;
import com.epam.model.Tag;

import java.util.List;

public interface GiftCertificateDAO {

    GiftCertificate findById(int id);

    List<GiftCertificate> findByTag(Tag tag);

    List<GiftCertificate> findByName(String name);

    List<GiftCertificate> findByDescription(String description);

    List<GiftCertificate> sortAscending();

    List<GiftCertificate> sortDescending();

    GiftCertificate createGiftCertificate(GiftCertificate giftCertificate);

    GiftCertificate updateGiftCertificate(GiftCertificate giftCertificate);

    void deleteGiftCertificate(GiftCertificate giftCertificate);
}

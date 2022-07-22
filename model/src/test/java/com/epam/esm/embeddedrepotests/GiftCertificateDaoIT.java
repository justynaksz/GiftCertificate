package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.DataSourceConfig;

import org.assertj.core.api.SoftAssertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateDaoIT {

    @Autowired
    GiftCertificateDAOImpl giftCertificateDAOImpl;
    @Autowired
    GiftCertificate giftCertificate;
    @Autowired
    GiftCertificate giftCertificateInserted;
    @Autowired
    GiftCertificate giftCertificateRetrieved;

    List<GiftCertificate> giftCertificatesInDb = new ArrayList<>();

    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    @Order(1)
    @DisplayName("find by id - gift certificate is correctly found")
    void findByIdShouldReturnCorrectGiftCertificate() {
        // GIVEN

        // WHEN
        giftCertificateRetrieved = giftCertificateDAOImpl.findById(1);
        giftCertificate.setName("H&M gift card");
        giftCertificate.setDescription("Gift card to the fashion store");
        giftCertificate.setPrice(100.00);
        giftCertificate.setDuration(90);
        giftCertificate.setId(1);
        giftCertificate.setCreateDate(LocalDateTime.parse("2022-06-22T18:31:44.574"));
        giftCertificate.setLastUpdateDate(null);
        // THEN
        assertEquals(giftCertificate, giftCertificateRetrieved);
    }

    @Test
    @Order(2)
    @DisplayName("find by id - gift certificate of given id doesn't exist")
    void findByIdShouldThrowExceptionIfGiftCertificateDoesNotExist() {
        // GIVEN
        int id = 999;
        // WHEN

        // THEN
        assertThrows(EmptyResultDataAccessException.class, () -> giftCertificateDAOImpl.findById(id));
    }

    @Test
    @Order(3)
    @DisplayName("find by tag - gift certificates are correctly found")
    void findByTagShouldReturnListOfGiftCertificatesWithSpecifiedTag() {
        // GIVEN
        String requestedName = "shopping";
        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findByTag(requestedName);
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    @Test
    @Order(4)
    @DisplayName("find by tag - no matching gift certificate found")
    void findByTagShouldReturnEmptyListWhenNoGiftCertificateMatchRequest() {
        // GIVEN
        String requestedName = "football";
        // WHEN

        // THEN
        assertTrue(giftCertificateDAOImpl.findByTag(requestedName).isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("find by name or description - gift certificates are correctly found")
    void findByNameOrDescriptionShouldReturnListOfGiftCertificatesWithKeyWordInNameOrDescription() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findByNameOrDescription("store");
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    @Test
    @Order(6)
    @DisplayName("find by name or description - no matching gift certificate found")
    void findByNameOrDescriptionShouldReturnEmptyListWhenNoGiftCertificateMatchRequest() {
        // GIVEN
        String key = "swimming";
        // WHEN

        // THEN
        assertTrue(giftCertificateDAOImpl.findByNameOrDescription(key).isEmpty());
    }

    @Test
    @Order(7)
    @DisplayName("find all gift certificates test ")
    void shouldReturnListOfAllGiftCertificates() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findAll();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
    }

    @Test
    @Order(8)
    @DisplayName("sort in ascending order test")
    void checksIfListSizeAndASCOrderIsCorrect() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.sortAscending();
        // THEN
        softAssertions.assertThat(3).isEqualTo(giftCertificatesInDb.size());
        softAssertions.assertThat("Gift card to cafe").isEqualTo(giftCertificatesInDb.get(2).getDescription());
        softAssertions.assertThat("Gift card to the fashion store").isEqualTo(giftCertificatesInDb.get(0).getDescription());
        softAssertions.assertAll();
    }

    @Test
    @Order(9)
    @DisplayName("sort in descending order test")
    void checksIfListSizeAndDSCOrderIsCorrect() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.sortDescending();
        // THEN
        softAssertions.assertThat(3).isEqualTo(giftCertificatesInDb.size());
        softAssertions.assertThat("Gift card to cafe").isEqualTo(giftCertificatesInDb.get(0).getDescription());
        softAssertions.assertThat("Gift card to the fashion store").isEqualTo(giftCertificatesInDb.get(2).getDescription());
        softAssertions.assertAll();
    }

    @Test
    @Order(10)
    @DisplayName("create gift certificate test")
    void specificGiftCertificateIsPresentInDbAndCountOfGiftCertificatesInDbIsCorrect() {
        // GIVEN
        giftCertificate.setName("Paintball voucher");
        giftCertificate.setDescription("2 hours of paintball match in Paintball-World");
        giftCertificate.setPrice(49.99);
        giftCertificate.setDuration(180);
        int initialDbSize = giftCertificateDAOImpl.findAll().size();
        int expectedDBSizeChange = 1;
        // WHEN
        giftCertificateInserted = giftCertificateDAOImpl.createGiftCertificate(giftCertificate);
        // THEN
        softAssertions.assertThat(giftCertificateDAOImpl.findAll().contains(giftCertificateInserted)).isTrue();
        softAssertions.assertThat(giftCertificate).isEqualTo(giftCertificateInserted);
        softAssertions.assertThat(initialDbSize + expectedDBSizeChange).isEqualTo(giftCertificateDAOImpl.findAll().size());
        softAssertions.assertAll();
    }

    @Test
    @Order(11)
    @DisplayName("update - gift certificate is correctly updated")
    void sizeOfDbIsNotChangedAndGiftCertificateIsCorrectlyUpdated() {
        // GIVEN
        int initDatabaseSize = giftCertificateDAOImpl.findAll().size();
        giftCertificate = giftCertificateDAOImpl.findById(1);
        giftCertificate.setDescription("Gift card to the fashion shop");
        // WHEN
        giftCertificateDAOImpl.updateGiftCertificate(giftCertificate);
        // THEN
        softAssertions.assertThat(initDatabaseSize).isEqualTo(giftCertificateDAOImpl.findAll().size());
        softAssertions.assertThat(giftCertificate).isEqualTo(giftCertificateDAOImpl.findById(1));
        softAssertions.assertAll();
    }

    @Test
    @Order(12)
    @DisplayName("update - update non existing gift certificate")
    void updateNonExistingGiftCertificateShouldTrowException() {
        // GIVEN

        // WHEN
        giftCertificate.setName("Paintball voucher");
        giftCertificate.setDescription("2 hours of paintball match in Paintball-World");
        giftCertificate.setPrice(49.99);
        giftCertificate.setDuration(180);
        giftCertificate.setId(999);
        // THEN
        assertThrows(EmptyResultDataAccessException.class, () -> giftCertificateDAOImpl.updateGiftCertificate(giftCertificate));
    }

    @Test
    @Order(13)
    @DisplayName("delete - gift certificate is correctly removed")
    void countOfGiftCertificatesInDbHasShrunkAfterDeletingAndGiftCertificateIsNotPresentInDb() {
        // GIVEN
        int dbSize = giftCertificateDAOImpl.findAll().size();
        int expectedDBSizeChange = 1;
        int requestedId = 1;
        // WHEN
        giftCertificate = giftCertificateDAOImpl.findById(requestedId);
        giftCertificateDAOImpl.deleteGiftCertificate(requestedId);
        // THEN
        softAssertions.assertThat(dbSize - expectedDBSizeChange).isEqualTo(giftCertificateDAOImpl.findAll().size());
        softAssertions.assertThat(giftCertificateDAOImpl.findAll().contains(giftCertificate)).isFalse();
        softAssertions.assertAll();

    }

    @Test
    @Order(14)
    @DisplayName("delete - delete non existing gift certificate test")
    void deleteNonExistingGiftCertificateShouldTrowException() {
        // GIVEN
        int requestedId = 999;
        // WHEN

        // THEN
        assertThrows(EmptyResultDataAccessException.class, () -> giftCertificateDAOImpl.deleteGiftCertificate(requestedId));
    }
}

DROP TABLE IF EXISTS "gift_certificate_tag";
DROP TABLE IF EXISTS "gift_certificate";
DROP TABLE IF EXISTS "tag";

CREATE TABLE "gift_certificate"(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(40) NOT NULL,
    "description" VARCHAR(200) NOT NULL,
    "price" DECIMAL(7, 2) NOT NULL,
    "duration" INT NOT NULL,
    "create_date" TIMESTAMP NOT NULL,
    "last_update_date" TIMESTAMP
);

CREATE TABLE "tag"(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(40) NOT NULL UNIQUE
);


CREATE TABLE "gift_certificate_tag"(
    "id" SERIAL PRIMARY KEY,
    "gift_certificate_id" BIGINT NOT NULL,
    "tag_id" BIGINT NOT NULL
    );

ALTER TABLE
    "gift_certificate_tag" ADD CONSTRAINT "gift_certificate_tag_gift_certificate_id_foreign" FOREIGN KEY("gift_certificate_id") REFERENCES "gift_certificate"("id");
ALTER TABLE
    "gift_certificate_tag" ADD CONSTRAINT "gift_certificate_tag_tag_id_foreign" FOREIGN KEY("tag_id") REFERENCES "tag"("id");

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('H&M gift card', 'Gift card to the fashion store', 100.00, 90, CURRENT_TIMESTAMP, NULL);
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('Sweet coffee', 'Gift card to cafe', 50.00, 365, CURRENT_TIMESTAMP, NULL);
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('Ruby store', 'Gift card to jewellery store', 1000.00, 30, CURRENT_TIMESTAMP, NULL);

INSERT INTO tag (name) VALUES ('sweets');
INSERT INTO tag (name) VALUES ('fashion');
INSERT INTO tag (name) VALUES ('jewellery');
INSERT INTO tag (name) VALUES ('beauty&spa');
INSERT INTO tag (name) VALUES ('coffee');
INSERT INTO tag (name) VALUES ('shopping');

INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (1, 2);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (1, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (1, 6);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (2, 1);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (2, 5);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (3, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (3, 6);
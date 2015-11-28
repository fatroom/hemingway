INSERT INTO author (name) VALUES ('Mark Twain');
INSERT INTO author (name) VALUES ('Unknown journalist');
INSERT INTO author (name) VALUES ('Jane Doe');
INSERT INTO author (name) VALUES ('John Doe');


INSERT INTO article (header, short_description, text, publish_date) VALUES ('The Awful German Language', 'A little learning makes the whole world kin', 'I went often to look at the collection of curiosities in Heidelberg Castle, and one day I surprised the keeper of it with my German...', to_date('1880-06-15', 'YYYY-MM-DD'));
INSERT INTO article_keywords (article_id, keywords) VALUES (1, 'essay');
INSERT INTO article_authors (articles_id, authors_id) VALUES (1, 1);

INSERT INTO article (header, short_description, text, publish_date) VALUES ('An Unearthly Child', 'Trust Your Doctor', 'BBC premiered a show about an alien Who traveled through space and time to combat the powers of evil...', to_date('1963-11-23', 'YYYY-MM-DD'));
INSERT INTO article_keywords (article_id, keywords) VALUES (2, 'announcement');
INSERT INTO article_keywords (article_id, keywords) VALUES (2, 'tv');
INSERT INTO article_authors (articles_id, authors_id) VALUES (2, 2);

INSERT INTO article (header, short_description, text, publish_date) VALUES ('Big Damn Heroes, sir!', 'Ain''t we just', 'Take my love, take my land, Take me where I cannot stand, I don''t care, I''m still free, You can''t take the sky from me', to_date('2002-11-23', 'YYYY-MM-DD'));
INSERT INTO article_keywords (article_id, keywords) VALUES (3, 'tv');
INSERT INTO article_keywords (article_id, keywords) VALUES (3, 'review');
INSERT INTO article_authors (articles_id, authors_id) VALUES (3, 3);

INSERT INTO article (header, short_description, text, publish_date) VALUES ('Rick and Morty', 'Wubba Lubba Dub Dub!', 'Oh, unbelievable. We got... we got a bunch of robot... computer people sitting around with their... faces stuffed in their computer screens... Ar-ar-are you people even human?', to_date('2013-11-23', 'YYYY-MM-DD'));
INSERT INTO article_keywords (article_id, keywords) VALUES (4, 'tv');
INSERT INTO article_authors (articles_id, authors_id) VALUES (4, 4);

INSERT INTO article (header, short_description, text, publish_date) VALUES ('Paprika', 'Time for the greatest show on earth!', 'Three scientists at the Foundation for Psychiatric Research fail to secure a device they''ve invented, the D.C. Mini, which allows people to record and watch their dreams...', to_date('2006-03-15', 'YYYY-MM-DD'));
INSERT INTO article_keywords (article_id, keywords) VALUES (5, 'tv');
INSERT INTO article_authors (articles_id, authors_id) VALUES (5, 3);
INSERT INTO article_authors (articles_id, authors_id) VALUES (5, 4);

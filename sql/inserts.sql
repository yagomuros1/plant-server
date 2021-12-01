INSERT INTO difficulty (name)
VALUES ('Baja'),('Media'),('Alta');

INSERT INTO situation (name)
VALUES ('Interior'),('Exterior');

INSERT INTO category (name)
VALUES ('Tubérculos'),('Plantas'),('Legumbres'),('Decorativas');

INSERT INTO property (title, subtitle, value) VALUES ('Cantidad de sol', 'Natural', '4h');
INSERT INTO property (title, subtitle, value) VALUES ('Cantidad de riego', 'Ligero-Frecuente', '3-5 días');
INSERT INTO property (title, subtitle, value) VALUES ('Temperatura recomendada', 'Clima cálido', '20º-25º');
INSERT INTO property (title, subtitle, value) VALUES ('Tipo de tierra', 'Explicación breve', 'Arenosa');
INSERT INTO property (title, subtitle, value) VALUES ('Transplante', 'Explicación breve', 'No necesita');

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Zanahorias','Son plantas excelentes para la vista', "https://soycomocomo.es/media/2019/03/zanahorias.jpg", "Asegurate de que tome el sol", 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Patatas','Son para feir', "https://s1.eestatic.com/2020/10/09/ciencia/nutricion/patatas-adelgazar-dieta_526958892_162156492_1024x576.jpg", "Añade pesticidas", 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Tomates','De los rojos rojos', "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/251px-Bright_red_tomato_and_cross_section02.jpg", "Cuidado con los caracoles", 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Albahaca','Mmmm como huele!', "https://agroactivocol.com/wp-content/uploads/2020/08/7e2db098-albahaca-basil-adobestock_81129315-scaled-1.jpg", "Cuidado con el frio!", 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Menta','Para un mojito', "https://agroactivocol.com/wp-content/uploads/2020/08/HIERBABUENA.jpg", "Hay que podarla", 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Fresas','Tiene muchas vitaminas', "https://static.wikia.nocookie.net/esharrypotter/images/7/76/Fresa.jpg/revision/latest/top-crop/width/360/height/450?cb=20200713112301", "Les encatan a las babosas", 5, 5);

INSERT INTO crop_category (crop_id, category_id) VALUES (5, 5);
INSERT INTO crop_category (crop_id, category_id) VALUES (5, 15);
INSERT INTO crop_category (crop_id, category_id) VALUES (15, 25);
INSERT INTO crop_category (crop_id, category_id) VALUES (25, 5);
INSERT INTO crop_category (crop_id, category_id) VALUES (25, 15);
INSERT INTO crop_category (crop_id, category_id) VALUES (25, 25);

INSERT INTO crop_property (crop_id, property_id) VALUES (5, 5);
INSERT INTO crop_property (crop_id, property_id) VALUES (5, 25);

INSERT INTO crop_property (crop_id, property_id) VALUES (25, 15);
INSERT INTO crop_property (crop_id, property_id) VALUES (25, 5);

INSERT INTO crop_property (crop_id, property_id) VALUES (15, 5);
INSERT INTO crop_property (crop_id, property_id) VALUES (15, 15);
INSERT INTO crop_property (crop_id, property_id) VALUES (15, 25);
INSERT INTO crop_property (crop_id, property_id) VALUES (15, 35);

INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (5, 15, 1);
INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (5, 25, 1);
INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (5, 35, 0);

INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (15, 5, 1);
INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (15, 25, 0);

INSERT INTO crop_companion (crop_id, crop_companion_id, is_good_companion) VALUES (35, 5, 0);


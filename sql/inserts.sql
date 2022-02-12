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
VALUES ('Zanahorias','Son plantas excelentes para la vista', "https://soycomocomo.es/media/2019/03/zanahorias.jpg", "Asegurate de que tome el sol", 1, 1);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Patatas','Son para feir', "https://s1.eestatic.com/2020/10/09/ciencia/nutricion/patatas-adelgazar-dieta_526958892_162156492_1024x576.jpg", "Añade pesticidas", 1, 1);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Tomates','De los rojos rojos', "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/251px-Bright_red_tomato_and_cross_section02.jpg", "Cuidado con los caracoles", 1, 1);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Albahaca','Mmmm como huele!', "https://agroactivocol.com/wp-content/uploads/2020/08/7e2db098-albahaca-basil-adobestock_81129315-scaled-1.jpg", "Cuidado con el frio!", 1, 1);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Menta','Para un mojito', "https://agroactivocol.com/wp-content/uploads/2020/08/HIERBABUENA.jpg", "Hay que podarla", 1, 1);

INSERT INTO crop (name, description, image, conservation, difficulty_id, situation_id)
VALUES ('Fresas','Tiene muchas vitaminas', "https://static.wikia.nocookie.net/esharrypotter/images/7/76/Fresa.jpg/revision/latest/top-crop/width/360/height/450?cb=20200713112301", "Les encatan a las babosas", 1, 1);

INSERT INTO crop_category (crop_id, category_id) VALUES (1, 1);
INSERT INTO crop_category (crop_id, category_id) VALUES (1, 3);
INSERT INTO crop_category (crop_id, category_id) VALUES (3, 4);
INSERT INTO crop_category (crop_id, category_id) VALUES (5, 1);
INSERT INTO crop_category (crop_id, category_id) VALUES (5, 3);
INSERT INTO crop_category (crop_id, category_id) VALUES (5, 2);

INSERT INTO crop_property (crop_id, property_id) VALUES (1, 1);
INSERT INTO crop_property (crop_id, property_id) VALUES (1, 5);

INSERT INTO crop_property (crop_id, property_id) VALUES (5, 3);
INSERT INTO crop_property (crop_id, property_id) VALUES (5, 1);

INSERT INTO crop_property (crop_id, property_id) VALUES (3, 1);
INSERT INTO crop_property (crop_id, property_id) VALUES (3, 3);
INSERT INTO crop_property (crop_id, property_id) VALUES (3, 5);
INSERT INTO crop_property (crop_id, property_id) VALUES (3, 4);

INSERT INTO companion (external_id, name, image) VALUES (1, 'Zanahorias', 'https://soycomocomo.es/media/2019/03/zanahorias.jpg');
INSERT INTO companion (external_id, name, image) VALUES (2, 'Patatas', 'https://s1.eestatic.com/2020/10/09/ciencia/nutricion/patatas-adelgazar-dieta_526958892_162156492_1024x576.jpg');
INSERT INTO companion (external_id, name, image) VALUES (3, 'Tomates', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/251px-Bright_red_tomato_and_cross_section02.jpg');
INSERT INTO companion (external_id, name, image) VALUES (4, 'Albahaca', 'https://agroactivocol.com/wp-content/uploads/2020/08/7e2db098-albahaca-basil-adobestock_81129315-scaled-1.jpg');
INSERT INTO companion (external_id, name, image) VALUES (5, 'Menta', 'https://agroactivocol.com/wp-content/uploads/2020/08/HIERBABUENA.jpg');
INSERT INTO companion (external_id, name, image) VALUES (6, 'Fresas', 'https://static.wikia.nocookie.net/esharrypotter/images/7/76/Fresa.jpg/revision/latest/top-crop/width/360/height/450?cb=20200713112301');

INSERT INTO crop_companion (crop_id, companion_id) VALUES (1, 1);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (1, 3);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (2, 2);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (2, 4);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (3, 5);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (4, 6);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (5, 5);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (5, 3);
INSERT INTO crop_companion (crop_id, companion_id) VALUES (6, 2);
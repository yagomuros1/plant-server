INSERT INTO difficulty (name)
VALUES ('Baja'),('Media'),('Alta');

INSERT INTO situation (name)
VALUES ('Interior'),('Exterior');

INSERT INTO category (name)
VALUES ('Tubérculos'),('Planta');

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Zanahorias','Son plantas excelentes para la vista', "https://soycomocomo.es/media/2019/03/zanahorias.jpg", "Asegurate de que tome el sol", 5, 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Patatas','Son para feir', "https://s1.eestatic.com/2020/10/09/ciencia/nutricion/patatas-adelgazar-dieta_526958892_162156492_1024x576.jpg", "Añade pesticidas", 5, 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Tomates','De los rojos rojos', "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/251px-Bright_red_tomato_and_cross_section02.jpg", "Cuidado con los caracoles", 5, 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Albahaca','Mmmm como huele!', "https://agroactivocol.com/wp-content/uploads/2020/08/7e2db098-albahaca-basil-adobestock_81129315-scaled-1.jpg", "Cuidado con el frio!", 5, 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Menta','Para un mojito', "https://agroactivocol.com/wp-content/uploads/2020/08/HIERBABUENA.jpg", "Hay que podarla", 5, 5, 5);

INSERT INTO crop (name, description, image, conservation, difficulty_id, category_id, situation_id)
VALUES ('Fresas','Tiene muchas vitaminas', "https://static.wikia.nocookie.net/esharrypotter/images/7/76/Fresa.jpg/revision/latest/top-crop/width/360/height/450?cb=20200713112301", "Les encatan a las babosas", 5, 5, 5);
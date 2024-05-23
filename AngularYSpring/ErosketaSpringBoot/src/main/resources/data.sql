DROP SEQUENCE IF EXISTS category_seq;
create SEQUENCE category_seq START WITH 100 INCREMENT BY 1;

INSERT INTO Category (name, description, color, image)
VALUES
    ('Lacteos', 'Categoria de productos que contienen leche', '#96CFBE', 'https://thefoodtech.com/wp-content/uploads/2021/08/lacteos-828x548.jpg'),
    ('Carniceria', 'Categoria de productos provenientes de animales', '#FE0000', 'https://dialprix.es/wp/wp-content/uploads/2021/10/960x650carne-600x400.jpg?v=1.0.4'),
    ('Cuidado personal', 'Categoria de productos para el cuidado personal', '#3ECF72', 'https://media.licdn.com/dms/image/D4D12AQHxmnip6vx-sQ/article-cover_image-shrink_720_1280/0/1688544430850?e=2147483647&v=beta&t=FK3c03Vp_InDF5v3WHt6PQ4iMRX8U5adzo_Mbcc2wjA'),
    ('Panaderia', 'Categoria de productos de panaderia', '#FFD700', 'https://madridejos.es/wp-content/uploads/2021/05/PANADERIA-PEDRO.jpg'),
    ('Fruteria', 'Categoria de productos frescos naturales', '#32CD32', 'https://frutasmontijo.com/wp-content/uploads/2018/10/fruterias.jpg');

DROP SEQUENCE IF EXISTS products_seq;
create SEQUENCE products_seq START WITH 100 INCREMENT BY 1;

INSERT INTO Product (name, description, price, stock, image, category_id)
VALUES
    ('Costillas de cerdo', 'Jugosas costillas de cerdo', 12.50, 5, 'https://carniceriamaribel.com/wp-content/uploads/2020/12/Costillas-frescas-de-cerdo.jpg', 2),
    ('Queso fresco', 'Producto lacteo', 2.50, 10, 'https://i.blogs.es/6f1eeb/istock-1224507827/840_560.jpg', 1),
    ('Mantequilla', 'Producto lacteo', 4.50, 0, 'https://okdiario.com/img/2019/05/29/como-elegir-una-buena-mantequilla-655x368.jpg', 1),
    ('Yogur natural', 'Producto lacteo', 1.80, 15, 'https://www.recetasnestle.com.ec/sites/default/files/styles/crop_article_banner_desktop_nes/public/2023-03/cuenco-con-yogurt-griego.jpg%20banner%20mobile.jpg?itok=TdDxttDi', 1),
    ('Manzanas Organicas', 'Manzanas frescas y organicas', 2.99, 30, 'https://static.libertyprim.com/files/familles/pomme-large.jpg?1569271834', 5),
    ('Platano de canarias', 'Platano de procedencia canaria', 8.50, 15, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202301/24/00118109600041____5__600x600.jpg', 5),
    ('Batido de Frutas Tropicales', 'Refrescante batido con frutas tropicales', 4.99, 20, 'https://www.recetascostarica.com/base/stock/Recipe/303-image/303-image_web.jpg.webp', 5),
    ('Filete de res', 'Delicioso filete de carne de res', 8.99, 8, 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/9adf1c12-90a5-4a22-8a4a-0376bab468e2/Derivates/0a1f749b-4652-41b9-ba47-267750392cf8.jpg', 2),
    ('Salchichas gourmet', 'Salchichas premium para tus parrilladas', 6.75, 10, 'https://masmit.com/148-thickbox_default/comprar-salchicha-fresca-artesanal-carniceria.jpg', 2),
    ('Crema Hidratante', 'Nutre tu piel con nuestra crema hidratante de formula avanzada. Proporciona una hidratacion intensa y deja tu piel suave y radiante.', 15.99, 20, 'https://www.dermofarma.es/media/catalog/product/cache/1/thumbnail/600x/17f82f742ffe127f42dca9de82fb58b1/c/r/crema-hidratante-cara-y-cuerpo_-340-g.---cerave.jpg', 3),
    ('Champu Revitalizante', 'Dale vida a tu cabello con nuestro champu revitalizante. Con ingredientes naturales que fortalecen y revitalizan, dejando tu cabello sedoso y brillante.', 12.50, 15, 'https://www.perfumeriasana.com/documents/10180/10526/12657_G.jpg', 3),
    ('Set de Cuidado Facial', 'Transforma tu rutina de cuidado facial con nuestro set completo. Incluye limpiador facial, tonico y crema antienvejecimiento para una piel radiante y saludable.', 29.99, 10, 'https://img.kwcdn.com/product/Fancyalgo/VirtualModelMatting/8d2dc7356042a1dbf6fa41838c15754b.jpg?imageMogr2/auto-orient%7CimageView2/2/w/800/q/70/format/webp', 3),
    ('Baguette de Trigo', 'Pan crujiente de trigo', 1.99, 0, 'https://www.ecohuertacanaria.com/wp-content/uploads/2019/12/Panes-web-trigo-04-baguette.jpg', 4),
    ('Croissants de Mantequilla', 'Clasicos croissants con deliciosa mantequilla', 2.50, 20, 'https://www.bopan.cat/1904-thickbox_default/croissant-mantequilla-60g.jpg', 4),
    ('Pastel de Chocolate', 'Esponjoso pastel de chocolate', 15.99, 8, 'https://i.blogs.es/27bdbe/tarta-chocolate-vino1/840_560.jpg', 4),
    ('Leche Desnatada', 'Leche baja en grasa', 1.99, 0, 'https://supermercado.galuresa.com/1341-large_default/leche-desnatada-clasturiana-btlla-15l.jpg', 1),
    ('Queso Gouda', 'Queso Gouda de alta calidad', 3.99, 10, 'https://www.alvarezseleccion.com/wp-content/uploads/2021/06/queso-gouda-cominos-cuna-300-gramos-aprox.jpg', 1),
    ('Yogur de Fresa', 'Yogur con sabor a fresa', 1.50, 0, 'https://okdiario.com/img/2018/04/30/yogur-fresa.jpg', 1),
    ('Mantequilla de Hierbas', 'Mantequilla con hierbas frescas', 5.99, 8, 'https://cdn2.cocinadelirante.com/sites/default/files/images/2020/03/receta-facil-de-mantequilla-conajo-y-hierbas-casera.jpg', 1),
    ('Solomillo de Ternera', 'Solomillo de ternera premium', 18.99, 5, 'https://www.carnicasteijeiro.es/540-large_default/solomillo-de-ternera.jpg', 2),
    ('Salchichón Ibérico', 'Salchichón ibérico de bellota', 9.50, 0, 'https://sujamon.com/wp-content/uploads/2017/10/salchichon-iberico-cebo-lonchas-1.jpg', 2),
    ('Chuletón de Buey', 'Chuletón de buey madurado', 24.99, 8, 'https://mordestefoods.com/images/producto/2023/06/08/279/full_chuleta-gallega-buey-mordeste.jpg', 2),
    ('Hamburguesa de Pollo', 'Hamburguesa de pollo gourmet', 6.99, 12, 'https://okdiario.com/img/2016/01/19/hamburguesa-de-pollo-crujiente-con-salsa-ligera-de-ajo.jpg', 2),
    ('Crema Facial Antiarrugas', 'Crema facial para reducir arrugas', 19.99, 0, 'https://i.makeup.es/m/md/mddl9zgcdcmw.jpg', 3),
    ('Desodorante en Spray', 'Desodorante en spray de larga duración', 3.50, 0, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202306/08/00155754803678____30__600x600.jpg', 3),
    ('Set de Maquillaje', 'Set completo de maquillaje profesional', 29.99, 10, 'https://m.media-amazon.com/images/I/71wVsJbthbS._AC_UF894,1000_QL80_.jpg', 3),
    ('Acondicionador Reparador', 'Acondicionador para reparar el cabello', 8.99, 15, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/hbs/hbs00018/y/10.jpg', 3),
    ('Rosquillas de Canela', 'Rosquillas esponjosas con sabor a canela', 2.25, 18, 'https://img-global.cpcdn.com/recipes/d6565d799a99bb9e/680x482cq70/rosquillas-de-canela-foto-principal.jpg', 4),
    ('Trenza de Chocolate', 'Trenza de pan rellena de chocolate', 4.99, 0, 'https://okdiario.com/img/2018/03/14/trenza-chocolate.jpg', 4),
    ('Muffins de Arándanos', 'Muffins esponjosos con arándanos', 3.75, 20, 'https://s1.eestatic.com/2015/03/31/cocinillas/cocinillas_22257800_115759934_1000x703.jpg', 4),
    ('Pan Integral', 'Pan integral recién horneado', 2.49, 0, 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/D5666EAA-F144-4205-B5D2-AE84080AA898/Derivates/18FA3AC2-CE9E-4F97-8D75-F3B67D43C599.jpg', 4),
    ('Piña Fresca', 'Piña tropical fresca y jugosa', 2.99, 10, 'https://autoserviciomari.es/3070-large_default/pina-fresca-1-kilo.jpg', 5),
    ('Uvas Rojas', 'Uvas rojas frescas y deliciosas', 4.50, 0, 'https://img.freepik.com/fotos-premium/foto-uvas-rojas-semillas_512675-10.jpg', 5),
    ('Sandía', 'Sandía jugosa y refrescante', 7.99, 8, 'https://frutasolivar.com/wp-content/uploads/2020/07/31606320_s-1.jpg.webp', 5),
    ('Mango Maduro', 'Mango maduro y dulce', 1.99, 0, 'https://exoticfruitbox.com/wp-content/uploads/2015/10/mango.jpg', 5);


INSERT INTO user_ (firstname, lastname, biography, city, postal_code, country, phone, email, password, role)
VALUES
    ('David', 'Tejerina', 'Explorador apasionado, David busca aventuras en cada rincón del mundo', 'Getafe', 28901, 'España', 630172635, 'a', '$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPO', 'USER'),
    ('Juan', 'Gomez', 'Innovador y visionario, Juan transforma desafíos en oportunidades con creatividad', 'Getafe', 28901, 'España', 620182334, 'b', '$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPO', 'USER'),
    ('Administrador', 'Administrador', 'Administrador, Administrador', 'Administrador', null, 'Administrador', null, 'admin', '$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPO', 'ADMIN');



INSERT INTO Cart (user_id, product_id, amount)
VALUES
    (1, 2, 3),
    (1, 23, 6),
    (1, 12, 4),
    (2, 8, 7),
    (2, 30, 4),
    (2, 19, 9),
    (2, 24, 2);


INSERT INTO Wish_list (user_id, product_id)
VALUES
    (1, 2),
    (1, 23),
    (1, 12),
    (2, 8),
    (2, 30),
    (2, 19),
    (2, 24);



-- INSERT INTO Detail (order_id, product_id, amount)
-- VALUES
--     (1, 2, 3), -- Orden 1 incluye 3 quesos frescos
--     (2, 5, 2); -- Orden 2 incluye 2 manzanas orgánicas



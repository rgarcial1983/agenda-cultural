-- Insertar categorías
INSERT INTO categoria (nombre) VALUES
('Concierto'),
('Taller'),
('Infantil'),
('Gratuito'),
('Al aire libre'),
('Exposición'),
('Teatro'),
('Gastronomía'),
('Visita Guiada'),
('Festival');

-- Insertar ciudad de Úbeda
INSERT INTO ciudad (nombre) VALUES ('Úbeda');

-- Insertar localizaciones emblemáticas de Úbeda
INSERT INTO localizacion (lugar, enlace_google_maps) VALUES
('Plaza Vázquez de Molina', 'https://goo.gl/maps/8XJZQZQZQZQZQZQZQ'),
('Palacio de las Cadenas', 'https://goo.gl/maps/9XJZQZQZQZQZQZQZQ'),
('Sacra Capilla del Salvador', 'https://goo.gl/maps/7XJZQZQZQZQZQZQZQ'),
('Hospital de Santiago', 'https://goo.gl/maps/6XJZQZQZQZQZQZQZQ'),
('Palacio del Deán Ortega', 'https://goo.gl/maps/5XJZQZQZQZQZQZQZQ'),
('Iglesia de San Pablo', 'https://goo.gl/maps/4XJZQZQZQZQZQZQZQ'),
('Palacio Vázquez de Molina', 'https://goo.gl/maps/3XJZQZQZZQZQZQZQZ'),
('Casa de las Torres', 'https://goo.gl/maps/2XJZQZQZQZQZQZQZQ'),
('Palacio del Marqués de Mancera', 'https://goo.gl/maps/1XJZQZQZQZQZQZQZQ'),
('Sinagoga del Agua', 'https://goo.gl/maps/0XJZQZQZQZQZQZQZQ');

-- Insertar eventos
INSERT INTO evento (titulo, descripcion, fecha, hora, imagenurl, localizacion_id, ciudad_id) VALUES
('Concierto de Música Renacentista', 'Concierto especial de música renacentista en la Sacra Capilla del Salvador', '2024-06-15','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 3, 1),
('Visita Guiada Nocturna', 'Recorrido nocturno por los palacios renacentistas de Úbeda', '2025-06-20','18:30','https://www.willysplan.com/wp-content/uploads/2022/11/dog-tour-poble-sec-300x208.webp', 1, 1),
('Taller de Cerámica', 'Taller práctico de cerámica tradicional ubetense', '2025-06-25','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 8, 1),
('Festival Gastronómico', 'Muestra de la gastronomía tradicional de Úbeda', '2024-07-01','20:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 4, 1),
('Concierto de Órgano', 'Concierto de órgano en la Iglesia de San Pablo', '2024-07-05','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 6, 1),
('Exposición de Arte', 'Exposición de arte contemporáneo en el Palacio Vázquez de Molina', '2024-07-10','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 7, 1),
('Teatro Clásico', 'Representación de teatro clásico en el Hospital de Santiago', '2026-07-15','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 4, 1),
('Visita a la Sinagoga', 'Visita guiada a la Sinagoga del Agua', '2026-07-20','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 10, 1),
('Concierto de Música Sefardí', 'Concierto de música sefardí en el Palacio de las Cadenas', '2026-07-25','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 2, 1),
('Festival de Música Antigua', 'Festival de música antigua en el Palacio del Deán Ortega', '2024-07-30','15:30','https://www.willysplan.com/wp-content/uploads/2020/07/rutas-por-barcelona-ciudad1-1-300x200.jpg', 5, 1);

-- Asociar categorías a eventos
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES
(1, 1), (1, 5), -- Concierto de Música Renacentista: Concierto, Al aire libre
(2, 9), (2, 5), -- Visita Guiada Nocturna: Visita Guiada, Al aire libre
(3, 2), (3, 3), -- Taller de Cerámica: Taller, Infantil
(4, 8), (4, 4), -- Festival Gastronómico: Gastronomía, Gratuito
(5, 1), (5, 4), -- Concierto de Órgano: Concierto, Gratuito
(6, 6), (6, 4), -- Exposición de Arte: Exposición, Gratuito
(7, 7), (7, 5), -- Teatro Clásico: Teatro, Al aire libre
(8, 9), (8, 4), -- Visita a la Sinagoga: Visita Guiada, Gratuito
(9, 1), (9, 5), -- Concierto de Música Sefardí: Concierto, Al aire libre
(10, 1), (10, 10); -- Festival de Música Antigua: Concierto, Festival 
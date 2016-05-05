INSERT INTO `election_call` (`id`, `description`, `name`) VALUES
(1, 'Referéndum por el modelo electoral arobado en 2016', 'Referéndum por el modelo electoral 2016'),
(2, 'Referendum por la Independencia de Asturias 2016', 'Independencia de Asturias 2016');
INSERT INTO `election` (`id`, `description`, `end_time`, `start_time`, `name`, `election_call`) VALUES
(1, 'Referendum', '2016-05-04 20:25:07', '2016-05-04 20:08:27', '¿Estás de acuerdo con el nuevo modelo electoral?', 1),
(2, 'Referendum', '2016-05-04 22:30:00', '2016-05-04 01:00:00', '¿Estás a favor de la independencia de Asturias?', 2);
INSERT INTO `region` (`id`, `name`, `election`) VALUES
(1, 'España', 1),
(2, 'Asturias', 2);

INSERT INTO `district` (`id`, `name`, `region`) VALUES
(1, 'Estado', 1),
(2, 'Principado de Asturias', 2);

INSERT INTO `candidature` (`dtype`, `id`, `name`, `vote_option`, `list_name`, `district`, `candidature_list`) VALUES
('ReferendumOption', 1, NULL, 'Sí', NULL, 1, NULL),
('ReferendumOption', 2, NULL, 'No', NULL, 1, NULL),
('ReferendumOption', 3, NULL, 'Si', NULL, 2, NULL),
('ReferendumOption', 4, NULL, 'No', NULL, 2, NULL);
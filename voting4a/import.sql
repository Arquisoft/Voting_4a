INSERT INTO `candidature` (`dtype`, `id`, `name`, `vote_option`, `list_name`, `district`, `candidature_list`) VALUES
('ReferendumOption', 1, NULL, 'Sí', NULL, 1, NULL),
('ReferendumOption', 2, NULL, 'No', NULL, 1, NULL),
('ReferendumOption', 3, NULL, 'Si', NULL, 2, NULL),
('ReferendumOption', 4, NULL, 'No', NULL, 2, NULL);
INSERT INTO `district` (`id`, `name`, `region`) VALUES
(1, 'Estado', 1),
(2, 'Principado de Asturias', 2);
INSERT INTO `election` (`id`, `description`, `end_time`, `start_time`, `name`, `election_call`) VALUES
(1, 'Referéndum por el modelo electoral 2016', '2016-05-04 20:25:07', '2016-05-04 20:08:27', '¿Estás de acuerdo con el nuevo modelo electoral?', 1),
(2, 'Referendum por la Independencia de Asturias 2016', '2016-05-04 22:30:00', '2016-05-04 01:00:00', '¿Estás a favor de la independencia de Asturias?', 2);
INSERT INTO `election_call` (`id`, `description`, `name`) VALUES
(1, 'Referéndum por el modelo electoral arobado en 2016', 'Referéndum por el modelo electoral 2016'),
(2, 'Referendum por la Independencia de Asturias 2016', 'Independencia de Asturias 2016');
INSERT INTO `region` (`id`, `name`, `election`) VALUES
(1, 'España', 1),
(2, 'Asturias', 2);

INSERT INTO `vote` (`id`, `vote_option`, `candidature`, `voting_place`) VALUES
(1, b'0', 1, 3),
(2, b'0', 4, 3),
(3, b'0', 4, 1),
(4, b'0', 2, 1),
(5, b'0', 4, 1),
(6, b'0', 1, 1),
(7, b'0', 4, 1),
(8, b'0', 2, 2),
(9, b'0', 3, 2),
(10, b'0', 1, 3),
(11, b'0', 4, 3),
(12, b'0', 1, 3),
(13, b'0', 4, 3),
(14, b'0', 1, 3),
(15, b'0', 4, 3),
(16, b'0', 2, 3),
(17, b'0', 4, 3),
(18, b'0', 1, 4),
(19, b'0', 4, 4),
(20, b'0', 1, 4),
(21, b'0', 4, 4),
(22, b'0', 2, 4),
(23, b'0', 3, 4);
INSERT INTO `voted_election` (`id`, `id_election`, `voter`) VALUES
(1, 1, 1),
(2, 1, 10),
(3, 2, 10),
(4, 2, 1),
(5, 1, 2),
(6, 2, 2),
(7, 1, 3),
(8, 2, 3),
(9, 1, 5),
(10, 2, 5),
(11, 1, 6),
(12, 2, 6),
(13, 1, 7),
(14, 2, 7),
(15, 1, 8),
(16, 2, 8),
(17, 1, 9),
(18, 2, 9),
(19, 1, 11),
(20, 2, 11),
(21, 1, 12),
(22, 2, 12),
(23, 1, 13),
(24, 2, 13);

INSERT INTO `voter` (`id`, `email`, `id_voting_place`, `name`, `nif`, `password`, `voting_place`) VALUES
(1, 'perico@servidor.com', 1, 'Perico Delgado Gutiérrez', '11111111A', 'f95c141f91e483356a3977d5289bc06e', NULL),
(2, 'juan@servidor.com', 1, 'Juan Álvarez González', '11111112B', 'd799f1fd0412a10a07900922595e9e12', NULL),
(3, 'manuel@servidor.com', 1, 'Manuel Fernández Álvarez', '11111113C', 'f1c30cf0c2dba460991a84aa39121d47', NULL),
(4, 'francisco@servidor.com', 2, 'Franciso Gutiérrez Fernández', '11111114D', 'cd16c1522da17adb52bf5baf30d67cea', NULL),
(5, 'paco@servidor.com', 2, 'Paco Domínguez Álvarez', '11111115E', '3ffbfff48ac94f035bb48d0e303dedd2', NULL),
(6, 'javier@servidor.com', 3, 'Javier Sánchez Pon', '11111116F', 'b26c3a78dbe97d238724c77a96f7f661', NULL),
(7, 'manolo@servidor.com', 3, 'Manuel Álvarez Álvarez', '11111117G', '525955c057c3e7a5a34713b25070cc48', NULL),
(8, 'alvaro@servidor.com', 3, 'Álvaro Díaz Díaz', '11111118H', 'cb981a4a66484b1e99297d8fccfa6557', NULL),
(9, 'fran@servidor.com', 3, 'Franciso Díaz Fernández', '11111119I', 'f35220098272d65aa816e3148adcd9cb', NULL),
(10, 'javi@servidor.com', 3, 'Javier Díaz Pon', '11111110J', 'd1768ddd1eca078e4aeca615fd474280', NULL),
(11, 'sandra@servidor.com', 4, 'Sandra Álvarez González', '11111120K', 'b0d54c83f4a376d45a07125572b90673', NULL),
(12, 'marta@servidor.com', 4, 'Marta Díaz Díaz', '11111121L', '8c7c4c6b57f4f06086f51a0f14ec50d8', NULL),
(13, 'maria@servidor.com', 4, 'María Díaz Díaz', '11111122M', '2965caa84e8074c6ed239fcacec8f600', NULL),
(14, 'luz@servidor.com', 4, 'Luz Díaz Díaz', '11111123N', 'b0d74905737cc393d2f05c8f922569f8', NULL),
(15, 'manuela@servidor.com', 4, 'Manuela Díaz Díaz', '11111124O', 'b86fda01790dd67a79ecf5df6fb36a9f', NULL);
INSERT INTO `voting_place` (`id`, `id_voting_place`, `name`) VALUES
(1, 1, 'Colegio Electoral 1'),
(2, 2, 'Colegio Electoral 2'),
(3, 3, 'Colegio Electoral 3'),
(4, 4, 'Colegio Electoral 4');
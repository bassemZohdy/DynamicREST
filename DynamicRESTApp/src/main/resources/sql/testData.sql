-- ---
-- Test Data
-- ---

INSERT INTO `Resource` (`id`,`name`) VALUES (1,'person');
INSERT INTO `Resource` (`id`,`name`) VALUES (2,'car');
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (1,'firstName','String',1,1);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (2,'lastName','String',1,2);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (3,'email','String',1,3);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (4,'number','Integer',1,4);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (5,'birthDate','Date',1,5);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (6,'name','String',2,1);
INSERT INTO `Field` (`id`,`name`,`type`,`resourceId`,`fieldId`) VALUES (7,'model','String',2,2);
INSERT INTO `ResourceData` (`id`,`dataId`,`resourceId`) VALUES (1,1,1);
INSERT INTO `ResourceData` (`id`,`dataId`,`resourceId`) VALUES (2,1,2);
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (1,1,1,'firstName');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (2,2,1,'lastName');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (3,3,1,'email');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (4,4,1,'4');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (5,5,1,'28/2/1982');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (6,6,2,'mycar');
INSERT INTO `FieldData` (`id`,`fieldId`,`resourceDataId`,`value`) VALUES (7,7,2,'2010');

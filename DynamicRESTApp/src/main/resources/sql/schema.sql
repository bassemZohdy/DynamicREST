DROP TABLE IF EXISTS Resource;
DROP TABLE IF EXISTS Field;
DROP TABLE IF EXISTS ResourceData;
DROP TABLE IF EXISTS FieldData;

CREATE TABLE IF NOT EXISTS Resource  (
  id INT NOT NULL,
  name VARCHAR(20) NOT NULL ,
  PRIMARY KEY (id)
);
		
CREATE TABLE IF NOT EXISTS Field (
  id INT NOT NULL  ,
  fieldId INT NOT NULL ,
  resourceId INT NOT NULL ,
  name VARCHAR(20) NOT NULL ,
  `type` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (id)
);
		
CREATE TABLE IF NOT EXISTS ResourceData (
  id INT NOT NULL  ,
  dataId INT NOT NULL ,
  resourceId INT NOT NULL ,
  createTimeStamp TIMESTAMP NOT NULL ,
  modifyTimeStamp TIMESTAMP NULL ,
  PRIMARY KEY (id)
);
		
CREATE TABLE IF NOT EXISTS FieldData (
  id INT NOT NULL  ,
  `value` VARCHAR(20) NULL ,
  fieldId INT NOT NULL ,
  resourceDataId INT NOT NULL ,
  PRIMARY KEY (id)
);

ALTER TABLE Field ADD FOREIGN KEY (resourceId) REFERENCES Resource (id);
ALTER TABLE ResourceData ADD FOREIGN KEY (resourceId) REFERENCES Resource (id);
ALTER TABLE FieldData ADD FOREIGN KEY (fieldId) REFERENCES Field (id);
ALTER TABLE FieldData ADD FOREIGN KEY (resourceDataId) REFERENCES ResourceData (id);

ALTER TABLE ResourceData ADD UNIQUE INDEX(resourceId, dataId);
ALTER TABLE Field ADD UNIQUE INDEX(resourceId, fieldId);
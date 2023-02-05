
CREATE TABLE orders_location (
  id bigint NOT NULL AUTO_INCREMENT
        PRIMARY KEY,
  date datetime(6) DEFAULT NULL,
  latitude double DEFAULT NULL,
  longitude double DEFAULT NULL
);

CREATE TABLE orders (
  id bigint NOT NULL AUTO_INCREMENT
        PRIMARY KEY,
  operator_id bigint NOT NULL,
  start_order_location_id bigint DEFAULT NULL,
  end_order_location_id bigint DEFAULT NULL,
  CONSTRAINT FK_start_order_id
        FOREIGN KEY (start_order_location_id)
        REFERENCES orders_location (id),
  CONSTRAINT FK_end_order_id
        FOREIGN KEY (end_order_location_id)
        REFERENCES orders_location (id)
);

CREATE TABLE orders_services (
  order_id bigint NOT NULL,
  services_id bigint NOT NULL,
  CONSTRAINT FK_service_id
        FOREIGN KEY (services_id)
        REFERENCES assistances (id),
  CONSTRAINT FK_order_id
        FOREIGN KEY (order_id)
        REFERENCES orders (id)
);

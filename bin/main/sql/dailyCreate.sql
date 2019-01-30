CREATE TABLE `share_information_<DD_MM_YYYY>` (
  `id` int(11) NOT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `prev_close` double DEFAULT NULL,
  `open_price` double DEFAULT NULL,
  `high_price` double DEFAULT NULL,
  `low_price` double DEFAULT NULL,
  `last_price` double DEFAULT NULL,
  `close_price` double DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `ttl_trd_quantity` double DEFAULT NULL,
  `turnover_lacs` double DEFAULT NULL,
  `no_of_trades` double DEFAULT NULL,
  `delivery_quantity` double DEFAULT NULL,
  `delivery_percentage` double DEFAULT NULL,
  PRIMARY KEY (`id`)
)
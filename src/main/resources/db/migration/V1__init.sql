CREATE TABLE IF NOT EXISTS `inbox` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `event_id` VARCHAR(50) NOT NULL,
    `type` VARCHAR(100),
    `payload` TEXT,
    `note` VARCHAR(200),
    `is_read` TINYINT(1),
    `event_time` TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    `created_date` TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    `modified_date` TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    UNIQUE KEY `inbox` (`event_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;
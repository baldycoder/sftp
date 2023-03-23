-- sftp.sftp_detail definition

CREATE TABLE `sftp_detail` (
                               `file_name` varchar(250) NOT NULL,
                               `workdate` date NOT NULL,
                               `batch_num` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                               `status` varchar(9) NOT NULL,
                               `retry_count` varchar(3) DEFAULT NULL,
                               `file_size` varchar(3) DEFAULT NULL,
                               `local_path` varchar(250) DEFAULT NULL,
                               `remote_path` varchar(250) DEFAULT NULL,
                               `used_time` varchar(9) DEFAULT NULL,
                               `msg` varchar(500) DEFAULT NULL,
                               `create_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`workdate`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.sftp_file_param definition

CREATE TABLE `sftp_file_param` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `file_name` varchar(250) NOT NULL,
                                   `file_separator` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `data_base_type` varchar(9) NOT NULL,
                                   `table_name` varchar(70) NOT NULL,
                                   `status` varchar(9) NOT NULL,
                                   `bean_name` varchar(250) DEFAULT NULL,
                                   `field_names` varchar(2000) DEFAULT NULL,
                                   `mapper_name` varchar(250) DEFAULT NULL,
                                   `remote_url` varchar(500) DEFAULT NULL,
                                   `load_script` text,
                                   `partition_flag` varchar(9) NOT NULL,
                                   `partition_period` varchar(9) DEFAULT NULL,
                                   `partition_unit` varchar(9) DEFAULT NULL,
                                   `partition_max_value` varchar(9) DEFAULT NULL,
                                   `create_date` datetime NOT NULL,
                                   `create_user` varchar(30) NOT NULL,
                                   `upd_date` datetime NOT NULL,
                                   `upd_user` varchar(30) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sftp.user1 definition

CREATE TABLE `user1` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user11 definition

CREATE TABLE `user11` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user12 definition

CREATE TABLE `user12` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user13 definition

CREATE TABLE `user13` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user14 definition

CREATE TABLE `user14` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user15 definition

CREATE TABLE `user15` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user16 definition

CREATE TABLE `user16` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user17 definition

CREATE TABLE `user17` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user18 definition

CREATE TABLE `user18` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user19 definition

CREATE TABLE `user19` (
                          `mobile` char(11) DEFAULT NULL,
                          `passwd` varchar(50) DEFAULT NULL,
                          `name` varchar(50) DEFAULT NULL,
                          `sex` tinyint DEFAULT NULL,
                          `birthday` datetime DEFAULT NULL,
                          `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user2 definition

CREATE TABLE `user2` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user3 definition

CREATE TABLE `user3` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user4 definition

CREATE TABLE `user4` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user5 definition

CREATE TABLE `user5` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user6 definition

CREATE TABLE `user6` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user7 definition

CREATE TABLE `user7` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user8 definition

CREATE TABLE `user8` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;


-- sftp.user9 definition

CREATE TABLE `user9` (
                         `mobile` char(11) DEFAULT NULL,
                         `passwd` varchar(50) DEFAULT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `sex` tinyint DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`updated_time`))
(PARTITION p2020 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p2021 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2022 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p2023 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p2024 VALUES LESS THAN (2025) ENGINE = InnoDB) */;
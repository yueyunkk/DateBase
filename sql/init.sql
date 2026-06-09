-- =========================
-- 校园二手交易系统数据库脚本
-- 数据库：campus_second_hand
-- MySQL 8.x
-- =========================

-- 1. 创建数据库
DROP DATABASE IF EXISTS campus_second_hand;
CREATE DATABASE campus_second_hand DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE campus_second_hand;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =========================
-- 2. 用户表
-- =========================
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
    gender VARCHAR(10) DEFAULT NULL COMMENT '性别',
    phone VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    role VARCHAR(20) NOT NULL DEFAULT 'user' COMMENT '角色：admin/user',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1正常 0禁用',
    register_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'
) COMMENT='用户表';

-- =========================
-- 3. 商品分类表
-- =========================
DROP TABLE IF EXISTS category;
CREATE TABLE category (
    category_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    category_name VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    category_desc VARCHAR(255) DEFAULT NULL COMMENT '分类描述',
    sort_no INT DEFAULT 0 COMMENT '排序号',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1启用 0禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='商品分类表';

-- =========================
-- 4. 商品表
-- =========================
DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    user_id BIGINT NOT NULL COMMENT '发布者ID',
    category_id BIGINT NOT NULL COMMENT '分类ID',
    product_name VARCHAR(100) NOT NULL COMMENT '商品名称',
    product_desc TEXT COMMENT '商品描述',
    price DECIMAL(10,2) NOT NULL COMMENT '售价',
    original_price DECIMAL(10,2) DEFAULT NULL COMMENT '原价',
    quality_level VARCHAR(20) DEFAULT NULL COMMENT '成色',
    image_url VARCHAR(255) DEFAULT NULL COMMENT '商品图片',
    status VARCHAR(20) NOT NULL DEFAULT 'ON_SALE' COMMENT '状态：ON_SALE在售 OFF_SALE下架 SOLD已售',
    publish_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_product_user FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
) COMMENT='商品表';

-- =========================
-- 5. 收藏表
-- =========================
DROP TABLE IF EXISTS favorite;
CREATE TABLE favorite (
    favorite_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    favorite_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
    CONSTRAINT uk_favorite_user_product UNIQUE (user_id, product_id),
    CONSTRAINT fk_favorite_user FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    CONSTRAINT fk_favorite_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) COMMENT='收藏表';

-- =========================
-- 6. 留言表
-- =========================
DROP TABLE IF EXISTS message;
CREATE TABLE message (
    message_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '留言ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    user_id BIGINT NOT NULL COMMENT '留言用户ID',
    content VARCHAR(500) NOT NULL COMMENT '留言内容',
    message_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
    CONSTRAINT fk_message_product FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT fk_message_user FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
) COMMENT='留言表';

-- =========================
-- 7. 订单表
-- =========================
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    buyer_id BIGINT NOT NULL COMMENT '买家ID',
    seller_id BIGINT NOT NULL COMMENT '卖家ID',
    order_price DECIMAL(10,2) NOT NULL COMMENT '订单价格',
    order_status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '订单状态：PENDING待处理 COMPLETED已完成 CANCELLED已取消',
    order_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    finish_time DATETIME DEFAULT NULL COMMENT '完成时间',
    CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT fk_orders_buyer FOREIGN KEY (buyer_id) REFERENCES sys_user(user_id),
    CONSTRAINT fk_orders_seller FOREIGN KEY (seller_id) REFERENCES sys_user(user_id)
) COMMENT='订单表';

-- =========================
-- 8. 索引
-- =========================
CREATE INDEX idx_product_user_id ON product(user_id);
CREATE INDEX idx_product_category_id ON product(category_id);
CREATE INDEX idx_product_status ON product(status);

CREATE INDEX idx_favorite_user_id ON favorite(user_id);
CREATE INDEX idx_favorite_product_id ON favorite(product_id);

CREATE INDEX idx_message_product_id ON message(product_id);
CREATE INDEX idx_message_user_id ON message(user_id);

CREATE INDEX idx_orders_product_id ON orders(product_id);
CREATE INDEX idx_orders_buyer_id ON orders(buyer_id);
CREATE INDEX idx_orders_seller_id ON orders(seller_id);
CREATE INDEX idx_orders_status ON orders(order_status);

-- =========================
-- 9. 初始化用户
-- =========================
INSERT INTO sys_user (username, password, real_name, gender, phone, email, role, status, register_time) VALUES
('admin', '123456', '系统管理员', '男', '13800000000', 'admin@school.com', 'admin', 1, NOW()),
('user1', '123456', '张三', '男', '13811111111', 'user1@school.com', 'user', 1, NOW()),
('user2', '123456', '李四', '女', '13822222222', 'user2@school.com', 'user', 1, NOW()),
('user3', '123456', '王五', '男', '13833333333', 'user3@school.com', 'user', 1, NOW());

-- =========================
-- 10. 初始化商品分类
-- =========================
INSERT INTO category (category_name, category_desc, sort_no, status, create_time) VALUES
('教材书籍', '各类教材、考研资料、课外书籍', 1, 1, NOW()),
('数码电子', '手机、耳机、平板、电脑配件等', 2, 1, NOW()),
('生活用品', '台灯、收纳箱、衣架、水杯等', 3, 1, NOW()),
('服饰鞋包', '衣服、鞋子、背包、饰品等', 4, 1, NOW()),
('体育器材', '篮球、羽毛球拍、瑜伽垫等', 5, 1, NOW());

-- =========================
-- 11. 初始化商品
-- 注意：user_id 和 category_id 必须与上面插入顺序对应
-- admin=1, user1=2, user2=3, user3=4
-- 分类ID从1开始
-- =========================
INSERT INTO product (user_id, category_id, product_name, product_desc, price, original_price, quality_level, image_url, status, publish_time) VALUES
(2, 1, '高等数学同济版', '教材保存完好，只有少量笔记，适合大一新生使用。', 18.00, 56.00, '8成新', 'https://picsum.photos/400/300?random=1', 'ON_SALE', NOW()),
(2, 2, '小米蓝牙耳机', '正常使用，无维修，音质不错，附充电线。', 79.00, 199.00, '9成新', 'https://picsum.photos/400/300?random=2', 'ON_SALE', NOW()),
(3, 3, '宿舍护眼台灯', '亮度正常，适合宿舍学习使用。', 25.00, 69.00, '8成新', 'https://picsum.photos/400/300?random=3', 'ON_SALE', NOW()),
(3, 4, '双肩电脑背包', '容量大，可放15.6寸电脑，日常通勤很方便。', 45.00, 129.00, '7成新', 'https://picsum.photos/400/300?random=4', 'ON_SALE', NOW()),
(4, 5, '斯伯丁篮球', '比赛训练都可以，气足，磨损不大。', 60.00, 168.00, '8成新', 'https://picsum.photos/400/300?random=5', 'ON_SALE', NOW()),
(4, 1, '考研英语单词书', '单词齐全，便于记忆，后期冲刺好用。', 12.00, 39.80, '9成新', 'https://picsum.photos/400/300?random=6', 'ON_SALE', NOW()),
(2, 2, '机械键盘', '青轴机械键盘，办公游戏都可以，带灯光。', 99.00, 259.00, '8成新', 'https://picsum.photos/400/300?random=7', 'OFF_SALE', NOW()),
(3, 3, '保温水杯', '不漏水，保温效果良好，外观简洁。', 15.00, 49.00, '9成新', 'https://picsum.photos/400/300?random=8', 'ON_SALE', NOW()),
(4, 4, '运动鞋', '尺码42，适合跑步健身，穿着舒适。', 88.00, 299.00, '7成新', 'https://picsum.photos/400/300?random=9', 'ON_SALE', NOW()),
(2, 5, '羽毛球拍', '附带拍套，适合日常锻炼。', 35.00, 89.00, '8成新', 'https://picsum.photos/400/300?random=10', 'SOLD', NOW());

-- =========================
-- 12. 初始化收藏
-- =========================
INSERT INTO favorite (user_id, product_id, favorite_time) VALUES
(2, 3, NOW()),
(2, 4, NOW()),
(3, 1, NOW()),
(4, 2, NOW());

-- =========================
-- 13. 初始化留言
-- =========================
INSERT INTO message (product_id, user_id, content, message_time) VALUES
(1, 3, '这本书还有吗？可以小刀吗？', NOW()),
(1, 2, '还在的，可以私聊。', NOW()),
(2, 4, '耳机续航怎么样？', NOW()),
(2, 2, '正常使用一天没问题。', NOW()),
(5, 2, '篮球适合室外场地吗？', NOW()),
(5, 4, '可以的，室内室外都能用。', NOW());

-- =========================
-- 14. 初始化订单
-- product_id=10 对应已售商品
-- buyer_id=3 user2
-- seller_id=2 user1
-- =========================
INSERT INTO orders (product_id, buyer_id, seller_id, order_price, order_status, order_time, finish_time) VALUES
(10, 3, 2, 35.00, 'COMPLETED', NOW(), NOW());

SET FOREIGN_KEY_CHECKS = 1;
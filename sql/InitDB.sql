# 测试库
# DROP DATABASE IF EXISTS cf_test;
# CREATE DATABASE cf_test;
# USE cf_test;

### ------------------------------------------------------------------------------------------ ###

# 生产库
# DROP DATABASE IF EXISTS coding_first;
# CREATE DATABASE coding_first;
# USE coding_first;

### ------------------------------------------------------------------------------------------ ###

# 系统信息表
DROP TABLE IF EXISTS `t_system_info`;
CREATE TABLE `t_system_info`
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(255) NOT NULL COMMENT '属性名',
    value TEXT         NOT NULL COMMENT '属性值'
);

# 系统日志表
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    level      VARCHAR(10) DEFAULT 'default'
        COMMENT '日志等级：[default, debug, info, warning, error, fatal]',
    info       TEXT NOT NULL,
    time       DATETIME    DEFAULT NOW(),
    username   VARCHAR(30) COMMENT '产生日志的用户名',
    ip_address VARCHAR(30) COMMENT '产生日志的地址',
    INDEX (level)
);

### ------------------------------------------------------------------------------------------ ###

# FJUT荣誉榜单展示表
DROP TABLE IF EXISTS `t_border_honor_rank`;
CREATE TABLE `t_border_honor_rank`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    username_one    VARCHAR(30),
    real_name_one   VARCHAR(10) COMMENT '真实姓名',
    username_two    VARCHAR(30),
    real_name_two   VARCHAR(10),
    username_three  VARCHAR(30),
    real_name_three VARCHAR(10),
    reward_date     DATE,
    register_time   DATETIME DEFAULT NOW() COMMENT '登记该条记录的时间',
    contest_level   INT COMMENT '比赛等级',
    award_level     INT COMMENT '获奖等级',
    description     TEXT COMMENT '获奖描述'
);

### ------------------------------------------------------------------------------------------ ###

# 用户基本信息表
DROP TABLE IF EXISTS `t_user_base_info`;
CREATE TABLE `t_user_base_info`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    username        VARCHAR(30) NOT NULL UNIQUE COMMENT '16个英文字母的登录名',
    nick            VARCHAR(8)  NOT NULL COMMENT '八个汉字或者八个字符',
    gender          SMALLINT COMMENT '性别，0为保密，1为男，2为女',
    email           VARCHAR(50) NOT NULL COMMENT '邮箱',
    phone           VARCHAR(20) NOT NULL COMMENT '电话',
    motto           VARCHAR(50) COMMENT '个性签名',
    register_time   DATETIME    NOT NULL DEFAULT NOW() COMMENT '注册时间',
    rating          INT         NOT NULL DEFAULT 0 COMMENT '评分',
    ranking         INT         NOT NULL DEFAULT 0 COMMENT '排行',
    ac_num          INT         NOT NULL DEFAULT 0 COMMENT 'AC题数',
    acb             INT         NOT NULL DEFAULT 0 COMMENT 'ACB数量',
    school          VARCHAR(20) COMMENT '学校',
    faculty         VARCHAR(20) COMMENT '学院',
    major           VARCHAR(20) COMMENT '专业',
    cla             VARCHAR(20) COMMENT '班级',
    student_id      VARCHAR(20) COMMENT '学号',
    graduation_year VARCHAR(10) COMMENT '毕业年份，字符串类型'
);

# 用户登录授权表
DROP TABLE IF EXISTS `t_user_auth`;
CREATE TABLE `t_user_auth`
(
    id                       INT PRIMARY KEY AUTO_INCREMENT,
    username                 VARCHAR(30) NOT NULL UNIQUE,
    salt                     CHAR(32)    NOT NULL COMMENT '加密盐值',
    password                 CHAR(40)    NOT NULL,
    attempt_login_fail_count SMALLINT DEFAULT 0 COMMENT '尝试登录失败次数',
    locked                   SMALLINT DEFAULT 0 COMMENT '0为未锁定，1为锁定',
    unlock_time              DATETIME COMMENT '账号解锁时间',
    last_login_time          DATETIME,
    FOREIGN KEY (username) REFERENCES t_user_base_info (username)
);

# 用户个性化信息表
DROP TABLE IF EXISTS `t_user_custom_info`;
CREATE TABLE `t_user_custom_info`
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(30) NOT NULL UNIQUE,
    avatar_url   TEXT COMMENT '用户头像url地址',
    adjective_id INT COMMENT '形容词头衔ID',
    article_id   INT COMMENT '名词头衔ID',
    seal_id      INT COMMENT '印章ID',
    FOREIGN KEY (username) REFERENCES t_user_base_info (username)
);

# 全部头衔表
DROP TABLE IF EXISTS `t_user_title`;
CREATE TABLE `t_user_title`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    type        INT COMMENT '头衔类型：0：未定义；1：形容词；2：名词头衔',
    name        VARCHAR(255) NOT NULL COMMENT '头衔名称',
    picture_url TEXT COMMENT '头衔图片url，如果有',
    life_time   INT DEFAULT -1 COMMENT '头衔有效时间，单位：天'
);

# 用户头衔表
DROP TABLE IF EXISTS `t_user_title_record`;
CREATE TABLE `t_user_title_record`
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(30) NOT NULL UNIQUE,
    title_id     INT         NOT NULL COMMENT '头衔ID',
    obtain_time  DATETIME DEFAULT NOW() COMMENT '获得时间',
    expired_time DATETIME COMMENT '过期时间',
    FOREIGN KEY (username) REFERENCES t_user_base_info (username),
    FOREIGN KEY (title_id) REFERENCES t_user_title (id)
);

# 全部印章表
DROP TABLE IF EXISTS `t_user_seal`;
CREATE TABLE `t_user_seal`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    type        INT DEFAULT 0 COMMENT '目前未定义属性',
    name        VARCHAR(255) COMMENT '印章名称',
    picture_url TEXT NOT NULL COMMENT '印章图片url',
    life_time   INT DEFAULT -1 COMMENT '印章有效时间，单位：天'
);

# 用户印章表
DROP TABLE IF EXISTS `t_user_seal_record`;
CREATE TABLE `t_user_seal_record`
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(30) NOT NULL UNIQUE,
    seal_id      INT         NOT NULL COMMENT '印章ID',
    obtain_time  DATETIME DEFAULT NOW() COMMENT '获得时间',
    expired_time DATETIME COMMENT '过期时间',
    FOREIGN KEY (username) REFERENCES t_user_base_info (username),
    FOREIGN KEY (seal_id) REFERENCES t_user_seal (id)
);

DROP TABLE IF EXISTS `t_user_message`;
CREATE TABLE `t_user_message`
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    status   SMALLINT DEFAULT 0 COMMENT '0:未读，1：已读',
    title    VARCHAR(100),
    text     text        NOT NULL,
    time     DATETIME DEFAULT NOW()
);

### ------------------------------------------------------------------------------------------ ###

# 系统权限类型表
DROP TABLE IF EXISTS `t_permission_type`;
CREATE TABLE `t_permission_type`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    permission_name VARCHAR(20) NOT NULL
);

# 用户系统权限表
DROP TABLE IF EXISTS `t_user_permission`;
CREATE TABLE `t_user_permission`
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    username      VARCHAR(30) NOT NULL,
    permission_id INT         NOT NULL,
    granter       VARCHAR(30) COMMENT '授权人',
    grant_time    DATETIME DEFAULT NOW() COMMENT '授予时间',
    FOREIGN KEY (username) REFERENCES t_user_base_info (username)
);

### ------------------------------------------------------------------------------------------ ###

# 题目基础信息表
DROP TABLE IF EXISTS `t_problem_info`;
CREATE TABLE `t_problem_info`
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    problem_id        INT UNIQUE COMMENT '题目的题号',
    title             VARCHAR(80) NOT NULL COMMENT '题目标题',
    belong_oj_id      INT         NOT NULL DEFAULT 0 COMMENT '题目来源哪个OJ, 0为本地',
    belong_problem_id VARCHAR(10) COMMENT '题目在来源OJ里的ID',
    author            VARCHAR(30)          DEFAULT 'spider' COMMENT '出题人',
    total_submit      INT         NOT NULL DEFAULT 0 COMMENT '总共提交次数',
    total_ac          INT         NOT NULL DEFAULT 0 COMMENT '提交次数内总共AC次数',
    total_submit_user INT         NOT NULL DEFAULT 0 COMMENT '总共提交用户人数',
    total_ac_user     INT         NOT NULL DEFAULT 0 COMMENT '提交用户内总共AC人数',
    visible           TINYINT(1)           DEFAULT 1 COMMENT '是否普通用户可见，0： 不可见， 1：可见',
    judge_option      SMALLINT COMMENT '评测机制选择： 0：默认，1：本地评测优先；2：第三方评测优先',
    INDEX (problem_id),
    INDEX (belong_oj_id)
);

# 题目详情表
DROP TABLE IF EXISTS `t_problem_view`;
CREATE TABLE `t_problem_view`
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    problem_id   INT UNIQUE,
    time_limit   VARCHAR(30) NOT NULL COMMENT '时间限制',
    memory_limit VARCHAR(30) NOT NULL COMMENT '内存限制',
    int_format   VARCHAR(10) NOT NULL COMMENT '64位int使用的格式化字符串',
    spj          TINYINT(1)  NOT NULL COMMENT '特判标记, 0：不是，1：是',
    description  TEXT        NOT NULL COMMENT '题目描述',
    input        TEXT        NOT NULL COMMENT '输入',
    output       TEXT        NOT NULL COMMENT '输出'
);

# 题目样例表
DROP TABLE IF EXISTS `t_problem_sample`;
CREATE TABLE `t_problem_sample`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    problem_id  INT,
    case_order  INT COMMENT '样例顺序',
    input_case  TEXT NOT NULL COMMENT '输入样例',
    output_case TEXT NOT NULL COMMENT '输出样例'
);

# 题目难度表
DROP TABLE IF EXISTS `t_problem_difficult`;
CREATE TABLE `t_problem_difficult`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    problem_id      INT,
    problem_type    INT      DEFAULT 0 COMMENT '题目基本类型，0~6',
    difficult_level SMALLINT DEFAULT NULL COMMENT '难度级别 1~3 ，越高越难'
);

# 题目标签表
DROP TABLE IF EXISTS `t_problem_tag`;
CREATE TABLE `t_problem_tag`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL COMMENT '标签名称',
    tag_type    INT         DEFAULT -1 COMMENT '标签隶属类型',
    create_user VARCHAR(30) DEFAULT 'SYSTEM' COMMENT '标签创建者',
    priority    INT         DEFAULT 0 COMMENT '优先级'
);

# 题目标签记录表
DROP TABLE IF EXISTS `t_problem_tag_record`;
CREATE TABLE `t_problem_tag_record`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    problem_id INT         NOT NULL COMMENT '题目ID',
    tag_id     INT         NOT NULL,
    time       DATETIME DEFAULT NOW() COMMENT '贴标签时间',
    confidence DOUBLE   DEFAULT 0 COMMENT '标签置信度'
);

# 题目收藏表
DROP TABLE IF EXISTS `t_problem_star`;
CREATE TABLE `t_problem_star`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    problem_id INT         NOT NULL COMMENT '收藏的题目ID',
    username   VARCHAR(30) NOT NULL,
    time       DATETIME     DEFAULT NOW() COMMENT '收藏时间',
    mark       VARCHAR(255) DEFAULT '' COMMENT '备注'
);

### ------------------------------------------------------------------------------------------ ###

# 比赛基本信息表
DROP TABLE IF EXISTS `t_contest_info`;
CREATE TABLE `t_contest_info`
(
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    contest_id          INT UNIQUE COMMENT '比赛编号',
    contest_kind        INT COMMENT '比赛类型： 0：练习；1：积分；2：趣味；3：正式；4：隐藏；5：DIY',
    title               VARCHAR(100) NOT NULL COMMENT '比赛标题',
    description         TEXT         NOT NULL COMMENT '比赛介绍',
    create_user         VARCHAR(30) COMMENT '比赛开设人',
    create_time         DATETIME              DEFAULT NOW() COMMENT '比赛创建时间',
    begin_time          DATETIME     NOT NULL COMMENT '比赛开始时间',
    end_time            DATETIME     NOT NULL COMMENT '比赛结束时间',
    permission_type     INT          NOT NULL DEFAULT 0
        COMMENT '权限类型： 0：公开；1：密码；2：私有；3：注册；4：正式；5：组队',
    password            VARCHAR(16) COMMENT '需要密码时的密码',
    register_begin_time DATETIME COMMENT '需要注册参赛信息时的注册开始时间',
    register_end_time   DATETIME COMMENT '需要注册参赛信息时的注册结束时间',
    computer_rating     TINYINT(1)            DEFAULT 0 COMMENT '是否计算积分',
    rank_type           INT          NOT NULL COMMENT '计分方式',
    problem_put_tag     TINYINT(1)            DEFAULT 1
        COMMENT '题目是否可以贴标签： 0：不可以，1：可以，定义同下',
    status_read_out     TINYINT(1)            DEFAULT 1 COMMENT '是否可以查看评测列表',
    show_register_list  TINYINT(1)            DEFAULT 1 COMMENT '展示注册名单',
    show_border_list    TINYINT(1)            DEFAULT 1 COMMENT '展示比赛版单',
    show_other_status   TINYINT(1)            DEFAULT 1 COMMENT '展示别人的代码',
    INDEX (create_user)
);

# 比赛题目表
DROP TABLE IF EXISTS `t_contest_problem`;
CREATE TABLE `t_contest_problem`
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    contest_id    INT COMMENT '比赛编号',
    problem_id    INT COMMENT '题目的题号',
    problem_order INT COMMENT '题目在比赛内的顺序'
);

# 比赛注册成员表
DROP TABLE IF EXISTS `t_contest_register_user`;
CREATE TABLE `t_contest_register_user`
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    contest_id    INT         NOT NULL,
    username      VARCHAR(30) NOT NULL,
    register_time DATETIME DEFAULT NOW() COMMENT '注册时间',
    review_status SMALLINT DEFAULT 0 COMMENT '审核是否通过: 0:还未审核；1：审核通过；2：审核失败',
    review_info   VARCHAR(255) COMMENT '审核反馈信息',
    review_time   DATETIME COMMENT '审核时间',
    INDEX (contest_id),
    INDEX (username)
);

### ------------------------------------------------------------------------------------------ ###

# 评测信息表
DROP TABLE IF EXISTS `t_judge_status`;
CREATE TABLE `t_judge_status`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(30) NOT NULL,
    problem_id  INT         NOT NULL,
    contest_id  INT         NOT NULL DEFAULT -1,
    language    INT         NOT NULL COMMENT '使用语言',
    submit_time DATETIME             DEFAULT NOW() COMMENT '提交时间',
    result      INT         NOT NULL COMMENT '评测结果',
    score       INT COMMENT '如果是spj类型的题目，这里显示得分',
    time_used   VARCHAR(10) NOT NULL COMMENT '评测耗时',
    memory_used VARCHAR(10) NOT NULL COMMENT '内存消耗',
    code        TEXT        NOT NULL COMMENT '评测代码',
    code_length INT         NOT NULL COMMENT '代码长度',
    INDEX (problem_id),
    INDEX (contest_id),
    INDEX (username),
    INDEX (result),
    INDEX (language)
);

# 评测结果表
DROP TABLE IF EXISTS `t_judge_result`;
CREATE TABLE `t_judge_result`
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    judge_id INT COMMENT '评测ID' UNIQUE,
    info     MEDIUMTEXT NOT NULL COMMENT '评测返回的结果'
);

# 用户解决题目表
DROP TABLE IF EXISTS `t_user_problem_solved`;
CREATE TABLE `t_user_problem_solved`
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(30),
    problem_id        INT,
    try_count         INT DEFAULT 0 COMMENT '尝试次数',
    solved_count      INT DEFAULT 0 COMMENT '解决次数',
    last_try_time     DATETIME COMMENT '最后尝试日期',
    first_solved_time DATETIME COMMENT '第一次解锁日期',
    KEY (username),
    KEY (problem_id)
);

### ------------------------------------------------------------------------------------------ ###

# 挑战模式模块表
DROP TABLE IF EXISTS `t_challenge_block`;
CREATE TABLE `t_challenge_block`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(15) NOT NULL COMMENT '模块名称',
    block_type  INT         NOT NULL COMMENT '模块类型',
    description TEXT        NOT NULL COMMENT '模块描述'
);

# 挑战模式题目表
DROP TABLE IF EXISTS `t_challenge_block_problem`;
CREATE TABLE `t_challenge_block_problem`
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    block_id      INT NOT NULL COMMENT '挑战模式模块ID',
    problem_order INT NOT NULL COMMENT '模块内的题目顺序',
    problem_id    INT NOT NULL COMMENT '题目ID',
    score         INT NOT NULL DEFAULT 0 COMMENT '题目分值',
    reward_acb    INT NOT NULL DEFAULT 0 COMMENT '解锁后奖励ACB'
);

# 挑战模式解锁模块条件表
DROP TABLE IF EXISTS `t_challenge_block_condition`;
CREATE TABLE `t_challenge_block_condition`
(
    id                        INT PRIMARY KEY AUTO_INCREMENT,
    block_id                  INT NOT NULL COMMENT '挑战模式模块ID',
    precondition_block_id     INT NOT NULL COMMENT '前置挑战模式模块ID',
    precondition_unlock_score INT NOT NULL COMMENT '前置模块需要解锁的积分'
);

# 用户已解锁挑战模块表
DROP TABLE IF EXISTS `t_challenge_user_open_block`;
CREATE TABLE `t_challenge_user_open_block`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(30) NOT NULL,
    block_id    INT         NOT NULL,
    unlock_time DATETIME DEFAULT NOW() COMMENT '模块解锁时间'
);


### ------------------------------------------------------------------------------------------ ###

# 讨论帖子表
DROP TABLE IF EXISTS `t_discuss_post`;
CREATE TABLE `t_discuss_post`
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(50) NOT NULL,
    time     DATETIME DEFAULT NOW() COMMENT '帖子建立时间',
    author   VARCHAR(30) NOT NULL,
    priority DOUBLE   DEFAULT NULL
);

# 讨论回复表
DROP TABLE IF EXISTS `t_discuss_reply_post`;
CREATE TABLE `t_discuss_reply_post`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    discuss_id  INT         NOT NULL COMMENT '回帖帖子编号',
    reply_order INT         NOT NULL COMMENT '回帖楼层顺序',
    time        DATETIME DEFAULT NOW(),
    author      VARCHAR(30) NOT NULL,
    text        TEXT COMMENT '帖子内容'
);

### ------------------------------------------------------------------------------------------ ###

# BUG反馈表
DROP TABLE IF EXISTS `t_bug_report`;
CREATE TABLE `t_bug_report`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(30) NOT NULL,
    title       VARCHAR(255) DEFAULT NULL,
    type        INT         NOT NULL COMMENT '0：其他 1：系统漏洞 2：功能异常 3：逻辑错误 4：界面问题 ',
    text        VARCHAR(255) DEFAULT NULL,
    report_time DATETIME     DEFAULT NOW() COMMENT '反馈时间',
    is_fixed    SMALLINT     DEFAULT 0,
    INDEX (username),
    INDEX (type)
);

### ------------------------------------------------------------------------------------------ ###

# 用户签到表
DROP TABLE IF EXISTS `t_user_check_in`;
CREATE TABLE `t_user_check_in`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    check_time DATETIME    DEFAULT NOW(),
    info       VARCHAR(10) DEFAULT 'normal' COMMENT '签到状态',
    ip_address VARCHAR(20) COMMENT '签到ip地址',
    INDEX (username)
);

### ------------------------------------------------------------------------------------------ ###

# 商城商品表
DROP TABLE IF EXISTS `t_mall_goods`;
CREATE TABLE `t_mall_goods`
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(30) NOT NULL COMMENT '商品名称',
    cost             INT         NOT NULL DEFAULT 0 COMMENT '所需价格',
    goods_type       INT COMMENT '商品大类类型：0：默认；1：实体物品；2：虚拟物品',
    stock            INT         NOT NULL DEFAULT -1 COMMENT '库存数量，-1为不限量',
    description      TEXT COMMENT '商品介绍',
    picture_url      TEXT COMMENT '图片url地址',
    visible          TINYINT              DEFAULT 1 COMMENT '普通用户是否可见：0：不可见；1：可见',
    shelf_user       VARCHAR(30) COMMENT '上传用户',
    shelf_time       DATETIME             DEFAULT NOW() COMMENT '上架时间',
    buy_limit        INT COMMENT '购买限制',
    buy_verify_limit INT                  DEFAULT 0 COMMENT '购买权限限制：0：登录后不限制；'
);

# 商城购买订单表
DROP TABLE IF EXISTS `t_mall_order`;
CREATE TABLE `t_mall_order`
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    goods_id     INT         NOT NULL,
    origin_cost  INT         NOT NULL COMMENT '商品初始价格',
    real_cost    INT         NOT NULL COMMENT '实际付出价格',
    order_user   VARCHAR(30) NOT NULL COMMENT '购买用户',
    order_time   DATETIME DEFAULT NOW() COMMENT '下单时间',
    review_user  VARCHAR(30) COMMENT '审核订单用户',
    order_status SMALLINT COMMENT '订单状态: 0:无需审核；1：等待审核；2：订单失效；3：订单成交',
    order_cancel TINYINT  DEFAULT 0 COMMENT '用户是否主动取消：0：未取消订单；1：取消订单'
);

### ------------------------------------------------------------------------------------------ ###

# ACB变更账单
DROP TABLE IF EXISTS `t_acb_record`;
CREATE TABLE `t_acb_record`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    current_acb INT         NOT NULL COMMENT '记录时的ACB数量',
    reason     INT         NOT NULL DEFAULT 0 COMMENT 'ACB变更原因：0：默认；',
    time       DATETIME             DEFAULT NOW() COMMENT '记录时间',
    mark       VARCHAR(255) COMMENT '原因标记'
);

### ------------------------------------------------------------------------------------------ ###

# 积分变更账单
DROP TABLE IF EXISTS `t_rating_record`;
CREATE TABLE `t_rating_record`
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    username       VARCHAR(30) NOT NULL,
    current_rating INT         NOT NULL COMMENT '记录时的积分值',
    reason         INT         NOT NULL DEFAULT 0 COMMENT '积分变更原因：0：默认；',
    time           DATETIME             DEFAULT NOW() COMMENT '记录时间',
    mark           VARCHAR(255) COMMENT '原因标记'
);

### ------------------------------------------------------------------------------------------ ###

# 评测列表视图（过滤非法用户名提交的代码显示）
DROP VIEW IF EXISTS `v_judge_status`;
CREATE VIEW v_judge_status
AS
SELECT tjs.id,
       tubi.nick,
       tjs.username,
       tjs.problem_id,
       tjs.contest_id,
       tjs.language,
       tjs.submit_time,
       tjs.result,
       tjs.score,
       tjs.time_used,
       tjs.memory_used,
       tjs.code,
       tjs.code_length
FROM t_judge_status tjs
         JOIN t_user_base_info tubi
              ON tjs.username = tubi.username
ORDER BY tjs.id DESC


package com.lyt.comm;

public class JedisConstants {

	/**
	 * 用户选择的所有题和答案
	 */
	public static final String TSS_QUESTION_SELECTVALUE_STR = "TSS:QUESTION:SELECTVALUE:STR.";

	/**
	 * 主页信息
	 */
	public static final String TSS_HOME_INFO_HASH = "TSS:HOME:INFO:HASH.";

	/**
	 * 机构中心介绍信息
	 */
	public static final String TSS_ORG_INTRODUCE_STR = "TSS:ORG:INTRODUCE:STR.";

	/**
	 * 机构中心介绍信息（新）
	 */
	public static final String TSS_ORG_INTRODUCE_SS = "TSS:ORG:INTRODUCE:SS.";

	/**
	 * 机构中心介绍信息
	 */
	public static final String TSS_DEVICE_MAC_STR = "TSS:DEVICE:MAC:STR.";

	/**
	 * 	项目详情信息缓存（SYS:PROJECT:DETAIL:HASH.123456）
	 * @key: projectCode 项目ID
	 * @field: projectName:项目名
	 * @field: platformName:平台名
	 * @field: areaCode:地区编码
	 * @field: firstActivatedDate:开始统计时间（第一台设备的激活时间）
	 * @field: devicCodeList:已激活的设备类型（多种设备类型）
	 */
	public static final String SYS_PROJECT_DETAIL_HASH = "SYS:PROJECT:DETAIL:HASH.";
	/**
	 * 	项目列表缓存（SYS:PROJECT:SS）
	 * @score:项目添加时间
	 * @value: projectCode:项目ID
	 * @value: projectName:项目名
	 */
	public static final String SYS_PROJECT_SS = "SYS:PROJECT:SS";

	/**
	 * 硬件使用MQ
	 */
	public static final String SYS_DEVICE_USER_LOG_LIST = "SYS:DEVICE:USER:LOG:LIST";

	/**
	 * 硬件应用更新缓存
	 */
	public static final String SYS_APP_VERSION_MAC_HASH = "SYS:APP:VERSION:MAC:HASH.";

	/**
	 * 激活码为KEY，机构为value
	 */
	public static final String TSS_AUTHORIZATION_ORG_CODE_STR = "TSS:AUTHORIZATION:ORG:CODE:STR.";

	/**
	 * 问题分类缓存
	 */
	public static final String TSS_TALK_QUESTION_CLASS_STR = "TSS:TALK:QUESTION:CLASS:STR.";

	/**
	 * pro_conf字典表缓存(.classify)
	 */
	public static final String SYS_DICTIONARY_STR = "SYS:DICTIONARY:STR.";
	/**
	 * 问题缓存
	 *
	 * @key: 问题id
	 * @field: classCode 分类编码
	 * @field: channel 渠道
	 * @field: question 问题内容
	 * @field: answer 答案
	 * @field: time 时间
	 */
	public static final String TSS_TALK_QA_HASH = "TSS:TALK:QA:HASH.";

	/**
	 * 问答线路缓存
	 */
	public static final String TSS_TALK_QA_LINE_STR = "TSS:TALK:QA:LINE:STR";

	/**
	 * 问答页面标题
	 */
	public static final String TSS_TALK_QA_TITLE_STR = "TSS:TALK:QA:TITLE:STR";

	/**
	 * 区域缓存:
	 *
	 * @key: 区域code
	 * @field: parentCode
	 * @field: areaName
	 * @field: areaLevel
	 * @field: fullAreaName
	 */
	public static final String BASE_AREA_CODE_INFO_HASH = "BASE:AREA:CODE:INFO:HASH.";

	/**
	 * 区域缓存
	 *
	 * @key:区域全名
	 * @field: areaCode
	 * @field: parentCode
	 * @field: areaLevel
	 */
	public static final String BASE_AREA_NAME_INFO_HASH = "BASE:AREA:NAME:INFO:HASH.";

	/**
	 * 区域缓存
	 *
	 * @key:区域code
	 * @value: areaCode
	 */
	public static final String BASE_AREA_CODE_LOWER_LIST = "BASE:AREA:CODE:LOWER:STR.";

	/**原有的地区列表缓存key已修改，该key指向原有的缓存key*/
	public static final String BASE_AREA_CODE_LOWER_LIST_TEMP = "BASE:AREA:CODE:LOWER:LIST.";

	/**
	 * 字典表添加
	 */
	public static final String TSS_DICTIONARIES_MODER_STR = "TSS:DICTIONARIES:MODER:STR.";

	/**
	 * 工伤赔偿计算器计算公式缓存（TSS:INJURYDAMAGE:HASH.year_areaCode）
	 */
	public static final String TSS_INJURYDAMAGE_HASH = "TSS:INJURYDAMAGE:HASH.";

	/**
	 * 工伤赔偿计算器自理能力缓存（TSS:SELFCARE:HASH.year_areaCode）
	 */
	public static final String TSS_SELFCARE_HASH = "TSS:SELFCARE:HASH.";

	/**
	 * 社平工资内容缓存（TSS:SOCIALWAGES:HASH.year） filed:areaCode
	 */
	public static final String TSS_SOCIALCONTENT_HASH = "TSS:SOCIALCONTENT:HASH.";

	/**
	 * 工伤赔偿计算公式年份缓存
	 */
	public static final String TSS_INJURYDAMAGE_YEAR_STR = "TSS:INJURYDAMAGE:YEAR_STR";

	/**
	 * 一次性工亡补助金缓存 以年份为keyTSS:DEATHALLOWANCE:STR:year
	 */
	public static final String TSS_DEATHALLOWANCE_STR = "TSS:DEATHALLOWANCE:STR.";

	/**
	 * 法律法规缓存
	 *
	 * @key: 地区编码-计算器类型-伤残等级
	 * @field: legisName 法律法规名称
	 * @field: legisContent 法律依据
	 */
	public static final String TSS_LEGIS_HASH = "TSS:LEGIS:HASH.";

	/**
	 * 设备渠道缓存
	 */
	public static final String SYS_DEVICE_CHANNL_HASH = "SYS:DEVICE:CHANNL:HASH.";

	/**
	 * 设备类型缓存
	 */
	public static final String SYS_DEVICE_CHANNL_STR = "SYS:DEVICE:CHANNL:STR.";

	/**
	 * 服务亭用户信息
	 *
	 * @key: 用户身份证号
	 * @field: idNumber 身份证号
	 * @field: userName 姓名
	 * @field: sex 性别（1：男；2：女）
	 * @field: birthday 出生日期
	 * @field: nation 民族
	 * @field: faceImage 人脸识别照片
	 * @field: macId 服务亭mac地址
	 * @field: areaCode 地区编码
	 */
	public static final String SK_USER_INFO_HASH = "SK:USER:INFO:HASH.";

	/**
	 * 12348法律服务网信息
	 *
	 * @key: 地区编码
	 * @field: name 网站名称
	 * @field: logo 网站的logo图片地址
	 * @field: url 网站的首页地址
	 */
	public static final String SK_LEGALSERVICE_INFO_HASH = "SK:LEGALSERVICE:INFO:HASH.";

	/**
	 * 权限缓存
	 */
	public static final String SYS_AUTH_SS = "SYS:AUTH:SS";

	/**
	 * 角色权限信息
	 */
	public static final String SYS_ROLEAUTH_HASH = "SYS:ROLEAUTH:HASH.";

	/**
	 * 角色菜单信息
	 */
	public static final String SYS_ROLEMENU_STR = "SYS:ROLEMENU:STR.";

	/**
	 * 说明文字缓存
	 */
	public static final String TSS_EXPLAIN_STR = "TSS:EXPLAIN:STR.";

	/** Redis缓存Key_会员信息 */
    public static final String SYS_ADMIN_HASH = "SYS:ADMIN:HASH.";

	/**
	 * 登陆时根据用户名存SID，用于做登陆校验
	 */
	public static final String SYS_SID_ADMIN_STR = "SYS:SID:ADMIN:STR.";

	/**
	 * 验证码
	 */
	public static final String SYS_CODE_STR = "SYS:CODE:STR.";


	/**
	 * tmds MQ
	 */
	public static final String TBD_MQ_LAWSUIT_LIST = "TBD:MQ:LAWSUIT:LIST";

	/**
	 * 平台说明内容缓存
	 */
	public static final String TSS_PLATFORM_STR = "TSS:PLATFORM:STR.";

	/**
	 * 法律文书缓存
	 */
	public static final String TSS_LEGALWRIT_HASH = "TSS:LEGALWRIT:HASH";


    /** @Fields TBD_TSENTENCINGFORMULA_HASH : 刑期预测量刑公式缓存 */
	public static final String TBD_TSENTENCINGFORMULA_HASH = "TBD:TSENTENCINGFORMULA:HASH.";


    /** @Fields TBD_TSENTENCINGFACTOR_HASH : 刑期预测量量刑因素缓存 */
	public static final String TBD_TSENTENCINGFACTOR_HASH = "TBD:TSENTENCINGFACTOR:HASH.";


    /** @Fields TBD_TSENTENCINGFACTOR_HASH : 刑期预测量问题变量 */
    public static final String TBD_TSENTENCINGFACTOR_QUESTIONNO_STR = "TBD:TSENTENCINGFORMULA:QUESTIONNO.STR.";

    /** @Fields TBD_LAWS_SS_000000 : 全国法律法规缓存 */
    public static final String TBD_LAWS_SS_000000 = "TBD:LAWS:SS.000000_";

    /** @Fields TBD_LAWS_SS : 法律法规缓存 */
    public static final String TBD_LAWS_SS = "TBD:LAWS:SS.";

    /** @Fields TBD_CASE_SS : 相关案例列表缓存 */
    public static final String TBD_CASE_SS = "TBD:CASE:SS.";

    /** @Fields TSS_LAW_NEW_KEY_STR : new关键字有效期缓存 */
    public static final String TSS_LAW_NEW_KEY_HASH="TSS:LAW:NEW.KEY";

    /**硬件渠道id和在线渠道id关系缓存**/
    public static final String QA_CHANNEL_RELATION_STR="QA:CHANNEL_RELATION:STR.";

    /**小律问答热门地区缓存*/
    public static final String QA_HOTAREA_STR_ALL="QA:HOTAREA:STR.ALL";
    /**小律问答热门问题类型缓存*/
    public static final String  QA_INDEX_STR="QA:INDEX:STR.";
    /**小律问答问题入库开关*/
    public static final String  QA_ON_OR_OFF_STR="QA:ON_OR_OFF:STR";
    /**小律问答问题数据缓存 */
    public static final String  QA_QISSUE_RECORD_LIST="QA:QISSUE_RECORD:LIST";

    /**
     * 热门问题唯一编码自增长数值
     */
    public static final String QSS_RECOMMEND_NUM="QA:RECOMMEND:NUM";

    /**
     * 热门问题缓存
     */
    public static final String TSS_RECOMMEND_SET="TSS:RECOMMEND:SET.";

    /**
     * des3秘钥key
     */
    public static final String SK_DES3_SECRETKEY_STR = "SK:DES3:SECRETKEY:STR.";

    /**
     * 个性配置详情缓存QA:PERSONALITY:HASH:0
     */
    public static final String QA_PERSONALITY_HASH="QA:PERSONALITY:HASH.";

    /**
     * 渠道详情缓存SYS:CHANNEL_DETAIL:HASH.
     */
    public static final String SYS_CHANNEL_DETAIL_HASH="SYS:CHANNEL_DETAIL:HASH.";

    /**
     * Python关键词
     */
    public static final String TBD_KEYWORDSPAGE_SS="TBD:KEYWORDSPAGE:SS.";

    /** 刑期预测案例详情 */
    public static final String TBD_CASE_STR = "TBD:CASE:STR.";

    /** 刑期预测案例同步队列 */
    public static final String TBD_CASE_LIST_MQ = "TBD:CASE:LIST.MQ";

    /** 刑期预测犯罪事实 */
    public static final String TBD_CRIMINALFACT_HASH_CODE = "TBD:CRIMINALFACT:HASH.CODE";

    /** 刑期预测犯罪幅度 */
    public static final String TBD_CRIMINALSCALE_HASH = "TBD:CRIMINALSCALE:HASH.";

    /** 刑期预测保释缓刑 */
    public static final String TBD_PROBATION_SS = "TBD:PROBATION:SS.";

    /** 柜员机地区关联业务缓存 */
    public static final String ATM_ORG_RELATION_SS = "ATM:ORG:RELATION:SS.";
    
    /** 单位-律师团关联缓存 */
    public static final String ATM_ORG_TEAM_RELATION = "ATM:ORG:TEAM:RELATION.";
    
    /** 律师-律师团关联缓存 */
    public static final String ATM_LAWYER_TEAM_RELATION = "ATM:LAWYER:TEAM:RELATION.";
    
    /** 无团队律师推荐上限缓存 */
    public static final String ATM_NOTEAM_LAWYER_RECOMMEND_MAX_STR = "ATM:NOTEAM:LAWYER:RECOMMEND:MAX:STR";

    /** 柜员机公证用途关联公证事项缓存 */
    public static final String ATM_USE_RELATION_MATTER_SS = "ATM:USE:RELATION:MATTER:SS.";

    /** 柜员机机构列表缓存 */
    public static final String ATM_NOTARY_ORG_RELATION_STR = "ATM:NOTARY:ORG:RELATION:STR.";

    /** 柜员机公证事项/公证用途申办说明缓存 */
    public static final String ATM_NOTARY_MATTERUSE_HASH = "ATM:NOTARY:MATTERUSE:HASH.";
	/** 柜员机公证事项 禁用地区缓存 */
	public static final String ATM_NOTARY_DISABLE_HASH = "ATM:NOTARY:DISABLE:HASH.";
    /** 柜员机公证事项填写信息缓存 */
    public static final String ATM_NOTARY_MATTERINFO_HASH = "ATM:NOTARY:MATTERINFO:HASH.";

    /** 柜员机申办记录详情缓存 */
    public static final String ATM_APPLICATION_DETAIL_HASH = "ATM:APPLICATION:DETAIL:HASH.";

    /** 柜员机申办列表缓存 */
    public static final String ATM_APPLICATION_LIST_SS = "ATM:APPLICATION:LIST:SS.";

    /** 柜员机身份证和用户ID关联缓存 */
    public static final String ATM_USER_HASH = "ATM:USER:HASH.";

    /** 柜员机打印描述缓存 */
    public static final String ATM_PRINT_DESCRIPTION_STR = "ATM:PRINT:DESCRIPTION:STR";

	/**柜员机事项管理/用途详情
	 * @key ATM_NOTARY_DETIAL_HASH.type_id
	 * @param type （公证事项：0，公证用途：1）
	 * @param id 主键id
	 * @field useType 用途类型（0：国内用途，1：国外用途）公证事项无此字段
	 * @field name 事项/用途名称
	 * */
    public static final String ATM_NOTARY_DETIAL_HASH = "ATM:NOTARY:DETIAL:HASH.";

    /**机构详情 */
    public static final String ATM_ORG_DETIAL_HASH = "ATM:ORG:DETIAL:HASH.";

    /**设备详情 */
    public static final String ATM_DEVICE_DETIAL_HASH = "ATM:DEVICE:DETIAL:HASH.";

    /** 柜员机国家列表缓存 */
    public static final String ATM_COUNTRY_HASH = "ATM:COUNTRY:HASH";

    /** 柜员机语言列表缓存 */
    public static final String ATM_LANGUE_STR = "ATM:LANGUE:STR.";

    /** 柜员机申请记录code全局唯一缓存 */
    public static final String ATM_APPLICATIONRECORD_RECORDCODEKEY = "ATM:APPLICATIONRECORD:RECORDCODEKEY";

    /** 柜员机用户ID全局唯一缓存 */
    public static final String ATM_APPLICATIONRECORD_USERIDKEY = "ATM:APPLICATIONRECORD:USERIDKEY";

    /** 柜员机申请序号编码缓存 */
    public static final String ATM_APPLICATIONRECORD_SERIALNUMBER = "ATM:APPLICATIONRECORD:SERIALNUMBER";

    /** 柜员机SQL执行MQ */
    public static final String ATM_MQ_SQL = "ATM:MQ:SQL";

    /** 柜员机申办记录追加MQ */
    public static final String ATM_MQ_APPLICATION_RECODE_LIST_SQL = "ATM:MQ:APPLICATION:RECODE:LIST:SQL";

    /** 手机验证码 */
    public static final String ATM_USER_CAPTCHA_STR = "ATM:USER:CAPTCHA:STR.";

	/** 手机验证码已发送 60秒有效期 */
	public static final String ATM_USER_CAPTCHA_SENT_STR = "ATM:USER:CAPTCHA:SENT:STR.";

    /** 手机验证码标记 */
    public static final String ATM_USER_CAPTCHA_FLAG_STR = "ATM:USER:CAPTCHA:FLAG:STR.";
    /** 设备打电话 loginToken队列 */
    public static final String TSS_DEVICE_PHONE_LOGIN_TOKEN_QUEUE = "TSS:DEVICE:PHONE:LOGIN:TOKEN:QUEUE";
    /** 可以使用设备打电话 loginToken的设备类型和产品类型 */
    public static final String TSS_DEVICE_PHONE_LOGIN_PRODUCT_TYPE_HASH = "TSS:DEVICE:PHONE:LOGIN:PRODUCT:TYPE:HASH";

	/** 设备打电话 loginToken */
	public static final String TSS_DEVICE_PHONE_LOGIN_TOKEN_STR = "TSS:DEVICE:PHONE:LOGIN:TOKEN:STR.";

	/**V2.5 背景定制缓存*/
	public static final String TSS_BACKGROUND_ZSET = "TSS:BACKGROUND:ZSET.";

	/**V2.5 在线相关链接办理*/
	public static final String TSS_PROCESSARLIKE_HASH="TSS:PROCESSARLIKE:HASH.";

    /**V2.5 背景绑定硬件缓存*/
    public static final String TSS_BACKGROUND_DEVICE_SET="TSS:BACKGROUND:DEVICE:SET.";

    /**V2.5 有绑定硬件的背景标记缓存（在此缓存中有某个背景id，则说明该背景被绑定硬件了）*/
    public static final String TSS_BACKGROUND_DEVICE_BIND_SET="TSS:BACKGROUND:DEVICE:BIND.SET";

    /**V2.5 是否有智能版块缓存*/
    public static final String TSS_PLATECONFIG_STR = "TSS:PLATECONFIG:STR.";

    /**V2.5 系统主题列表缓存*/
    public static final String TSS_THEME_ZSET ="TSS:THEME:ZSET";

    /**V2.5硬件设备详情缓存
	 * @key macId
	 * @field deviceCode 设备类型
	 * @field orgCode 单位编码
	 * @field projectCode 项目编码
	 * @field authorizeStatus 授权状态 0：未授权，1：已授权
	 * */
    public static final String SYS_DEVICE_HASH ="SYS:DEVICE:HASH.";

    /**V2.5 首页主题缓存*/
    public static final String TSS_THEMEBACK_INFO_HASH ="TSS:THEMEBACK:INFO:HASH.";

    /**V2.5 超级激活码*/
    public static final String SYS_ADMIN_PASSWORD_STR ="SYS:ADMIN:PASSWORD:STR";

    /**V2.5 系统库MQ*/
    public static final String SYS_CONF_MQ_LIST = "SYS:CONF:MQ:LIST";

    /**V2.5 主题定制唯一标识*/
    public static final String TSS_THEMECUSTOM_ONLYID_STR = "TSS:THEMECUSTOM:ONLYID:STR";

    /**V2.5 机构hash缓存*
     * field:areaCode,
     * field:orgName
     * 智能终端业务平台管理增加filed
     * field:firstActivatedDate:开始统计时间（第一台设备的激活时间）
     * field:deviceCodeList:已激活的设备类型（多种设备类型）
     *
     *  */
    public static final String SYS_ORGANIZATION_HASH = "SYS:ORGANIZATION:HASH.";

    /**V2.5 地区缓存并发锁（定时器使用）*/
	public static final String SYS_AREA_LOCK_STR = "SYS:AREA:LOCK:STR.";

	/** sk用户ID全局唯一缓存 柜员机1.1*/
	public static final String SK_USER_ID_LONG = "SK:USER:ID:LONG";

	/** 服务评价列表缓存 柜员机1.1*/
	public static final String ATM_LAWYER_GUDGE_SS="ATM:LAWYER:GUDGE:SS.";

	/**柜员机通话时间设置 柜员机1.1*/
	public static  final  String SYS_SETTING_GYJ_CALLTIME_HASH="SYS:SETTING:GYJ:CALLTIME:HASH.";

	/** 用户记录导出exel发短信 是否已发送 柜员机1.1*/
	public static final String SK_USER_EXPORT_SMS_SENT_STR="SK:USER:EXPORT:SMS:SENT:STR.";

	/** 用户记录导出exel发短信code  柜员机1.1*/
	public static final String SK_USER_EXPORT_SMS_STR="SK:USER:EXPORT:SMS:STR.";

    /**
     * V3.0 常见问题缓存
     */
    public static final String QA_COMMONPROBLEM_ZSET="QA:COMMONPROBLEM:ZSET.";

    /**
     * V3.0 banner管理缓存
     */
    public static final String SYS_BANNER_HASH="SYS:BANNER:HASH.";

	/** 普法专题详情缓存  小律问答3.0*/
	public static final String QA_LAW_SUBJECT_HASH="QA:LAW:SUBJECT:HASH.";

	/** 普法专题列表缓存 QA:LAW:SUBJECT:ZSET.areaCode_classCode  小律问答3.0*/
	public static final String QA_LAW_SUBJECT_ZSET="QA:LAW:SUBJECT:ZSET.";

	/** 普法专题分类列表缓存 QA:LAW:SUBJECT:CLASS:ZSET.areaCode_classCode  小律问答3.0
	 * （classCode为空，则为一级；有classCode则为二级，且classCode为一级编码）
	 */
	public static final String QA_LAW_SUBJECT_CLASS_ZSET="QA:LAW:SUBJECT:CLASS:ZSET.";

    /*普法专题分类详情缓存   QA:LAW:SUBJECT:CLASS:STR:classCode
     * {""classCode"":""P001000000"",""className"":""一级分类"",
	*"imageName"":"" http://www.baidu.com/{width}/123.jgp""}
     */
	public static final String QA_LAW_SUBJECT_CLASS_STR="QA:LAW:SUBJECT:CLASS:STR.";

	/**
	 * 普法读本列表缓存
	 */
	public static final String QA_POPULAR_READER_ZSET="QA:POPULAR:READER:ZSET.";

     /**
      * 普法读本详情缓存
      */
	public static final String QA_READER_DETAILS_HASH="QA:READER:DETAILS:HASH.";

	/**
	 * 法治进校园分类详情缓存
	 */
	public static final String SH_RULELAW_CAMPUS_CLASS_STR="SH:RULELAW:CAMPUS:CLASS:STR.";

	/** 法律法规、效力层级、案例查询、法律文书、人员、机构分类  小律问答3.0*/
	public static final String QA_DICTIONARIES_ZSET="QA:DICTIONARIES:ZSET.";

	/** 设备分辨率缓存  小律问答3.0*/
	public static final String  SYS_DEVICE_RESOLUTION_HASH="SYS:DEVICE:RESOLUTION:HASH";

	/** 设备分辨率宽的集合缓存 小律问答3.0*/
	public static final String  SYS_DEVICE_RESOLUTION_WIDTH_SET="SYS:DEVICE:RESOLUTION:WIDTH:SET";


	/** 法治资讯已发布缓存 小律问答3.0*/
	public static final String  QA_INFORMATION_ZSET = "QA:INFORMATION:ZSET.";

	/** 法治资讯推荐缓存 小律问答3.0*/
	public static final String  QA_INFORMATION_TOP_ZSET = "QA:INFORMATION:TOP:ZSET.";

	/** 法治资讯首页缓存 小律问答3.0*/
	public static final String  QA_INFORMATION_HOME_ZSET="QA:INFORMATION:HOME:ZSET.";

	/** 法治资讯详情缓存 小律问答3.0*/
	public static final String  QA_INFORMATION_DETAILS_HASH="QA:INFORMATION:DETAILS:HASH.";

	/**小律问答用户反馈缓存 小律问答3.0*/
	public static final String  QA_USER_EVALUATION_OBJECT_LIST="QA:USER:EVALUATION:OBJECT:LIST";


	/** 事项全国下属地区禁用缓存 柜员机1.2 */
	public static final String ATM_NOTARY_DISABLE_HASH_000000 = "ATM:NOTARY:DISABLE:HASH.000000_";

	/**柜员机视频通话记录缓存 1.2*/
	public static final String ATM_USER_VIDEO_RECORD_STR="ATM:USER:VIDEO:RECORD.STR.";

	/**柜员机视频通话记录猎豹缓存 1.2*/
	public static final String ATM_MQ_VIDEO_RECORD_LIST="ATM:MQ:VIDEO:RECORD:LIST";

	/** 当cookie使用 小律问答3.0*/
	public static final String SYS_COOKIE_JEDIS_STR ="SYS:COOKIE:JEDIS:STR.";

	/**文书生成pdf MQ 小律问答3.0*/
	public static final String QA_REPORT_PDF_LIST = "QA:REPORT:PDF:LIST";

	/**壁挂机1.0 手机号和身份证绑定缓存*/
	public static final String SK_USER_IDCARD_STR = "SK:USER:IDCARD:STR.";

    /*
     *法治进校园
     */

	/** 校园机器人列表缓存 SH:RULELAW:CAMPUS:ZSET.areaCode_classCode  校园机器人*/
	public static final String SH_RULELAW_CAMPUS_ZSET="SH:RULELAW:CAMPUS:ZSET.";

	/** 校园机器人分类列表缓存 SH:RULELAW:CAMPUS:CLASS:ZSET.areaCode_classCode  校园机器人
	 * （classCode为空，则为一级；有classCode则为二级，且classCode为一级编码）
	 */
	public static final String SH_RULELAW_CAMPUS_CLASS_ZSET="SH:RULELAW:CAMPUS:CLASS:ZSET.";

    /**法治进校园详情缓存*/
    public static final String SH_RULELAW_CAMPUS_HASH = "SH:RULELAW:CAMPUS:HASH.";
    
	/** 校园机器人logo缓存 QA:BOT:BANNER:HASH.macId  校园机器人*/
	public static final String QA_BOT_BANNER_HASH="QA:BOT:BANNER:HASH.";

    /** 统计数据SQL执行MQ（智能终端业务管理平台1.0） */
    public static final String REPORT_MQ_SQL = "REPORT:MQ:SQL";

    /** 智能问答详情 缓存智能终端业务管理平台 1.0 */
    public static final String REPORT_INTELLIGENT_QUE_DETAIL_STR = "REPORT:INTELLIGENT:QUE:DETAIL:STR.";

	/** 智能问答咨询记录自增id 智能终端业务管理平台 1.0*/
	public static final String REPORT_INTELLIGENT_ID_LONG = "REPORT:INTELLIGENT:ID:LONG";

	/** 智能问答咨询记录自增id 智能终端业务管理平台 1.0*/
	public static final String REPORT_INTELLIGENT_LAW_ID_LONG = "REPORT:INTELLIGENT:LAW:ID:LONG";


    /**特色业务-法治进校园详情缓存*/
	public static final String REPORT_LAW_SCHOOL_DETAILS_HASH = "REPORT:LAW:SCHOOL:DETAIL:HASH.";

	/** ES数据Id自增值（智能终端业务管理平台1.0） */
	public static final String REPORT_ES_ID_STR = "REPORT:ES:ID:STR";

	/**ES数据Id自增值 r_notary_log（智能终端业务管理平台1.0）*/
	public static final String REPORT_ES_R_NOTARY_LOG_ID_STR="REPORT:ES:RNOTARYLOG:ID:STR";

	/**ES数据Id自增值 r_intell_access_count（智能终端业务管理平台1.0）*/
	public static final String REPORT_ES_R_INTELL_ACCESS_COUNT_ID_STR="REPORT:ES:RINTELLACCESSCOUNT:ID:STR";

	/** 便民查询详情缓存（智能终端业务管理平台1.0） */
	public static final String REPORT_PEOPLEQUERY_INFO_HASH = "REPORT:PEOPLEQUERY:INFO:HASH.";

	/** 法治宣传详情缓存（智能终端业务管理平台1.0） */
	public static final String REPORT_LAWPROMOTE_INFO_HASH = "REPORT:LAWPROMOTE:INFO:HASH.";

	/**ES数据id自增值  r_network_call网络电话 （智能终端业务管理平台1.0）*/
	public static  final String REPORT_ES_R_NETWORK_CALL_ID_STR="REPORT:ES:RNETWORKCALL:ID:STR";

	/**ES数据id自增值  r_remote_call远程视频 （智能终端业务管理平台1.0）*/
	public static final String REPORT_ES_R_REMOTE_CALL_ID_STR="REPORT:ES:RREMOTECALL:ID:STR";

	/**ES数据id自增值  r_remote_access_count 远程视频点击量 （智能终端业务管理平台1.0）*/
	public static final String REPORT_ES_R_REMOTE_ACCESS_COUNT_ID_STR="REPORT:ES:RREMOTEACCESSCOUNT:ID:STR";

	/**ES数据id自增值  r_remote_access_count 远程视频点击量 （智能终端业务管理平台1.0）*/
	public static final String REPORT_ES_R_NETWORK_ACCESS_COUNT_ID_STR="REPORT:ES:RNETWORKACCESSCOUNT:ID:STR";

	/**首页词云 （智能终端业务管理平台1.0） */
	public static final String REPORT_PORTAL_DICCLOUD_HASH="REPORT:PORTAL:DICCLOUD:HASH.";

	/**远程视频用户评价详情缓存 （智能终端业务管理平台1.0） */
	public static final String REPORT_ES_R_REMOTECALL_JUDGE_STR="REPORT:ES:RREMOTECALL:JUDGE:STR.";
	
	/**
	 * 智能终端业务管理系统 1.0
	 */
	public static final String SYS_MODULE_SS = "SYS:MODULE:SS";

	/**根据分类的二级编码，获取一级的名称缓存 （智能终端业务管理平台1.0）*/
	public static final String TSS_DICTIONARIES_BYFIRSTCODE_MODER_HASH="TSS:DICTIONARIES:BYFIRSTCODE:MODER:HASH";

	/**
	 * 政策文件列表缓存
	 */
	public static final String QA_POLICY_DOCUMENT_SS = "QA:POLICY:DOCUMENT:SS.";

	/**
	 * 政策文件详情缓存
	 */
	public static final String QA_POLICY_DOCUMENT_DETAILS_HASH = "QA:POLICY:DOCUMENT:DETAILS:HASH.";
	
	/**
	 * 漫画管理列表缓存
	 */
	public static final String QA_COMIC_SS = "QA:COMIC:SS.";

	/**
	 * 漫画管理详情缓存
	 */
	public static final String QA_COMIC_DETAILS_HASH = "QA:COMIC:DETAILS:HASH.";

    /**
     * 视频管理列表缓存
     */
    public static final String QA_VIDEO_DOCUMENT_SS = "QA:VIDEO:DOCUMENT:SS.";

    /**
     * 视频管理详情缓存
     */
    public static final String QA_VIDEO_DOCUMENT_DETAILS_HASH = "QA:VIDEO:DOCUMENT:DETAILS:HASH.";

	/**宁夏智能终端pc端动态设置渠道编码 (宁夏pc端1.0）*/
	public static final String SYS_CHANNEL_NX_STR="SYS:CHANNEL:NX:STR";

	/**license mq*/
	public static final String TSS_LICENSE_MQ_LIST="TSS:LICENSE:MQ:LIST";
	
	/**
	 * 疫情数据缓存（小律在线V3.2）
	 */
	public static final String QA_EPIDEMIC_STR = "QA:EPIDEMIC:STR.";
	
	/**
	 * 疫情数据缓存（小律在线V3.2）
	 */
	public static final String QA_EPIDEMIC_TEMP_STR = "QA:EPIDEMIC:TEMP:STR.";
	
	/**
	 * 存储疫情数据最新的截止统计时间（小律在线V3.2）
	 */
	public static final String QA_EPIDEMIC_CUTOFFTIME_STR = "QA:EPIDEMIC:CUTOFFTIME:STR.";
	
	/**
	 * 疫情数据同步锁缓存（小律在线V3.2）
	 */
	public static final String QA_EPIDEMIC_SYNCHRONLOCK_STR = "QA:EPIDEMIC:SYNCHRONLOCK:STR.";
	
    /** 项目律师推荐规则缓存（柜员机1.4） */
	public static final String ATM_PROJECT_RECOMMEND_ORDER_HASH = "ATM:PROJECT:RECOMMEND:ORDER:HASH.";
	
	/** 获取宝典律师接口最大值（柜员机1.4） */
	public static final String ATM_LAWYER_NUM = "ATM:LAWYER:NUM";

	/** 小律在线0点SQL执行MQ */
	public static final String QA_ZERO_CLOCK_MQ_SQL = "QA:ZERO:CLOCK:MQ:SQL";
	
	   
    /**
     * 疫情数据分类编码缓存（小律在线V4.0）
     */
    public static final String QA_EPIDEMIC_PNEUMONIA_CLASSCODE_HASH = "QA:EPIDEMIC:PNEUMONIA:CLASSCODE:HASH.";
}

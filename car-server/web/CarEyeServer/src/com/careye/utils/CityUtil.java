/**
* Description: car-eye车辆管理平台
* 文件名：CityUtil.java
* 版本信息：1.0
* 日期：2013-10-11
* Copyright car-eye车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.utils;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：CityUtil
 * @类描述：处理终端省份城市
 * @创建人：zr
 * @创建时间：2013-10-11 上午11:55:56
 * @修改人：zr
 * @修改时间：2013-10-11 上午11:55:56
 * @修改备注：
 * @version 1.0
 */
public class CityUtil {
    /**
     * 获取省份对应的编号
     * @param count
     * @return
     */
    public static int getProvinceId(int count) {
        switch (count) {
        case 1:
            return 1; // 北京
        case 2:
            return 2; // 天津
        case 3:
            return 3; // 河北
        case 4:
            return 4; // 山西
        case 5:
            return 5; // 内蒙
        case 6:
            return 8; // 黑龙江
        case 7:
            return 7; // 吉林
        case 8:
            return 6; // 辽宁
        case 9:
            return 15; // 山东
        case 10:
            return 9; // 上海
        case 11:
            return 10; // 江苏
        case 12:
            return 11; // 浙江
        case 13:
            return 12; // 安徽
        case 14:
            return 13; // 福建
        case 15:
            return 14; // 江西
        case 16:
            return 16; // 河南
        case 17:
            return 17; // 湖北
        case 18:
            return 18; // 湖南
        case 19:
            return 19; // 广东
        case 20:
            return 20; // 广西
        case 21:
            return 21; // 海南
        case 22:
            return 25; // 云南
        case 23:
            return 24; // 贵州
        case 24:
            return 23; // 四川
        case 25:
            return 22; // 重庆
        case 26:
            return 26; // 西藏
        case 27:
            return 27; // 陕西
        case 28:
            return 28; // 甘肃
        case 29:
            return 30; // 宁夏
        case 30:
            return 29; // 青海
        case 31:
            return 31; // 新疆
        }
        return 0;
    }

    /**
     * 获取省份对应的编号
     * @param count
     * @return
     */
    public static String getProvinceName(int count) {
        switch (count) {
        case 1:
            return "北京市";
        case 2:
            return "天津市";
        case 3:
            return "河北省";
        case 4:
            return "山西省";
        case 5:
            return "内蒙古自治区";
        case 6:
            return "黑龙江省";
        case 7:
            return "吉林省";
        case 8:
            return "辽宁省";
        case 9:
            return "山东省";
        case 10:
            return "上海市";
        case 11:
            return "江苏省";
        case 12:
            return "浙江省";
        case 13:
            return "安徽省";
        case 14:
            return "福建省";
        case 15:
            return "江西省";
        case 16:
            return "河南省";
        case 17:
            return "湖北省";
        case 18:
            return "湖南省";
        case 19:
            return "广东省";
        case 20:
            return "广西壮族自治区";
        case 21:
            return "海南省";
        case 22:
            return "云南省";
        case 23:
            return "贵州省";
        case 24:
            return "四川省";
        case 25:
            return "重庆市";
        case 26:
            return "西藏自治区";
        case 27:
            return "陕西省";
        case 28:
            return "甘肃省";
        case 29:
            return "宁夏回族自治区";
        case 30:
            return "青海省";
        case 31:
            return "新疆维吾尔自治区";
        }
        return "未知";
    }

    /**
     * 获取省份对应的拼音
     * @param count
     * @return
     */
    public static String getProvinceCodeName(int count) {
        switch (count) {
        case 1:
            return "beijing";
        case 2:
            return "tianjin";
        case 3:
            return "hebei";
        case 4:
            return "shanxi";
        case 5:
            return "neimenggu";
        case 6:
            return "heilongjiang";
        case 7:
            return "jilin";
        case 8:
            return "liaoning";
        case 9:
            return "shandong";
        case 10:
            return "shanghai";
        case 11:
            return "jiangsu";
        case 12:
            return "zhejiang";
        case 13:
            return "anhui";
        case 14:
            return "fujian";
        case 15:
            return "jiangxi";
        case 16:
            return "henan";
        case 17:
            return "hubei";
        case 18:
            return "hunan";
        case 19:
            return "guangdong";
        case 20:
            return "guangxi";
        case 21:
            return "hainan";
        case 22:
            return "yunnan";
        case 23:
            return "guizhou";
        case 24:
            return "sichuan";
        case 25:
            return "chongqing";
        case 26:
            return "xizang";
        case 27:
            return "shaanxi";
        case 28:
            return "gansu";
        case 29:
            return "ningxia";
        case 30:
            return "qinghai";
        case 31:
            return "xinjiang";
        }
        return "weizhi";
    }

    /**
     * 获取省份对应的拼音
     * @param count
     * @return
     */
    public static String getProvinceCodeNameByAddress(String address) {
        if (address.indexOf("北京市") != -1) {
            return "beijing";
        } else if (address.indexOf("天津市") != -1) {
            return "tianjin";
        } else if (address.indexOf("河北省") != -1) {
            return "hebei";
        } else if (address.indexOf("山西省") != -1) {
            return "shanxi";
        } else if (address.indexOf("内蒙古") != -1) {
            return "neimenggu";
        } else if (address.indexOf("黑龙江") != -1) {
            return "heilongjiang";
        } else if (address.indexOf("吉林省") != -1) {
            return "jilin";
        } else if (address.indexOf("辽宁省") != -1) {
            return "liaoning";
        } else if (address.indexOf("山东省") != -1) {
            return "shandong";
        } else if (address.indexOf("上海市") != -1) {
            return "shanghai";
        } else if (address.indexOf("江苏省") != -1) {
            return "jiangsu";
        } else if (address.indexOf("浙江省") != -1) {
            return "zhejiang";
        } else if (address.indexOf("安徽省") != -1) {
            return "anhui";
        } else if (address.indexOf("福建省") != -1) {
            return "fujian";
        } else if (address.indexOf("江西省") != -1) {
            return "jiangxi";
        } else if (address.indexOf("河南省") != -1) {
            return "henan";
        } else if (address.indexOf("湖北省") != -1) {
            return "hubei";
        } else if (address.indexOf("湖南省") != -1) {
            return "hunan";
        } else if (address.indexOf("广东省") != -1) {
            return "guangdong";
        } else if (address.indexOf("广西") != -1) {
            return "guangxi";
        } else if (address.indexOf("海南省") != -1) {
            return "hainan";
        } else if (address.indexOf("云南省") != -1) {
            return "yunnan";
        } else if (address.indexOf("贵州省") != -1) {
            return "guizhou";
        } else if (address.indexOf("四川省") != -1) {
            return "sichuan";
        } else if (address.indexOf("重庆") != -1) {
            return "chongqing";
        } else if (address.indexOf("西藏") != -1) {
            return "xizang";
        } else if (address.indexOf("陕西省") != -1) {
            return "shaanxi";
        } else if (address.indexOf("甘肃省") != -1) {
            return "gansu";
        } else if (address.indexOf("宁夏") != -1) {
            return "ningxia";
        } else if (address.indexOf("青海") != -1) {
            return "qinghai";
        } else if (address.indexOf("新疆") != -1) {
            return "xinjiang";
        } else {
            return "weizhi";
        }
    }

    public static int getCityId(int provinceId, int cityId) {
        switch (provinceId) {
        case 1:
            return getBeijing();
        case 2:
            return getTianjing();
        case 3:
            return getHeBei(cityId);
        case 4:
            return getShanXi(cityId);
        case 5:
            return getNeiMengGu(cityId);
        case 6:
            return getLiaoNing(cityId);
        case 7:
            return getJiLin(cityId);
        case 8:
            return getHeiLongJiang(cityId);
        case 9:
            return getShangHai();
        case 10:
            return getJiangSu(cityId);
        case 11:
            return getZheJiang(cityId);
        case 12:
            return getAnhui(cityId);
        case 13:
            return getFujian(cityId);
        case 14:
            return getJiangXi(cityId);
        case 15:
            return getShanDong(cityId);
        case 16:
            return getHeNan(cityId);
        case 17:
            return getHuBei(cityId);
        case 18:
            return getHuNan(cityId);
        case 19:
            return getGuangdong(cityId);
        case 20:
            return getGuangXi(cityId);
        case 21:
            return getHaiNan(cityId);
        case 22:
            return getChongQing();
        case 23:
            return getSiChuan(cityId);
        case 24:
            return getGuiZhou(cityId);
        case 25:
            return getYunNan(cityId);
        case 26:
            return getXiZang(cityId);
        case 27:
            return getShangXi(cityId);
        case 28:
            return getGanSu(cityId);
        case 29:
            return getQingHai(cityId);
        case 30:
            return getNingXia(cityId);
        case 31:
            return getXinJiang(cityId);
        }
        return 0;
    }

    public static String getCityName(int provinceId, int cityId) {
        switch (provinceId) {
        case 1:
            return getBeijingName();
        case 2:
            return getTianjingName();
        case 3:
            return getHeBeiCityName(cityId);
        case 4:
            return getShanXiCityName(cityId);
        case 5:
            return getNeiMengGuCityName(cityId);
        case 6:
            return getLiaoNingCityName(cityId);
        case 7:
            return getJiLinCityName(cityId);
        case 8:
            return getHeiLongJiangCityName(cityId);
        case 9:
            return getShangHaiName();
        case 10:
            return getJiangSuCityName(cityId);
        case 11:
            return getZheJiangCityName(cityId);
        case 12:
            return getAnhuiCityName(cityId);
        case 13:
            return getFujianCityName(cityId);
        case 14:
            return getJiangXiCityName(cityId);
        case 15:
            return getShanDongCityName(cityId);
        case 16:
            return getHeNanCityName(cityId);
        case 17:
            return getHuBeiCityName(cityId);
        case 18:
            return getHuNanCityName(cityId);
        case 19:
            return getGuangdongCityName(cityId);
        case 20:
            return getGuangXiCityName(cityId);
        case 21:
            return getHaiNanCityName(cityId);
        case 22:
            return getChongQingName();
        case 23:
            return getSiChuanCityName(cityId);
        case 24:
            return getGuiZhouCityName(cityId);
        case 25:
            return getYunNanCityName(cityId);
        case 26:
            return getXiZangCityName(cityId);
        case 27:
            return getShangXiCityName(cityId);
        case 28:
            return getGanSuCityName(cityId);
        case 29:
            return getQingHaiCityName(cityId);
        case 30:
            return getNingXiaCityName(cityId);
        case 31:
            return getXinJiangCityName(cityId);
        }
        return "未知";
    }

    private static String getChongQingName() {
        return "重庆市";
    }

    public static int getBeijing() {
        return 32;
    }

    public static String getBeijingName() {
        return "北京市";
    }

    public static int getTianjing() {
        return 34;
    }

    public static String getTianjingName() {
        return "天津市";
    }

    public static int getChongQing() {
        return 270;
    }

    public static int getShangHai() {
        return 107;
    }

    public static String getShangHaiName() {
        return "上海市";
    }

    public static int getHeBei(int cityId) {
        switch (cityId) {
        case 1:
            return 36;
        case 2:
            return 37;
        case 3:
            return 38;
        case 4:
            return 39;
        case 5:
            return 40;
        case 6:
            return 41;
        case 7:
            return 42;
        case 8:
            return 43;
        case 9:
            return 44;
        case 10:
            return 45;
        case 11:
            return 46;
        }

        return 0;
    }

    public static String getHeBeiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "石家庄市";
        case 2:
            return "唐山市";
        case 3:
            return "秦皇岛市";
        case 4:
            return "邯郸市";
        case 5:
            return "邢台市";
        case 6:
            return "保定市";
        case 7:
            return "张家口市";
        case 8:
            return "承德市";
        case 9:
            return "沧州市";
        case 10:
            return "廊坊市";
        case 11:
            return "衡水市";
        }
        return "未知";
    }

    public static int getAnhui(int cityId) {
        switch (cityId) {
        case 1:
            return 133;
        case 2:
            return 136;
        case 3:
            return 138;
        case 4:
            return 134;
        case 5:
            return 139;
        case 6:
            return 135;
        case 7:
            return 137;
        case 8:
            return 140;
        case 9:
            return 141;
        case 10:
            return 142;
        case 11:
            return 142;
        case 12:
            return 144;
        case 13:
            return 0; // 巢湖没有
        case 14:
            return 148;
        case 15:
            return 147;
        case 16:
            return 145;
        case 17:
            return 146;
        }

        return 0;
    }

    public static String getAnhuiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "合肥市";
        case 2:
            return "淮南市";
        case 3:
            return "淮北市";
        case 4:
            return "芜湖市";
        case 5:
            return "铜陵市";
        case 6:
            return "蚌埠市";
        case 7:
            return "马鞍山市";
        case 8:
            return "安庆市";
        case 9:
            return "黄山市";
        case 10:
            return "滁州市";
        case 11:
            return "阜阳市";
        case 12:
            return "宿州市";
        case 13:
            return "巢湖市"; // 巢湖没有
        case 14:
            return "宣城市";
        case 15:
            return "池州市";
        case 16:
            return "六安市";
        case 17:
            return "亳州市";
        }

        return "未知";
    }

    public static int getFujian(int cityId) {
        switch (cityId) {
        case 1:
            return 149;
        case 2:
            return 150;
        case 3:
            return 154;
        case 4:
            return 151;
        case 5:
            return 152;
        case 6:
            return 156;
        case 7:
            return 157;
        case 8:
            return 155;
        case 9:
            return 153;
        }
        return 0;
    }

    public static String getFujianCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "福州市";
        case 2:
            return "厦门市";
        case 3:
            return "漳州市";
        case 4:
            return "莆田市";
        case 5:
            return "三明市";
        case 6:
            return "龙岩市";
        case 7:
            return "宁德市";
        case 8:
            return "南平市";
        case 9:
            return "泉州市";
        }

        return "未知";
    }

    public static int getGuangdong(int cityId) {
        switch (cityId) {
        case 1:
            return 232;
        case 2:
            return 234;
        case 3:
            return 235;
        case 4:
            return 236;
        case 5:
            return 233;
        case 6:
            return 245;
        case 7:
            return 242;
        case 8:
            return 248;
        case 9:
            return 238;
        case 10:
            return 249;
        case 11:
            return 237;
        case 12:
            return 246;
        case 13:
            return 239;
        case 14:
            return 240;
        case 15:
            return 247;
        case 16:
            return 241;
        case 17:
            return 243;
        case 18:
            return 244;
        case 19:
            return 250;
        case 20:
            return 251;
        case 21:
            return 252;
        }

        return 0;
    }

    public static String getGuangdongCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "广州市";
        case 2:
            return "深圳市";
        case 3:
            return "珠海市";
        case 4:
            return "汕头市";
        case 5:
            return "韶关市";
        case 6:
            return "河源市";
        case 7:
            return "惠州市";
        case 8:
            return "东莞市";
        case 9:
            return "江门市";
        case 10:
            return "中山市";
        case 11:
            return "佛山市";
        case 12:
            return "阳江市";
        case 13:
            return "湛江市";
        case 14:
            return "茂名市";
        case 15:
            return "清远市";
        case 16:
            return "肇庆市";
        case 17:
            return "梅州市";
        case 18:
            return "汕尾市";
        case 19:
            return "潮州市";
        case 20:
            return "揭阳市";
        case 21:
            return "云浮市";
        }

        return "未知";
    }

    public static int getGanSu(int cityId) {
        switch (cityId) {
        case 1:
            return 335;
        case 2:
            return 337;
        case 3:
            return 338;
        case 4:
            return 339;
        case 5:
            return 336;
        case 6:
            return 342;
        case 7:
            return 344;
        case 8:
            return 340;
        case 9:
            return 341;
        case 10:
            return 343;
        case 11:
            return 345;
        case 12:
            return 346;
        case 13:
            return 348;
        case 14:
            return 347;
        }

        return 0;
    }

    public static String getGanSuCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "兰州市";
        case 2:
            return "金昌市";
        case 3:
            return "白银市";
        case 4:
            return "天水市";
        case 5:
            return "嘉峪关市";
        case 6:
            return "平凉市";
        case 7:
            return "庆阳市";
        case 8:
            return "武威市";
        case 9:
            return "张掖市";
        case 10:
            return "酒泉市";
        case 11:
            return "定西市";
        case 12:
            return "陇南市";
        case 13:
            return "甘南藏族自治州";
        case 14:
            return "临夏回族自治州";
        }

        return "未知";
    }

    public static int getGuangXi(int cityId) {
        switch (cityId) {
        case 1:
            return 253;
        case 2:
            return 254;
        case 3:
            return 255;
        case 4:
            return 256;
        case 5:
            return 257;
        case 6:
            return 258;
        case 7:
            return 259;
        case 8:
            return 260;
        case 9:
            return 261;
        case 10:
            return 262;
        case 11:
            return 263;
        case 12:
            return 264;
        case 13:
            return 265;
        case 14:
            return 266;
        }

        return 0;
    }

    public static String getGuangXiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "南宁市";
        case 2:
            return "柳州市";
        case 3:
            return "桂林市";
        case 4:
            return "梧州市";
        case 5:
            return "北海市";
        case 6:
            return "防城港市";
        case 7:
            return "钦州市";
        case 8:
            return "贵港市";
        case 9:
            return "玉林市";
        case 10:
            return "百色市";
        case 11:
            return "贺州市";
        case 12:
            return "河池市";
        case 13:
            return "来宾市";
        case 14:
            return "崇左市";
        }

        return "未知";
    }

    public static int getGuiZhou(int cityId) {
        switch (cityId) {
        case 1:
            return 293;
        case 2:
            return 294;
        case 3:
            return 295;
        case 4:
            return 296;
        case 5:
            return 298;
        case 6:
            return 299;
        case 7:
            return 297;
        case 8:
            return 300;
        case 9:
            return 301;
        }

        return 0;
    }

    public static String getGuiZhouCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "贵阳市";
        case 2:
            return "六盘水市";
        case 3:
            return "遵义市";
        case 4:
            return "安顺市";
        case 5:
            return "铜仁市";
        case 6:
            return "黔西南布依族苗族自治州";
        case 7:
            return "毕节市";
        case 8:
            return "黔东南苗族侗族自治州";
        case 9:
            return "黔南布依族苗族自治州";
        }

        return "";
    }

    public static int getHaiNan(int cityId) {
        switch (cityId) {
        case 1:
            return 267;
        case 2:
            return 268;
        }

        return 0;
    }

    public static String getHaiNanCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "海口市";
        case 2:
            return "三亚市";
        case 3:
            return "五指山市";
        case 4:
            return "琼海市";
        case 5:
            return "儋州市";
        case 6:
            return "文昌市";
        case 7:
            return "万宁市";
        case 8:
            return "东方市";
        case 9:
            return "定安县";
        case 10:
            return "屯昌县";
        case 11:
            return "澄迈县";
        case 12:
            return "临高县";
        case 13:
            return "白沙黎族自治县";
        case 14:
            return "昌江黎族自治县";
        case 15:
            return "乐东黎族自治县";
        case 16:
            return "陵水黎族自治县";
        case 17:
            return "保亭黎族苗族自治县";
        case 18:
            return "琼中黎族苗族自治县";
        }
        return "未知";
    }

    public static int getHeNan(int cityId) {
        switch (cityId) {
        case 1:
            return 186;
        case 2:
            return 187;
        case 3:
            return 188;
        case 4:
            return 189;
        case 5:
            return 191;
        case 6:
            return 192;
        case 7:
            return 190;
        case 8:
            return 194;
        case 9:
            return 196;
        case 10:
            return 195;
        case 11:
            return 197;
        case 12:
            return 199;
        case 13:
            return 198;
        case 14:
            return 200;
        case 15:
            return 202;
        case 16:
            return 201;
        case 17:
            return 193;
        case 18:
            return 0;
        }

        return 0;
    }

    public static String getHeNanCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "郑州市";
        case 2:
            return "开封市";
        case 3:
            return "洛阳市";
        case 4:
            return "平顶山市";
        case 5:
            return "鹤壁市";
        case 6:
            return "新乡市";
        case 7:
            return "安阳市";
        case 8:
            return "濮阳市";
        case 9:
            return "漯河市";
        case 10:
            return "许昌市";
        case 11:
            return "三门峡市";
        case 12:
            return "商丘市";
        case 13:
            return "南阳市";
        case 14:
            return "信阳市";
        case 15:
            return "驻马店市";
        case 16:
            return "周口市";
        case 17:    
            return "焦作市";
        case 18:    
            return "济源市";    
        }

        return "未知";
    }

    public static int getHeiLongJiang(int cityId) {
        switch (cityId) {
        case 1:
            return 94;
        case 2:
            return 95;
        case 3:
            return 97;
        case 4:
            return 104;
        case 5:
            return 99;
        case 6:
            return 100;
        case 7:
            return 101;
        case 8:
            return 98;
        case 9:
            return 102;
        case 10:
            return 96;
        case 11:
            return 103;
        case 12:
            return 106;
        case 13:
            return 105;
        }

        return 0;
    }

    public static String getHeiLongJiangCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "哈尔滨市";
        case 2:
            return "齐齐哈尔市";
        case 3:
            return "鹤岗市";
        case 4:
            return "黑河市";
        case 5:
            return "大庆市";
        case 6:
            return "伊春市";
        case 7:
            return "佳木斯市";
        case 8:
            return "双鸭山市";
        case 9:
            return "七台河市";
        case 10:
            return "鸡西市";
        case 11:
            return "牡丹江市";
        case 12:
            return "大兴安岭地区";
        case 13:
            return "绥化市";
        }

        return "未知";
    }

    public static int getHuBei(int cityId) {
        switch (cityId) {
        case 1:
            return 204;
        case 2:
            return 205;
        case 3:
            return 208;
        case 4:
            return 207;
        case 5:
            return 212;
        case 6:
            return 209;
        case 7:
            return 213;
        case 8:
            return 211;
        case 9:
            return 206;
        case 10:
            return 214;
        case 11:
            return 215;
        case 12:
            return 0;
        case 13:
            return 216;
        case 14:
            return 0;
        case 15:
            return 0;
        case 16:
            return 0;
        case 17:
            return 210;
        }

        return 0;
    }

    public static String getHuBeiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "武汉市";
        case 2:
            return "黄石市";
        case 3:
            return "襄阳市";
        case 4:
            return "宜昌市";
        case 5:
            return "荆州市";
        case 6:
            return "鄂州市";
        case 7:
            return "黄冈市";
        case 8:
            return "孝感市";
        case 9:
            return "十堰市";
        case 10:
            return "咸宁市";
        case 11:
            return "随州市";
        case 12:
            return "神农架市";
        case 13:
            return "恩施土家族苗族自治州";
        case 14:
            return "仙桃市";
        case 15:
            return "潜江市";
        case 16:
            return "天门市";
        case 17:
            return "荆门市";
        }

        return "未知";
    }

    public static int getHuNan(int cityId) {
        switch (cityId) {
        case 1:
            return 218;
        case 2:
            return 224;
        case 3:
            return 226;
        case 4:
            return 219;
        case 5:
            return 220;
        case 6:
            return 221;
        case 7:
            return 222;
        case 8:
            return 223;
        case 9:
            return 225;
        case 10:
            return 227;
        case 11:
            return 228;
        case 12:
            return 229;
        case 13:
            return 231;
        case 14:
            return 230;
        }
        return 0;
    }

    public static String getHuNanCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "长沙市";
        case 2:
            return "常德市";
        case 3:
            return "益阳市";
        case 4:
            return "株洲市";
        case 5:
            return "湘潭市";
        case 6:
            return "衡阳市";
        case 7:
            return "邵阳市";
        case 8:
            return "岳阳市";
        case 9:
            return "张家界市";
        case 10:
            return "郴州市";
        case 11:
            return "永州市";
        case 12:
            return "怀化市";
        case 13:
            return "湘西土家族苗族自治州";
        case 14:
            return "娄底市";
        }

        return "未知";
    }

    public static int getJiLin(int cityId) {
        switch (cityId) {
        case 1:
            return 85;
        case 2:
            return 86;
        case 3:
            return 87;
        case 4:
            return 88;
        case 5:
            return 89;
        case 6:
            return 90;
        case 7:
            return 91;
        case 8:
            return 92;
        case 9:
            return 93;
        }

        return 0;
    }

    public static String getJiLinCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "长春市";
        case 2:
            return "吉林市";
        case 3:
            return "四平市";
        case 4:
            return "辽源市";
        case 5:
            return "通化市";
        case 6:
            return "白山市";
        case 7:
            return "松原市";
        case 8:
            return "白城市";
        case 9:
            return "延边朝鲜族自治州";
        }

        return "未知";
    }

    public static int getJiangSu(int cityId) {
        switch (cityId) {
        case 1:
            return 109;
        case 2:
            return 112;
        case 3:
            return 110;
        case 4:
            return 113;
        case 5:
            return 111;
        case 6:
            return 114;
        case 7:
            return 115;
        case 8:
            return 116;
        case 9:
            return 117;
        case 10:
            return 118;
        case 11:
            return 119;
        case 12:
            return 120;
        case 13:
            return 121;
        }

        return 0;
    }

    public static String getJiangSuCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "南京市";
        case 2:
            return "常州市";
        case 3:
            return "无锡市";
        case 4:
            return "苏州市";
        case 5:
            return "徐州市";
        case 6:
            return "南通市";
        case 7:
            return "连云港市";
        case 8:
            return "淮安市";
        case 9:
            return "盐城市";
        case 10:
            return "扬州市";
        case 11:
            return "镇江市";
        case 12:
            return "泰州市";
        case 13:
            return "宿迁市";
        }

        return "未知";
    }

    public static int getJiangXi(int cityId) {
        switch (cityId) {
        case 1:
            return 158;
        case 2:
            return 161;
        case 3:
            return 159;
        case 4:
            return 160;
        case 5:
            return 162;
        case 6:
            return 163;
        case 7:
            return 164;
        case 8:
            return 168;
        case 9:
            return 166;
        case 10:
            return 167;
        case 11:
            return 165;
        }

        return 0;
    }

    public static String getJiangXiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "南昌市";
        case 2:
            return "九江市";
        case 3:
            return "景德镇市";
        case 4:
            return "萍乡市";
        case 5:
            return "新余市";
        case 6:
            return "鹰潭市";
        case 7:
            return "赣州市";
        case 8:
            return "上饶市";
        case 9:
            return "宜春市";
        case 10:
            return "抚州市";
        case 11:
            return "吉安市";
        }

        return "未知";
    }

    public static int getLiaoNing(int cityId) {
        switch (cityId) {
        case 1:
            return 71;
        case 2:
            return 72;
        case 3:
            return 73;
        case 4:
            return 74;
        case 5:
            return 75;
        case 6:
            return 76;
        case 7:
            return 77;
        case 8:
            return 78;
        case 9:
            return 79;
        case 10:
            return 80;
        case 11:
            return 82;
        case 12:
            return 83;
        case 13:
            return 81;
        case 14:
            return 84;
        }

        return 0;
    }

    public static String getLiaoNingCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "沈阳市";
        case 2:
            return "大连市";
        case 3:
            return "鞍山市";
        case 4:
            return "抚顺市";
        case 5:
            return "本溪市";
        case 6:
            return "丹东市";
        case 7:
            return "锦州市";
        case 8:
            return "营口市";
        case 9:
            return "阜新市";
        case 10:
            return "辽阳市";
        case 11:
            return "铁岭市";
        case 12:
            return "朝阳市";
        case 13:
            return "盘锦市";
        case 14:
            return "葫芦岛市";
        }

        return "未知";
    }

    public static int getNeiMengGu(int cityId) {
        switch (cityId) {
        case 1:
            return 59;
        case 2:
            return 60;
        case 3:
            return 61;
        case 4:
            return 62;
        case 5:
            return 63;
        case 6:
            return 64;
        case 7:
            return 65;
        case 8:
            return 66;
        case 9:
            return 67;
        case 10:
            return 68;
        case 11:
            return 69;
        case 12:
            return 70;
        }

        return 0;
    }

    public static String getNeiMengGuCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "呼和浩特市";
        case 2:
            return "包头市";
        case 3:
            return "乌海市";
        case 4:
            return "赤峰市";
        case 5:
            return "通辽市";
        case 6:
            return "鄂尔多斯市";
        case 7:
            return "呼伦贝尔市";
        case 8:
            return "巴彦淖尔市";
        case 9:
            return "乌兰察布市";
        case 10:
            return "兴安盟";
        case 11:
            return "锡林郭勒盟";
        case 12:
            return "阿拉善盟";
        }

        return "未知";
    }

    public static int getNingXia(int cityId) {
        switch (cityId) {
        case 1:
            return 357;
        case 2:
            return 358;
        case 3:
            return 359;
        case 4:
            return 360;
        case 5:
            return 361;
        }

        return 0;
    }

    public static String getNingXiaCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "银川市";
        case 2:
            return "石嘴山市";
        case 3:
            return "吴忠市";
        case 4:
            return "中卫市";
        case 5:
            return "固原市";
        }

        return "未知";
    }

    public static int getQingHai(int cityId) {
        switch (cityId) {
        case 1:
            return 349;
        case 2:
            return 350;
        case 3:
            return 351;
        case 4:
            return 352;
        case 5:
            return 353;
        case 6:
            return 354;
        case 7:
            return 355;
        case 8:
            return 356;
        }

        return 0;
    }

    public static String getQingHaiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "西宁市";
        case 2:
            return "海东地区";
        case 3:
            return "海北藏族自治州";
        case 4:
            return "黄南藏族自治州";
        case 5:
            return "海南藏族自治州";
        case 6:
            return "果洛藏族自治州";
        case 7:
            return "玉树藏族自治州";
        case 8:
            return "海西蒙古族藏族自治州";
        }

        return "未知";
    }

    public static int getSiChuan(int cityId) {
        switch (cityId) {
        case 1:
            return 272;
        case 2:
            return 273;
        case 3:
            return 274;
        case 4:
            return 275;
        case 5:
            return 276;
        case 6:
            return 277;
        case 7:
            return 290;
        case 8:
            return 278;
        case 9:
            return 279;
        case 10:
            return 280;
        case 11:
            return 281;
        case 12:
            return 282;
        case 13:
            return 283;
        case 14:
            return 292;
        case 15:
            return 284;
        case 16:
            return 285;
        case 17:
            return 286;
        case 18:
            return 287;
        case 19:
            return 288;
        case 20:
            return 289;
        case 21:
            return 291;
        }

        return 0;
    }

    public static String getSiChuanCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "成都市";
        case 2:
            return "自贡市";
        case 3:
            return "攀枝花市";
        case 4:
            return "泸州市";
        case 5:
            return "德阳市";
        case 6:
            return "绵阳市";
        case 7:
            return "阿坝藏族羌族自治州";
        case 8:
            return "广元市";
        case 9:
            return "遂宁市";
        case 10:
            return "内江市";
        case 11:
            return "乐山市";
        case 12:
            return "南充市";
        case 13:
            return "眉山市";
        case 14:
            return "凉山彝族自治州";
        case 15:
            return "宜宾市";
        case 16:
            return "广安市";
        case 17:
            return "达州市";
        case 18:
            return "雅安市";
        case 19:
            return "巴中市";
        case 20:
            return "资阳市";
        case 21:
            return "甘孜藏族自治州";

        }

        return "未知";
    }

    public static int getShanDong(int cityId) {
        switch (cityId) {
        case 1:
            return 169;
        case 2:
            return 170;
        case 3:
            return 174;
        case 4:
            return 172;
        case 5:
            return 175;
        case 6:
            return 178;
        case 7:
            return 176;
        case 8:
            return 177;
        case 9:
            return 179;
        case 10:
            return 182;
        case 11:
            return 184;
        case 12:
            return 181;
        case 13:
            return 185;
        case 14:
            return 183;
        case 15:
            return 171;
        case 16:
            return 173;
        case 17:
            return 180;
        }

        return 0;
    }

    public static String getShanDongCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "济南市";
        case 2:
            return "青岛市";
        case 3:
            return "烟台市";
        case 4:
            return "枣庄市";
        case 5:
            return "潍坊市";
        case 6:
            return "威海市";
        case 7:
            return "济宁市";
        case 8:
            return "泰安市";
        case 9:
            return "日照市";
        case 10:
            return "德州市";
        case 11:
            return "滨州市";
        case 12:
            return "临沂市";
        case 13:
            return "菏泽市";
        case 14:
            return "聊城市";
        case 15:
            return "淄博市";
        case 16:
            return "东营市";
        case 17:
            return "莱芜市";    
        }
        return "未知";
    }

    public static int getShangXi(int cityId) {
        switch (cityId) {
        case 1:
            return 325;
        case 2:
            return 327;
        case 3:
            return 328;
        case 4:
            return 326;
        case 5:
            return 330;
        case 6:
            return 332;
        case 7:
            return 329;
        case 8:
            return 334;
        case 9:
            return 331;
        case 10:
            return 333;

        }

        return 0;
    }

    public static String getShangXiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "西安市";
        case 2:
            return "宝鸡市";
        case 3:
            return "咸阳市";
        case 4:
            return "铜川市";
        case 5:
            return "延安市";
        case 6:
            return "榆林市";
        case 7:
            return "渭南市";
        case 8:
            return "商洛市";
        case 9:
            return "汉中市";
        case 10:
            return "安康市";
        }

        return "未知";
    }

    public static int getShanXi(int cityId) {
        switch (cityId) {
        case 1:
            return 48;
        case 2:
            return 49;
        case 3:
            return 50;
        case 4:
            return 51;
        case 5:
            return 52;
        case 6:
            return 53;
        case 7:
            return 56;
        case 8:
            return 54;
        case 9:
            return 58;
        case 10:
            return 57;
        case 11:
            return 55;
        }

        return 0;
    }

    public static String getShanXiCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "太原市";
        case 2:
            return "大同市";
        case 3:
            return "阳泉市";
        case 4:
            return "长治市";
        case 5:
            return "晋城市";
        case 6:
            return "朔州市";
        case 7:
            return "忻州市";
        case 8:
            return "晋中市";
        case 9:
            return "吕梁市";
        case 10:
            return "临汾市";
        case 11:
            return "运城市";

        }

        return "未知";
    }

    public static int getXinJiang(int cityId) {
        switch (cityId) {
        case 1:
            return 362;
        case 2:
            return 363;
        case 3:
            return 371;
        case 4:
            return 369;
        case 5:
            return 372;
        case 6:
            return 364;
        case 7:
            return 365;
        case 8:
            return 370;
        case 9:
            return 367;
        case 10:
            return 366;
        case 11:
            return 368;
        case 12:
            return 373;
        case 13:
            return 374;
        case 14:
            return 375;

        }

        return 0;
    }

    public static String getXinJiangCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "乌鲁木齐市";
        case 2:
            return "克拉玛依市";
        case 3:
            return "喀什地区";
        case 4:
            return "阿克苏地区";
        case 5:
            return "和田地区";
        case 6:
            return "吐鲁番地区";
        case 7:
            return "哈密地区";
        case 8:
            return "克孜勒苏柯尔克孜自治州";
        case 9:
            return "博尔塔拉蒙古自治州";
        case 10:
            return "昌吉回族自治州";
        case 11:
            return "巴音郭楞蒙古自治州";
        case 12:
            return "伊犁哈萨克自治州";
        case 13:
            return "塔城地区";
        case 14:
            return "阿勒泰地区";

        }

        return "未知";
    }

    public static int getXiZang(int cityId) {
        switch (cityId) {
        case 1:
            return 318;
        case 2:
            return 319;
        case 3:
            return 320;
        case 4:
            return 321;
        case 5:
            return 322;
        case 6:
            return 323;
        case 7:
            return 324;
        }

        return 0;
    }

    public static String getXiZangCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "拉萨市";
        case 2:
            return "昌都地区";
        case 3:
            return "山南地区";
        case 4:
            return "日喀则地区";
        case 5:
            return "那曲地区";
        case 6:
            return "阿里地区";
        case 7:
            return "林芝地区";

        }

        return "未知";
    }

    public static int getYunNan(int cityId) {
        switch (cityId) {
        case 1:
            return 302;
        case 2:
            return 303;
        case 3:
            return 304;
        case 4:
            return 305;
        case 5:
            return 306;
        case 6:
            return 307;
        case 7:
            return 308;
        case 8:
            return 309;
        case 9:
            return 310;
        case 10:
            return 311;
        case 11:
            return 312;
        case 12:
            return 313;
        case 13:
            return 314;
        case 14:
            return 315;
        case 15:
            return 316;
        case 16:
            return 317;
        }

        return 0;
    }

    public static String getYunNanCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "昆明市";
        case 2:
            return "曲靖市";
        case 3:
            return "玉溪市";
        case 4:
            return "保山市";
        case 5:
            return "昭通市";
        case 6:
            return "丽江市";
        case 7:
            return "普洱市";
        case 8:
            return "临沧市";
        case 9:
            return "楚雄彝族自治州";
        case 10:
            return "红河哈尼族彝族自治州";
        case 11:
            return "文山壮族苗族自治州";
        case 12:
            return "西双版纳傣族自治州";
        case 13:
            return "大理白族自治州";
        case 14:
            return "德宏傣族景颇族自治州";
        case 15:
            return "怒江傈僳族自治州";
        case 16:
            return "迪庆藏族自治州";

        }

        return "未知";
    }

    public static int getZheJiang(int cityId) {
        switch (cityId) {
        case 1:
            return 122;
        case 2:
            return 123;
        case 3:
            return 124;
        case 4:
            return 125;
        case 5:
            return 126;
        case 6:
            return 127;
        case 7:
            return 128;
        case 8:
            return 129;
        case 9:
            return 130;
        case 10:
            return 131;
        case 11:
            return 132;

        }

        return 0;
    }

    public static String getZheJiangCityName(int cityId) {
        switch (cityId) {
        case 1:
            return "杭州市";
        case 2:
            return "宁波市";
        case 3:
            return "温州市";
        case 4:
            return "嘉兴市";
        case 5:
            return "湖州市";
        case 6:
            return "绍兴市";
        case 7:
            return "金华市";
        case 8:
            return "衢州市";
        case 9:
            return "舟山市";
        case 10:
            return "台州市";
        case 11:
            return "丽水市";

        }
        return "未知";
    }
    
    public static void test(String [] temp){
        for(int i=0; i<2; i++){
            if(i == 0){
                temp[i] = "test";
            }
        }
    }
    
    public static void main(String args[]){
        String[] buffer = new String[]{"aa","bb"};
        //int[] ch = new int[]{0,1,2,3,4};
        CityUtil.test(buffer);
        System.out.println(buffer[0]);
    }
}

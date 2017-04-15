package com.wap.entity;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Dictionary {
    public static final Byte PC = Byte.valueOf((byte)1);
    public static final Byte ENABLE = Byte.valueOf((byte)0);
    public static final Byte DISABLE = Byte.valueOf((byte)1);
    public static final Byte TYPE_MANGER = Byte.valueOf((byte)1);
    public static final Byte TYPE_USER = Byte.valueOf((byte)2);
    public static final Long ROLE_CODE_STORE = Long.valueOf(1L);
    public static final Long ROLE_CODE_MANGER = Long.valueOf(2L);
    public static final Long ROLE_CODE_USER = Long.valueOf(3L);
    public static final Byte PAY_WAIT = Byte.valueOf((byte)0);
    public static final Byte PAY_SUCCESS = Byte.valueOf((byte)1);
    public static final Byte PAY_UNDO = Byte.valueOf((byte)2);
    public static final Byte PAY_REFUND = Byte.valueOf((byte)3);
    public static final Byte PAY_FAILD = Byte.valueOf((byte)4);
    public static final Byte PAY_PART_REFUND = Byte.valueOf((byte)5);
    public static final Byte PAY_CLOSE = Byte.valueOf((byte)6);
    public static final Byte REFUND_FAILD = Byte.valueOf((byte)0);
    public static final Byte REFUND_SUCCESS = Byte.valueOf((byte)1);
    public static final Byte REFUND_ING = Byte.valueOf((byte)2);
    public static final Byte REFUND_CLOSE = Byte.valueOf((byte)3);
    public static final String VALID = "VALID";
    public static final String VALID_APPROVING = "VALID_APPROVING";
    public static final String VALID_MODIFY = "VALID_MODIFY";
    public static final String AUDITING = "AUDITING";
    public static final String UN_SIGN = "UN_SIGN";
    public static Map<Byte, String> USER_SEX = new HashMap() {
        {
            this.put(Byte.valueOf((byte)0), "男");
            this.put(Byte.valueOf((byte)1), "女");
        }
    };
    public static Map<Byte, String> ORDER_STATUS = new HashMap() {
        {
            this.put(Dictionary.PAY_WAIT, "未支付");
            this.put(Dictionary.PAY_SUCCESS, "支付成功");
            this.put(Dictionary.PAY_UNDO, "已撤销");
            this.put(Dictionary.PAY_REFUND, "已退款");
            this.put(Dictionary.PAY_FAILD, "支付失败");
            this.put(Dictionary.PAY_PART_REFUND, "部分退款");
            this.put(Dictionary.PAY_CLOSE, "已关闭");
        }
    };
    public static Map<Byte, String> USER_ENABLE = new HashMap() {
        {
            this.put(Byte.valueOf((byte)0), "启用");
            this.put(Byte.valueOf((byte)1), "注销");
        }
    };
    public static Map<Byte, String> USER_TYPE = new HashMap() {
        {
            this.put(Byte.valueOf((byte)1), "店长");
            this.put(Byte.valueOf((byte)2), "员工");
        }
    };
    public static Map<Byte, String> REWARD_STATUS = new HashMap() {
        {
            this.put(Byte.valueOf((byte)0), "打赏失败");
            this.put(Byte.valueOf((byte)1), "打赏成功");
            this.put(Byte.valueOf((byte)2), "打赏失败");
        }
    };
    public static Map<Byte, String> wxPayType = new HashMap() {
        {
            this.put(Byte.valueOf((byte)0), "刷卡支付");
            this.put(Byte.valueOf((byte)1), "二维码支付");
        }
    };
    public static final String REDIS_RONGLIAN_SFYZ_SMS = "ronglian_sfyz_sms";
    public static final String LOGIN_TOKEN = "loginToken";
    public static final String COMPONEN_LIST = "componentList";
    public static Map<Long, AgentAliIsvCommon> AliIsv = new Hashtable();
    public static final String PAY_TYPE_MAIN = "QR_mian_pay";
    public static final String PAY_TYPE_REWARD = "QR_reward_pay";
    public static final String BROWSER_WX_PAY = "wx_pay_browser";
    public static final String BROWSER_ALI_PAY = "ali_pay_browser";
    public static final String BROWSER_OTHER = "other_browser";
    public static final String PAY_FOR_ID = "id";
    public static final String PAY_FOR_ORDERID = "orderId";
    public static final String PAY_FOR_STOREUSERID = "storeUserId";
    public static final Byte WX_PAY_TYPE = Byte.valueOf((byte)1);
    public static final Byte ALI_PAY_TYPE = Byte.valueOf((byte)2);

    public Dictionary() {
    }

    public static byte getWXRefundStatus(String tradeState) {
        byte status = 0;
        byte var3 = -1;
        switch(tradeState.hashCode()) {
            case -1149187101:
                if(tradeState.equals("SUCCESS")) {
                    var3 = 0;
                }
                break;
            case 2150174:
                if(tradeState.equals("FAIL")) {
                    var3 = 1;
                }
                break;
            case 907287315:
                if(tradeState.equals("PROCESSING")) {
                    var3 = 2;
                }
                break;
            case 1986660272:
                if(tradeState.equals("CHANGE")) {
                    var3 = 3;
                }
        }

        switch(var3) {
            case 0:
                status = REFUND_SUCCESS.byteValue();
                break;
            case 1:
                status = REFUND_FAILD.byteValue();
                break;
            case 2:
                status = REFUND_ING.byteValue();
                break;
            case 3:
                status = REFUND_CLOSE.byteValue();
        }

        return status;
    }

    public static byte getWXPayStatus(String tradeState) {
        byte status = 0;
        byte var3 = -1;
        switch(tradeState.hashCode()) {
            case -2136655264:
                if(tradeState.equals("PAYERROR")) {
                    var3 = 5;
                }
                break;
            case -1986353931:
                if(tradeState.equals("NOTPAY")) {
                    var3 = 2;
                }
                break;
            case -1881484424:
                if(tradeState.equals("REFUND")) {
                    var3 = 1;
                }
                break;
            case -1149187101:
                if(tradeState.equals("SUCCESS")) {
                    var3 = 0;
                }
                break;
            case 1818119806:
                if(tradeState.equals("REVOKED")) {
                    var3 = 4;
                }
                break;
            case 1990776172:
                if(tradeState.equals("CLOSED")) {
                    var3 = 3;
                }
        }

        switch(var3) {
            case 0:
                status = PAY_SUCCESS.byteValue();
                break;
            case 1:
                status = PAY_REFUND.byteValue();
                break;
            case 2:
                status = PAY_WAIT.byteValue();
                break;
            case 3:
                status = PAY_CLOSE.byteValue();
                break;
            case 4:
                status = PAY_UNDO.byteValue();
                break;
            case 5:
                status = PAY_FAILD.byteValue();
        }

        return status;
    }

    public static byte getALiPayStatus(String tradeState) {
        byte status = 0;
        byte var3 = -1;
        switch(tradeState.hashCode()) {
            case -1686543982:
                if(tradeState.equals("WAIT_BUYER_PAY")) {
                    var3 = 3;
                }
                break;
            case -1443174424:
                if(tradeState.equals("TRADE_SUCCESS")) {
                    var3 = 0;
                }
                break;
            case -1205295929:
                if(tradeState.equals("TRADE_CLOSED")) {
                    var3 = 2;
                }
                break;
            case -414706419:
                if(tradeState.equals("TRADE_FINISHED")) {
                    var3 = 1;
                }
        }

        switch(var3) {
            case 0:
                status = PAY_SUCCESS.byteValue();
                break;
            case 1:
                status = PAY_SUCCESS.byteValue();
                break;
            case 2:
                status = PAY_CLOSE.byteValue();
                break;
            case 3:
                status = PAY_WAIT.byteValue();
        }

        return status;
    }
}
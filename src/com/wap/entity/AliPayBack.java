package com.wap.entity;

import java.util.List;

public class AliPayBack {
        private List<String> fund_bill_list;
        private String subject;
        private String trade_no;
        private String gmt_create;
        private String notify_type;
        private String total_amount;
        private String out_trade_no;
        private String invoice_amount;
        private String open_id;
        private String seller_id;
        private String notify_time;
        private String trade_status;
        private String gmt_payment;
        private String seller_email;
        private String receipt_amount;
        private String buyer_id;
        private String app_id;
        private String notify_id;
        private String buyer_logon_id;
        private String sign_type;
        private String buyer_pay_amount;
        private String sign;
        private String point_amount;
        private String terminal_id;

    @Override
    public String toString() {
        return "AliPayBack{" +
                "fund_bill_list=" + fund_bill_list +
                ", subject='" + subject + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", notify_type='" + notify_type + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", invoice_amount='" + invoice_amount + '\'' +
                ", open_id='" + open_id + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", notify_time='" + notify_time + '\'' +
                ", trade_status='" + trade_status + '\'' +
                ", gmt_payment='" + gmt_payment + '\'' +
                ", seller_email='" + seller_email + '\'' +
                ", receipt_amount='" + receipt_amount + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", notify_id='" + notify_id + '\'' +
                ", buyer_logon_id='" + buyer_logon_id + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", buyer_pay_amount='" + buyer_pay_amount + '\'' +
                ", sign='" + sign + '\'' +
                ", point_amount='" + point_amount + '\'' +
                ", terminal_id='" + terminal_id + '\'' +
                '}';
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public List<String> getFund_bill_list() {
        return fund_bill_list;
    }

    public void setFund_bill_list(List<String> fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }
}


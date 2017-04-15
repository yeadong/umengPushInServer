package com.wap.service;

import com.wap.entity.AliPayBack;


public abstract interface AlipayService
{
  public abstract String payCallback(AliPayBack paramAliPayBack, String paramString1, String paramString2);
}
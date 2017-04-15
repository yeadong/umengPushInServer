package com.wap.entity;

import java.io.Serializable;
import java.util.Date;

public class AgentAliIsv
  implements Serializable
{
  private Long id;
  private String name;
  private String pid;
  private String appid;
  private String aliKey;
  private String publicKey;
  private Double thisProrata;
  private Double newProrata;
  private Date effectTime;
  private static final long serialVersionUID = 1L;

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = (name == null ? null : name.trim());
  }

  public String getPid() {
    return this.pid;
  }

  public void setPid(String pid) {
    this.pid = (pid == null ? null : pid.trim());
  }

  public String getAppid() {
    return this.appid;
  }

  public void setAppid(String appid) {
    this.appid = (appid == null ? null : appid.trim());
  }

  public String getAliKey() {
    return this.aliKey;
  }

  public void setAliKey(String aliKey) {
    this.aliKey = (aliKey == null ? null : aliKey.trim());
  }

  public String getPublicKey() {
    return this.publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = (publicKey == null ? null : publicKey.trim());
  }

  public Double getThisProrata() {
    return this.thisProrata;
  }

  public void setThisProrata(Double thisProrata) {
    this.thisProrata = thisProrata;
  }

  public Double getNewProrata() {
    return this.newProrata;
  }

  public void setNewProrata(Double newProrata) {
    this.newProrata = newProrata;
  }

  public Date getEffectTime() {
    return this.effectTime;
  }

  public void setEffectTime(Date effectTime) {
    this.effectTime = effectTime;
  }
}

/* Location:           C:\Users\Administrator\Desktop\chuangjiang\服务器20161220\application\merchant\webapp\WEB-INF\lib\partner-platform-model-1.8.5.jar
 * Qualified Name:     com.cloudrelation.partner.platform.model.AgentAliIsv
 * JD-Core Version:    0.6.2
 */
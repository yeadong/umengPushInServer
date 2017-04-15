package com.wap.entity;

public class GoodsEntity {
	// goods.id,
	// goods.`name`,goods.price,type.`name`,goods.merchant_id,goods_desc
	private Long id;
	private Long typeid;
	private String goodName;
	private Integer goodPrice;
	// private String typeName;
	private String goodsDesc;
	private Integer printType;

	public Integer getPrintType() {
		return printType;
	}

	public void setPrintType(Integer printType) {
		this.printType = printType;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Integer getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Integer goodPrice) {
		this.goodPrice = goodPrice;
	}

	// public String getTypeName() {
	// return typeName;
	// }
	//
	// public void setTypeName(String typeName) {
	// this.typeName = typeName;
	// }

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

}

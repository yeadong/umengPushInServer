package com.gjcm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjcm.dao.HFOrderVersionMapper;
import com.gjcm.entity.HFOrderVersion;

@Service
public class HFOrderServer {
	@Resource
	HFOrderVersionMapper hfOrderVersionMapper;

	public HFOrderVersion getversion() {
		HFOrderVersion orderVersion = hfOrderVersionMapper
				.selectHForderVersion();
		return orderVersion;
	}

}

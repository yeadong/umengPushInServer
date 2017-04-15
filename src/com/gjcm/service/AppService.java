package com.gjcm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjcm.dao.AppVersionMapper;
import com.gjcm.entity.AppVersion;

@Service
public class AppService {
	@Resource
	private AppVersionMapper appVersionMapper;

	public AppVersion getVersion() {
		AppVersion appVersion = appVersionMapper.selectVersion();
		return appVersion;
	}
}

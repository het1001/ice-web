package com.het.ice.service.conv;

import com.het.ice.dao.model.AppMainImgDO;
import com.het.ice.model.AppMainImg;
import com.het.ice.web.request.AppMainImageCreateWO;
import com.het.ice.web.request.AppMainImageEditWO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AppMainImgConvert {

	public static AppMainImg conv(AppMainImgDO doModel) {
		if (doModel == null) {
			return null;
		}

		AppMainImg model = new AppMainImg();
		model.setId(doModel.getId());
		model.setName(doModel.getName());
		model.setImageKey(doModel.getImageKey());
		model.setActive(doModel.getActive());
		model.setCreateTime(doModel.getCreateTime());
		model.setModifyTime(doModel.getModifyTime());
		return model;
	}

	public static AppMainImg conv(AppMainImageEditWO appMainImageEditWO) {
		if (appMainImageEditWO == null) {
			return null;
		}

		AppMainImg model = new AppMainImg();
		model.setId(appMainImageEditWO.getId());
		model.setActive(appMainImageEditWO.getActive());
		return model;
	}

	public static AppMainImg conv(AppMainImageCreateWO appMainImageCreateWO) {
		if (appMainImageCreateWO == null) {
			return null;
		}

		AppMainImg model = new AppMainImg();
		model.setName(appMainImageCreateWO.getName());
		model.setImageKey(appMainImageCreateWO.getImageKey());
		return model;
	}

	public static AppMainImgDO conv(AppMainImg model) {
		if (model == null) {
			return null;
		}

		AppMainImgDO doModel = new AppMainImgDO();
		doModel.setId(model.getId());
		doModel.setName(model.getName());
		doModel.setImageKey(model.getImageKey());
		doModel.setActive(model.getActive());
		doModel.setCreateTime(model.getCreateTime());
		doModel.setModifyTime(model.getModifyTime());
		return doModel;
	}

	public static List<AppMainImg> conv(List<AppMainImgDO> appMainImgDOS) {
		List<AppMainImg> appMainImgs = new ArrayList<AppMainImg>();
		if (CollectionUtils.isEmpty(appMainImgDOS)) {
			return appMainImgs;
		}

		for (AppMainImgDO appMainImgDO : appMainImgDOS) {
			appMainImgs.add(conv(appMainImgDO));
		}

		return appMainImgs;
	}
}

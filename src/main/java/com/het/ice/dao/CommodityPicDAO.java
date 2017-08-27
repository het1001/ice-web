package com.het.ice.dao;

import com.het.ice.dao.model.CommodityPicDO;
import com.het.ice.dao.model.LobDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author Administrator
 *
 */
public interface CommodityPicDAO {

	long insert(CommodityPicDO commodityPicDO);

	void update(CommodityPicDO commodityPicDO);

	void delete(long id);

	CommodityPicDO getMainByComId(@Param(value = "comId") long comId);

	List<CommodityPicDO> queryOtherByComId(@Param(value = "comId") long comId);

	List<CommodityPicDO> queryByComId(@Param(value = "comId") long comId);
}

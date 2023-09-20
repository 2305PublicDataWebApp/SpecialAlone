package com.alone.special.diner.service;

import java.util.List;

import com.alone.special.diner.domain.Diner;
import com.alone.special.diner.domain.DinerFile;
import com.alone.special.diner.domain.DinerRev;
import com.alone.special.diner.domain.DinerRevFile;
import com.alone.special.foodProduct.domain.PageInfo;

public interface DinerService {

	int insertDinerInfo(Diner diner);

	int getCurrentDinerId();

	int insertDinerFiles(List<DinerFile> dList);

	Integer getListCount();

	Integer getListCountByRegion(String region);

	List<DinerFile> selectDinerFileList();

	List<Diner> selectDinerInfoList(PageInfo pInfo);

	List<Diner> selectDinerInfoListByRegion(String region, PageInfo pInfo);

	Diner selectDetailInfoByFDinerId(Integer fDinerId);

	List<DinerFile> selectDetailFileByRefFDinerId(Integer refFDinerId);

	int insertRevInfo(DinerRev dinerRev);

	int getCurrentFDinerRevId();

	int insertRevFiles(List<DinerRevFile> dRevList);

	Integer getRevListCount();

	List<DinerRev> selectRevListByFdinerId(int fDinerId, PageInfo pInfo);

	List<DinerRevFile> selectRevFileList();

	int deleteDiner(int fDinerId);

	int deleteRev(DinerRev dinerRev);




}

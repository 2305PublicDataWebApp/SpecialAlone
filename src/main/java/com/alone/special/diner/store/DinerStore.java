package com.alone.special.diner.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alone.special.diner.domain.Diner;
import com.alone.special.diner.domain.DinerFile;
import com.alone.special.diner.domain.DinerRev;
import com.alone.special.diner.domain.DinerRevFile;
import com.alone.special.foodProduct.domain.PageInfo;

public interface DinerStore {

	int insertDinerInfo(SqlSession session, Diner diner);

	int getCurrentDinerId(SqlSession session);

	int insertDinerFiles(List<DinerFile> dList, SqlSession session);

	int selectListCount(SqlSession session);

	int selectListCountByRegion(SqlSession session, String region);

	List<DinerFile> selectDinerFileList(SqlSession session);

	List<Diner> selectDinerInfoList(SqlSession session, PageInfo pInfo);

	List<Diner> selectDinerInfoListByRegion(SqlSession session, PageInfo pInfo, String region);

	Diner selectDetailInfoByFDinerId(SqlSession session, Integer fDinerId);

	List<DinerFile> selectDetailFileByRefFDinerId(SqlSession session, Integer refFDinerId);

	int insertRevInfo(SqlSession session, DinerRev dinerRev);

	int getCurrentFDinerRevId(SqlSession session);

	int insertRevFiles(SqlSession session, List<DinerRevFile> dRevList);



}

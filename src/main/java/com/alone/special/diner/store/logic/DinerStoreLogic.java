package com.alone.special.diner.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.alone.special.diner.domain.Diner;
import com.alone.special.diner.domain.DinerFile;
import com.alone.special.diner.domain.DinerRev;
import com.alone.special.diner.domain.DinerRevFile;
import com.alone.special.diner.store.DinerStore;
import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.PageInfo;

@Repository
public class DinerStoreLogic implements DinerStore{

	@Override
	public int insertDinerInfo(SqlSession session, Diner diner) {
		int result = session.insert("FoodDinerMapper.insertDinerInfo", diner);
		return result;
	}

	@Override
	public int getCurrentDinerId(SqlSession session) {
		int result = session.selectOne("FoodDinerMapper.getCurrentDinerId");
		return result;
	}

	@Override
	public int insertDinerFiles(List<DinerFile> dList, SqlSession session) {
		Map<String,Object> params = new HashMap<>();
		params.put("list", dList);
		int result = session.insert("FoodDinerMapper.insertDinerFiles", params);
		return result;
	}

	@Override
	public int insertRevFiles(SqlSession session, List<DinerRevFile> dRevList) {
		Map<String,Object> params = new HashMap<>();
		params.put("list", dRevList);
		int result = session.insert("FoodDinerMapper.insertRevFiles", params);
		return result;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("FoodDinerMapper.selectListCount");
		return result;
	}

	@Override
	public int selectListCountByRegion(SqlSession session, String region) {
		int result = session.selectOne("FoodDinerMapper.selectListCountByRegion", region);
		return result;
	}

	@Override
	public List<DinerFile> selectDinerFileList(SqlSession session) {
		List<DinerFile> dFileList = session.selectList("FoodDinerMapper.selectDinerFileList");
		return dFileList;
	}

	@Override
	public List<Diner> selectDinerInfoList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Diner> dInfoList = session.selectList("FoodDinerMapper.selectDinerInfoList", null, rowBounds);
		return dInfoList;
	}

	@Override
	public List<Diner> selectDinerInfoListByRegion(SqlSession session, PageInfo pInfo, String region) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Diner> dInfoList = session.selectList("FoodDinerMapper.selectDinerInfoListByRegion", region, rowBounds);
		return dInfoList;
	}

	@Override
	public Diner selectDetailInfoByFDinerId(SqlSession session, Integer fDinerId) {
		Diner diner = session.selectOne("FoodDinerMapper.selectDetailInfoByFDinerId", fDinerId);
		return diner;
	}

	@Override
	public List<DinerFile> selectDetailFileByRefFDinerId(SqlSession session, Integer refFDinerId) {
		List<DinerFile> dFList = session.selectList("FoodDinerMapper.selectDetailFileByRefDinerId",refFDinerId);
		return dFList;
	}

	@Override
	public int insertRevInfo(SqlSession session, DinerRev dinerRev) {
		int result = session.insert("FoodDinerMapper.insertRevInfo", dinerRev);
		return result;
	}

	@Override
	public int getCurrentFDinerRevId(SqlSession session) {
		int currentFDinerRevId = session.selectOne("FoodDinerMapper.getCurrentFDinerRevId"); // 실제 시퀀스 값을 가져오는 코드가 필요합니다.
        return currentFDinerRevId;
	}

	@Override
	public int getRevListCount(SqlSession session) {
		int result = session.selectOne("FoodDinerMapper.getRevListCount");
		return result;
	}

	@Override
	public List<DinerRev> selectRevListByFdinerId(SqlSession session, PageInfo pInfo, int fDinerId) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<DinerRev> dRevList = session.selectList("FoodDinerMapper.selectRevListByFdinerId", fDinerId, rowBounds);
		return dRevList;
	}

	@Override
	public List<DinerRevFile> selectRevFileList(SqlSession session) {
		List<DinerRevFile> dRevFileList = session.selectList("FoodDinerMapper.selectRevFileList");
		return dRevFileList;
	}



}

package com.alone.special.diner.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alone.special.diner.domain.Diner;
import com.alone.special.diner.domain.DinerFile;
import com.alone.special.diner.domain.DinerRev;
import com.alone.special.diner.domain.DinerRevFile;
import com.alone.special.diner.service.DinerService;
import com.alone.special.diner.store.DinerStore;
import com.alone.special.foodProduct.domain.PageInfo;

@Service
public class DinerServiceImpl implements DinerService{
	@Autowired
	private SqlSession session;
	@Autowired
	private DinerStore FDStore;
	
	@Override
	public int insertDinerInfo(Diner diner) {
		int result = FDStore.insertDinerInfo(session,diner); 
		return result;
	}

	@Override
	public int getCurrentDinerId() {
        return FDStore.getCurrentDinerId(session);

	}

	@Override
	public int insertDinerFiles(List<DinerFile> dList) {
		int result = FDStore.insertDinerFiles(dList,session);
		return result;
	}

	@Override
	public Integer getListCount() {
		int result = FDStore.selectListCount(session);
		return result;
	}

	@Override
	public Integer getListCountByRegion(String region) {
		int result = FDStore.selectListCountByRegion(session,region);
		return result;
	}

	@Override
	public List<DinerFile> selectDinerFileList() {
		List<DinerFile> dFileList = FDStore.selectDinerFileList(session);
		return dFileList;
	}

	@Override
	public List<Diner> selectDinerInfoList(PageInfo pInfo) {
		List<Diner> dInfoList = FDStore.selectDinerInfoList(session,pInfo);
		return dInfoList;
	}

	@Override
	public List<Diner> selectDinerInfoListByRegion(String region, PageInfo pInfo) {
		List<Diner> dInfoList = FDStore.selectDinerInfoListByRegion(session,pInfo,region);
		return dInfoList;
	}

	@Override
	public Diner selectDetailInfoByFDinerId(Integer fDinerId) {
		Diner diner = FDStore.selectDetailInfoByFDinerId(session,fDinerId);
		return diner;
	}

	@Override
	public List<DinerFile> selectDetailFileByRefFDinerId(Integer refFDinerId) {
		List<DinerFile> dFList = FDStore.selectDetailFileByRefFDinerId(session,refFDinerId);
		return dFList;
	}

	@Override
	public int insertRevInfo(DinerRev dinerRev) {
		int result = FDStore.insertRevInfo(session,dinerRev);
		return result;
	}

	@Override
	public int getCurrentFDinerRevId() {
		return FDStore.getCurrentFDinerRevId(session);
	}

	@Override
	public int insertRevFiles(List<DinerRevFile> dRevList) {
		int result = FDStore.insertRevFiles(session,dRevList);
		return result;
	}

	@Override
	public Integer getRevListCount() {	
		int result = FDStore.getRevListCount(session);
		return result;
	}

	@Override
	public List<DinerRev> selectRevListByFdinerId(int fDinerId, PageInfo pInfo) {
		List<DinerRev> dRevList = FDStore.selectRevListByFdinerId(session,pInfo,fDinerId);
		return dRevList;
	}

	@Override
	public List<DinerRevFile> selectRevFileList() {
		List<DinerRevFile> dRevFileList = FDStore.selectRevFileList(session);
		return dRevFileList;
	}



}

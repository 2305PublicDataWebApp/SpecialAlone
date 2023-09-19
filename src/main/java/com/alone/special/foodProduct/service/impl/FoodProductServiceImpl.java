package com.alone.special.foodProduct.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.PageInfo;
import com.alone.special.foodProduct.service.FoodProductService;
import com.alone.special.foodProduct.store.FoodProductStore;

@Service
public class FoodProductServiceImpl implements FoodProductService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private FoodProductStore FPStore;

	@Override
	public int insertProductInfo(FoodProduct fProduct) {	
		int result = FPStore.insertProductInfo(fProduct,session);
		return result;
	}

	@Override
	public int insertProductFiles(List<FoodProductFile> fList) {
		int result = FPStore.insertProductFiles(fList,session);
		return result;
	}

	@Override
    public int getCurrentProductId() {
        return FPStore.getCurrentProductId(session);
    }

	@Override
	public Integer getListCount() {
		int result = FPStore.selectListCount(session);
		return result;
	}

	@Override
	public List<FoodProduct> selectProductInfoList(PageInfo pInfo) {
		List<FoodProduct> fPInfoList = FPStore.selectProductInfoList(session,pInfo);
		return fPInfoList;
	}

	@Override
	public List<FoodProductFile> selectProductFileList() {
		List<FoodProductFile> fPFileList = FPStore.selectProductFileList(session);
		return fPFileList;
	}

	@Override
	public Integer getListCountByCategory(String category) {
		int result = FPStore.selectListCountByCategory(session,category);
		return result;
	}

	@Override
	public List<FoodProduct> selectProductInfoListByCategory(String category, PageInfo pInfo) {
		List<FoodProduct> fPInfoList = FPStore.selectProductInfoListByCategory(session,pInfo,category);
		return fPInfoList;
	}

	@Override
	public FoodProduct selectDetailInfoByFProductId(int fProductId) {
		FoodProduct fProduct = FPStore.selectDetailInfoByFProductId(session,fProductId);
		return fProduct;
	}

	@Override
	public List<FoodProductFile> selectDetailFileByRefFProductId(Integer refFProductId) {
		List<FoodProductFile> fProductFileList = FPStore.selectDetailFileByRefFProductId(session,refFProductId);
		return fProductFileList;
	}

	@Override
	public int insertPhotoRevInfo(FoodProductPhotoRev fProductPhotoRev) {
		int result = FPStore.insertPhotoRevInfo(session,fProductPhotoRev);
		return result;
	}

	@Override
	public int getCurrentFProductRevId() {
		return FPStore.getCurrentFProductRevId(session);
	}

	@Override
	public int insertPhotoRevFiles(List<FoodProductPhotoRevFile> fPhotoRevList) {
		int result = FPStore.insertPhotoRevFiles(session,fPhotoRevList);
		return result;
	}

	@Override
	public Integer getRevListCount() {
		int result = FPStore.getRevListCount(session);
		return result;		
	}

	@Override
	public List<FoodProductPhotoRev> selectPhotoRevList(int fProductId,PageInfo pInfo) {
		List<FoodProductPhotoRev> fPPhotoRevList = FPStore.selectPhotoRevList(session,pInfo,fProductId);
		return fPPhotoRevList;
	}

	@Override
	public List<FoodProductPhotoRevFile> selectPhotoRevFileList() {
		List<FoodProductPhotoRevFile> fPPhotoRevFileList = FPStore.selectPhotoRevFileList(session);
		return fPPhotoRevFileList;
	}



	@Override
	public List<FoodProductOneRev> selectOneRevList(int fProductId) {
		List<FoodProductOneRev> fPOneRevList = FPStore.selectOneRevList(session,fProductId);
		return fPOneRevList;
	}

	@Override
	public int oneReviewRegister(FoodProductOneRev fPOneRev) {
		int result = FPStore.oneReviewRegister(session, fPOneRev);
		return result;
	}

	@Override
	public int modifyProductInfo(FoodProduct fProduct) {
		int result = FPStore.modifyProductInfo(session,fProduct);
		return result;
	}

	@Override
	public int modifyProductFiles(List<FoodProductFile> fList) {
		int result = FPStore.modifyProductFiles(session,fList);
		return result;
	}

	@Override
	public int deleteProduct(int fProductId) {
		int result = FPStore.deleteProduct(session,fProductId);
		return result;
	}

	@Override
	public int photoRevDelete(FoodProductPhotoRev fPPhotoRev) {
		int result = FPStore.photoRevDelete(session,fPPhotoRev);
		return result;
	}

	@Override
	public int oneRevDelete(FoodProductOneRev fPOneRev) {
		int result = FPStore.oneRevDelete(session,fPOneRev);
		return result;
	}




	


}

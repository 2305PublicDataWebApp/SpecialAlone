package com.alone.special.foodProduct.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.PageInfo;


public interface FoodProductStore {

	int insertProductInfo(FoodProduct fProduct, SqlSession session);

	int insertProductFiles(List<FoodProductFile> fList, SqlSession session);

	int getCurrentProductId(SqlSession session);

	int selectListCount(SqlSession session);

	List<FoodProduct> selectProductInfoList(SqlSession session, PageInfo pInfo);

	List<FoodProductFile> selectProductFileList(SqlSession session);

	int selectListCountByCategory(SqlSession session, String category);

	List<FoodProduct> selectProductInfoListByCategory(SqlSession session, PageInfo pInfo, String category);

	FoodProduct selectDetailInfoByFProductId(SqlSession session, int fProductId);

	List<FoodProductFile> selectDetailFileByRefFProductId(SqlSession session, Integer refFProductId);

	int insertPhotoRevInfo(SqlSession session, FoodProductPhotoRev fProductPhotoRev);

	int getCurrentFProductRevId(SqlSession session);

	int insertPhotoRevFiles(SqlSession session, List<FoodProductPhotoRevFile> fPhotoRevList);

	int getRevListCount(SqlSession session);

	List<FoodProductPhotoRev> selectPhotoRevList(SqlSession session, PageInfo pInfo, int fProductId);

	List<FoodProductPhotoRevFile> selectPhotoRevFileList(SqlSession session);

	int oneReviewRegister(SqlSession session,FoodProductOneRev fPOneRev);

	List<FoodProductOneRev> selectOneRevList(SqlSession session, int fProductId);

	int modifyProductInfo(SqlSession session, FoodProduct fProduct);

	int modifyProductFiles(SqlSession session, List<FoodProductFile> fList);

	int deleteProduct(SqlSession session, int fProductId);

	int photoRevDelete(SqlSession session, FoodProductPhotoRev fPPhotoRev);

	int oneRevDelete(SqlSession session, FoodProductOneRev fPOneRev);



	






}

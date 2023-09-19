package com.alone.special.foodProduct.service;

import java.util.List;

import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.PageInfo;

public interface FoodProductService {

	int insertProductInfo(FoodProduct fProduct);

	int insertProductFiles(List<FoodProductFile> fList);

    int getCurrentProductId();

	Integer getListCount();

	List<FoodProduct> selectProductInfoList(PageInfo pInfo);

	List<FoodProductFile> selectProductFileList();

	Integer getListCountByCategory(String category);

	List<FoodProduct> selectProductInfoListByCategory(String category, PageInfo pInfo);

	FoodProduct selectDetailInfoByFProductId(int fProductId);

	List<FoodProductFile> selectDetailFileByRefFProductId(Integer refFProductId);

	int insertPhotoRevInfo(FoodProductPhotoRev fProductPhotoRev);

	int getCurrentFProductRevId();

	int insertPhotoRevFiles(List<FoodProductPhotoRevFile> fPhotoRevList);

	Integer getRevListCount();

	List<FoodProductPhotoRev> selectPhotoRevList(int fProductId,PageInfo pInfo);

	List<FoodProductPhotoRevFile> selectPhotoRevFileList();

	int oneReviewRegister(FoodProductOneRev fPOneRev);

	List<FoodProductOneRev> selectOneRevList(int fProductId);

	int modifyProductInfo(FoodProduct fProduct);

	int modifyProductFiles(List<FoodProductFile> fList);

	int deleteProduct(int fProductId);

	int photoRevDelete(FoodProductPhotoRev fPPhotoRev);

	int oneRevDelete(FoodProductOneRev fPOneRev);



	


	

}

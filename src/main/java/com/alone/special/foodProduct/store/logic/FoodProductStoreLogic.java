package com.alone.special.foodProduct.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.PageInfo;
import com.alone.special.foodProduct.store.FoodProductStore;


@Repository
public class FoodProductStoreLogic implements FoodProductStore{

	@Override
	public int insertProductInfo(FoodProduct fProduct, SqlSession session) {
		int result = session.insert("FoodProductMapper.insertProductInfo",fProduct);
		return result;
	}

	@Override
	public int insertProductFiles(List<FoodProductFile> fList, SqlSession session) {
		Map<String,Object> params = new HashMap<>();
		params.put("list", fList);
		int result = session.insert("FoodProductMapper.insertProductFiles", params);
		return result;
	}

	@Override
	public int getCurrentProductId(SqlSession session) {
		int currentProductId = session.selectOne("FoodProductMapper.getCurrentProductId"); // 실제 시퀀스 값을 가져오는 코드가 필요합니다.
        return currentProductId;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("FoodProductMapper.selectListCount");
		return result;
	}

	@Override
	public List<FoodProduct> selectProductInfoList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<FoodProduct> fPInfoList = session.selectList("FoodProductMapper.selectProductInfoList", null, rowBounds);
		return fPInfoList;
	}

	@Override
	public List<FoodProductFile> selectProductFileList(SqlSession session) {
		List<FoodProductFile> fPFileList = session.selectList("FoodProductMapper.selectProductFileList");
		return fPFileList;
	}

	@Override
	public int selectListCountByCategory(SqlSession session, String category) {
		int result = session.selectOne("FoodProductMapper.selectListCountByCategory", category);
		return result;
	}

	@Override
	public List<FoodProduct> selectProductInfoListByCategory(SqlSession session, PageInfo pInfo, String category) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<FoodProduct> fPInfoList = session.selectList("FoodProductMapper.selectProductInfoListByCategory", category, rowBounds);
		return fPInfoList;
	}

	@Override
	public FoodProduct selectDetailInfoByFProductId(SqlSession session, int fProductId) {
		FoodProduct fproduct = session.selectOne("FoodProductMapper.selectDetailInfoByFProdcutId", fProductId);
		return fproduct;
	}

	@Override
	public List<FoodProductFile> selectDetailFileByRefFProductId(SqlSession session, Integer refFProductId) {
		List<FoodProductFile> fProductFileList = session.selectList("FoodProductMapper.selectDetailFileByRefFProductId", refFProductId);
		return fProductFileList;
	}

	@Override
	public int insertPhotoRevInfo(SqlSession session, FoodProductPhotoRev fProductPhotoRev) {
		int result = session.insert("FoodProductMapper.insertPhotoRevInfo", fProductPhotoRev);
		return result;
	}

	@Override
	public int getCurrentFProductRevId(SqlSession session) {
		int currentFProductRevId = session.selectOne("FoodProductMapper.getCurrentFProductRevId"); // 실제 시퀀스 값을 가져오는 코드가 필요합니다.
        return currentFProductRevId;
	}

	@Override
	public int insertPhotoRevFiles(SqlSession session, List<FoodProductPhotoRevFile> fPhotoRevList) {
		Map<String,Object> params = new HashMap<>();
		params.put("list", fPhotoRevList);
		int result = session.insert("FoodProductMapper.insertPhotoRevFiles", params);
		return result;
	}

	@Override
	public int getRevListCount(SqlSession session) {
		int result = session.selectOne("FoodProductMapper.getRevListCount");
		return result;
	}

	@Override
	public List<FoodProductPhotoRev> selectPhotoRevList(SqlSession session, PageInfo pInfo, int fProductId) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<FoodProductPhotoRev> FPPhotoRevList = session.selectList("FoodProductMapper.selectPhotoRevList", fProductId, rowBounds);
		return FPPhotoRevList;
	}

	@Override
	public List<FoodProductPhotoRevFile> selectPhotoRevFileList(SqlSession session) {
		List<FoodProductPhotoRevFile> fPPhotoRevFileList = session.selectList("FoodProductMapper.selectPhotoRevFileList");
		return fPPhotoRevFileList;
	}

	@Override
	public int oneReviewRegister(SqlSession session, FoodProductOneRev fPOneRev) {
		int result = session.insert("FoodProductMapper.oneReviewRegister", fPOneRev);
		return result;
	}

	@Override
	public List<FoodProductOneRev> selectOneRevList(SqlSession session, int fProductId) {
		List<FoodProductOneRev> fPOneRevList = session.selectList("FoodProductMapper.selectOneRevList", fProductId);
		return fPOneRevList;
	}

	@Override
	public int modifyProductInfo(SqlSession session, FoodProduct fProduct) {
		int result = session.update("FoodProductMapper.modifyProductInfo", fProduct);
		return result;
	}

	@Override
	public int modifyProductFiles(SqlSession session, List<FoodProductFile> fList) {
		int result = session.update("FoodProductMapper.modifyProductFiles", fList);
		return result;		
	}

	@Override
	public int deleteProduct(SqlSession session, int fProductId) {
		int result = session.delete("FoodProductMapper.deleteProduct", fProductId);
		return result;
	}

	@Override
	public int photoRevDelete(SqlSession session, FoodProductPhotoRev fPPhotoRev) {
		int result = session.delete("FoodProductMapper.photoRevDelete", fPPhotoRev);
		return result;
	}

	@Override
	public int oneRevDelete(SqlSession session, FoodProductOneRev fPOneRev) {
		int result = session.delete("FoodProductMapper.oneRevDelete", fPOneRev);
		return result;
	}









}

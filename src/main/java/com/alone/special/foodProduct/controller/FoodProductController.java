package com.alone.special.foodProduct.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.FoodProductRevSet;
import com.alone.special.foodProduct.domain.FoodProductSet;
import com.alone.special.foodProduct.domain.PageInfo;
import com.alone.special.foodProduct.service.FoodProductService;
import com.alone.special.user.domain.User;



@Controller
public class FoodProductController {

	@Autowired
	private FoodProductService FPService;
	
	// 추천상품 정보등록 페이지 이동
	@RequestMapping(value="/foodProduct/register.do", method=RequestMethod.GET)
	public ModelAndView showProductRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/foodRecommend/productReg");
		return mv;
	}

	// 추천상품 정보수정 페이지 이동
	@RequestMapping(value="/foodProduct/modifyInfo.do", method=RequestMethod.GET)
	public ModelAndView showProductModifyForm(ModelAndView mv
			,@RequestParam("fProductId") int fProductId) {
		try {
			FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductId);			
			if(foodProduct != null) {
				System.out.println(foodProduct);
				mv.addObject("foodProduct", foodProduct);
				mv.setViewName("food/foodRecommend/productModify");
			}else {
				mv.addObject("msg", "추천상품 정보수정폼 이동이 완료되지 않았습니다");
				mv.addObject("error", "추천상품 정보수정폼 이동 실패");
				mv.addObject("url", "/foodProduct/list.do");
				mv.setViewName("common/errorPage");				
			}			
		} catch (Exception e) {			 
			mv.addObject("msg", "추천상품 정보수정폼 이동 에러");
	        System.out.println(e.getMessage());
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage");			
		}
		return mv;
	}
	
	
	// 추천상품 파일등록 페이지 이동
	@RequestMapping(value="/foodProduct/fileregister.do", method=RequestMethod.GET)
	public ModelAndView showProductFileRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/foodRecommend/productFileReg");
		return mv;
	}

	// 추천상품 파일수정 페이지 이동
	@RequestMapping(value="/foodProduct/modifyFile.do", method=RequestMethod.GET)
	public ModelAndView showProductFileModifyForm(ModelAndView mv
			,@RequestParam("refFProductId") int refFProductId) {
		try {
			List<FoodProductFile> fPFileList = FPService.selectDetailFileByRefFProductId(refFProductId);
			if(fPFileList.size()>0) {
				mv.addObject("fPFileList", fPFileList);
				mv.setViewName("food/foodRecommend/productFileModify");
			}else {
				mv.addObject("msg", "추천상품 파일수정폼 이동이 완료되지 않았습니다");
				mv.addObject("error", "추천상품 파일수정폼 이동 실패");
				mv.addObject("url", "/foodProduct/list.do");
				mv.setViewName("common/errorPage");				
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "추천상품 파일수정폼 이동 에러");
	        System.out.println(e.getMessage());
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage");	
		}
		
		return mv;
	}	
	// 포토리뷰 정보 등록 페이지 이동
	@RequestMapping(value="/foodProduct/photoRevInfoRegister.do", method=RequestMethod.GET)
	public ModelAndView showPhotoRevInfoRegisterForm(ModelAndView mv
			,@RequestParam("fProductId") int fProductId) {
		FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductId);
		mv.addObject("foodProduct", foodProduct);
		mv.setViewName("food/foodRecommend/productPhotoRevInfoReg");
		return mv;
	}	

	// 포토리뷰 파일 등록 페이지 이동
	@RequestMapping(value="/foodProduct/photoRevFileRegister.do", method=RequestMethod.GET)
	public ModelAndView showPhotoRevFileRegisterForm(ModelAndView mv
			,@RequestParam("fProductId") int fProductId) {
		FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductId);
		mv.addObject("foodProduct", foodProduct);
		mv.setViewName("food/foodRecommend/productPhotoRevFileReg");
		return mv;
	}		
	// 상품 리뷰 리스트 이동
//	@RequestMapping(value="/foodProduct/showrevlist.do", method=RequestMethod.GET)
//	public ModelAndView showProductRevList(ModelAndView mv
//			,@RequestParam("fProductId") int fProductRevId) {
//		FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductRevId);
//		mv.addObject("foodProduct", foodProduct);
//		mv.setViewName("food/foodRecommend/productRevList");
//		return mv;
//	}		
	
	// 추천상품 목록 조회
	@RequestMapping(value = "/foodProduct/list.do", method = RequestMethod.GET)
	public ModelAndView showProductList(
	        ModelAndView mv,
	        @RequestParam(value = "category", required = false) String category,
	        @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
	        ,HttpSession session) {
	    try {
	        Integer totalCount;
	        if (category != null) {
	        	System.out.println(category);
	        	totalCount = FPService.getListCountByCategory(category);
	        }else {
	        	totalCount = FPService.getListCount();
	        }
	        PageInfo pInfo = this.getPageInfo(currentPage, totalCount,6);
	        
	        // 카테고리가 지정되지 않았을 때 전체 목록을 가져옵니다.
	        List<FoodProduct> fPInfoList;
	        List<FoodProductFile> fPFileList;
	        
	        fPFileList = FPService.selectProductFileList();
	        if (category == null) {
	            fPInfoList = FPService.selectProductInfoList(pInfo);
	        } else {
	            // 카테고리에 해당하는 제품 목록을 가져옵니다.
	            fPInfoList = FPService.selectProductInfoListByCategory(category, pInfo);
	        }
	
	        // 상품 세트 목록을 생성합니다.
	        List<FoodProductSet> foodProductSetList = createFoodProductSets(fPInfoList, fPFileList);
	        User user = (User) session.getAttribute("user");
	        mv.addObject("user", user);
	        mv.addObject("foodProductSetList", foodProductSetList);
	        mv.addObject("pInfo", pInfo);
	        mv.setViewName("food/foodRecommend/productList");
	    } catch (Exception e) {
	        // 예외 처리 로직 추가
	        mv.addObject("msg", "상품목록 불러오기 실패");
	        System.out.println(e.getMessage());
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/register.do");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
	    }
	    return mv;
	}

	// 상품 상세정보 조회
	@RequestMapping(value="/foodProduct/productdetail.do", method=RequestMethod.GET)
	public ModelAndView showProductDetail(ModelAndView mv
			,@ModelAttribute FoodProductOneRev fPOneRev
			,@RequestParam("fProductId") Integer fProductId
			,@RequestParam("refFProductId") Integer refFProductId) {	
		try {
			List<FoodProductOneRev> fPOneRevList;
	        fPOneRevList = FPService.selectOneRevList(fProductId);
			FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductId);
			List<FoodProductFile> fPFileList = FPService.selectDetailFileByRefFProductId(refFProductId);
			if(foodProduct !=null && fPFileList !=null) {
				mv.addObject("fPOneRevList", fPOneRevList);
				mv.addObject("foodProduct", foodProduct);
				mv.addObject("fPFileList", fPFileList);
				mv.setViewName("food/foodRecommend/productDetail");
			}else {
				mv.addObject("msg", "추천상품 상세조회가 완료되지 않았습니다");
				mv.addObject("error", "추천상품 상세조회 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "추천상품 상세조회 에러");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
		}
		return mv;
	}	
	
	
	
	// 상품리뷰 조회(한줄+포토)
	@RequestMapping(value = "/foodProduct/revlist.do", method = RequestMethod.GET)
	public ModelAndView showProductRevList(
	        ModelAndView mv
	        ,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
	        ,@RequestParam("fProductId") int fProductId) {
	    try {
			FoodProduct foodProduct = FPService.selectDetailInfoByFProductId(fProductId);
			mv.addObject("foodProduct", foodProduct);	    	
	        Integer totalCount;
	        totalCount = FPService.getRevListCount();
	        PageInfo pInfo = this.getPageInfo(currentPage, totalCount,2);	        
	        List<FoodProductPhotoRev> fPRevInfoList;
	        List<FoodProductPhotoRevFile> fPRevFileList;
	        fPRevInfoList = FPService.selectPhotoRevList(fProductId,pInfo);	        	        
	        fPRevFileList = FPService.selectPhotoRevFileList();
	        List<FoodProductOneRev> fPOneRevList;
	        fPOneRevList = FPService.selectOneRevList(fProductId);
	        System.out.println(fPOneRevList);
	        // 상품 세트 목록을 생성합니다.
	        List<FoodProductRevSet> foodProductRevSetList = createPhotoRevSets(fPRevInfoList, fPRevFileList);
	        mv.addObject("foodProductRevSetList", foodProductRevSetList);
	        mv.addObject("fPOneRevList", fPOneRevList);
	        mv.addObject("pInfo", pInfo);
	        mv.setViewName("food/foodRecommend/productRevList");
	    } catch (Exception e) {
	        // 예외 처리 로직 추가
	        mv.addObject("msg", "상품목록 불러오기 실패");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
	    }
	    return mv;
	}	

	// 추천상품 정보등록
	@RequestMapping(value="/foodProduct/register.do", method=RequestMethod.POST)
	public ModelAndView productInfoRegister(ModelAndView mv
			,@ModelAttribute FoodProduct fProduct
			,HttpSession session
			,HttpServletRequest request) {
		int result = FPService.insertProductInfo(fProduct);
		try {
			if(result>0) {
				//성공
				mv.setViewName("redirect:/foodProduct/fileregister.do");
			}else {
				//실패
				mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
				mv.addObject("error", "게시글 등록 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// 에러처리
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/register.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 추천상품 삭제(연관 파일,리뷰 모두삭제)
	@RequestMapping(value="/foodProduct/deleteProduct.do", method=RequestMethod.GET)
	public ModelAndView deleteProduct(ModelAndView mv
			,@RequestParam("fProductId") int fProductId) {
		try {
			int result = FPService.deleteProduct(fProductId);
				if(result>0) {
					mv.setViewName("redirect:/foodProduct/list.do");
				}else {
					mv.addObject("msg", "추천상품 삭제가 완료되지 않았습니다");
					mv.addObject("error", "추천상품 삭제 실패");
					mv.addObject("url", "/foodProduct/list.do");
					mv.setViewName("common/errorPage");
				}			
			} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/list.do");
			mv.setViewName("common/errorPage");			
		}		
		return mv;
	}
	
	
	
	// 추천상품 정보수정
	@RequestMapping(value="/foodProduct/modifyInfo.do", method=RequestMethod.POST)
	public ModelAndView productInfoModify(ModelAndView mv
			,@ModelAttribute FoodProduct fProduct
			,HttpSession session
			,HttpServletRequest request) {
		int result = FPService.modifyProductInfo(fProduct);
		try {
			if(result>0) {
				//성공
				mv.setViewName("redirect:/foodProduct/modifyFile.do?refFProductId=" + fProduct.getfProductId());
			}else {
				//실패
				mv.addObject("msg", "상품정보 수정이 완료되지 않았습니다");
				mv.addObject("error", "상품정보 수정 실패");
				mv.addObject("url", "/foodProduct/list.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// 에러처리
			mv.addObject("msg", "상품정보 수정 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/list.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}	
	// 추천상품 파일등록
	@RequestMapping(value="/foodProduct/fileregister.do", method=RequestMethod.POST)
	public ModelAndView productFileRegister(ModelAndView mv
			,@ModelAttribute FoodProduct fProduct
			,@RequestParam(value="imagename1", required=false) MultipartFile image1
			,@RequestParam(value="imagename2", required=false) MultipartFile image2
			,@RequestParam(value="imagename3", required=false) MultipartFile image3
			,HttpServletRequest request
			,HttpSession session) {
		try {
			int refFProductId = FPService.getCurrentProductId();
			// 이미지 3개가 값이 들어갔으면
	        List<FoodProductFile> fList = new ArrayList<>();
			if(image1 != null && !image1.getOriginalFilename().equals("")
					&& image2 != null && !image2.getOriginalFilename().equals("")
					&& image3 != null && !image3.getOriginalFilename().equals("")) {
				Map<String,Object> fMap1 = this.saveFile(request, image1);
				Map<String,Object> fMap2 = this.saveFile(request, image2);
				Map<String,Object> fMap3 = this.saveFile(request, image3);
				
				for (int i = 1; i <= 3; i++) {
				    FoodProductFile fProductFile = new FoodProductFile();
				    fProductFile.setRefFProductId(refFProductId); // 연관 상품 ID 설정
				    fProductFile.setfProductFileorder(i); // 파일 순서 설정
				    
				    // 각 이미지에 따라 파일 정보 설정
				    if (i == 1) {
				        fProductFile.setfProductFilename((String) fMap1.get("fileName"));
				        fProductFile.setfProductFilerename((String) fMap1.get("fileRename"));
				        fProductFile.setfProductFilepath((String) fMap1.get("filePath"));
				    } else if (i == 2) {
				        fProductFile.setfProductFilename((String) fMap2.get("fileName"));
				        fProductFile.setfProductFilerename((String) fMap2.get("fileRename"));
				        fProductFile.setfProductFilepath((String) fMap2.get("filePath"));
				    } else if (i == 3) {
				        fProductFile.setfProductFilename((String) fMap3.get("fileName"));
				        fProductFile.setfProductFilerename((String) fMap3.get("fileRename"));
				        fProductFile.setfProductFilepath((String) fMap3.get("filePath"));
				    }
				    
				    fList.add(fProductFile);
				}
			}
	        int result = FPService.insertProductFiles(fList);
			
			if(result == 3) {
				// 리스트로 이동 해야함
				mv.setViewName("redirect:/foodProduct/list.do");
			}else {
				// 에러페이지
				mv.addObject("msg", "파일등록이 완료되지 않았습니다");
				mv.addObject("error", "게시글 등록 실패");
				mv.addObject("url", "/foodProduct/fileregister.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/fileregister.do");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}

	// 추천상품 파일 수정
	@RequestMapping(value="/foodProduct/modifyFile.do", method=RequestMethod.POST)
	public ModelAndView productFileModify(ModelAndView mv
	        ,@RequestParam(name="updateImage1", defaultValue="false") boolean updateImage1
	        ,@RequestParam(name="updateImage2", defaultValue="false") boolean updateImage2
	        ,@RequestParam(name="updateImage3", defaultValue="false") boolean updateImage3
	        ,@RequestParam(value="imagename1", required=false) MultipartFile image1
	        ,@RequestParam(value="imagename2", required=false) MultipartFile image2
	        ,@RequestParam(value="imagename3", required=false) MultipartFile image3
	        ,@RequestParam(value="refFProductId") int refFProductId // refFProductId를 파라미터로 받습니다.
	        ,HttpServletRequest request
	        ,HttpSession session) {
	    try {
	        List<FoodProductFile> fList = new ArrayList<>();

	        // 사용자가 체크한 파일만 업데이트
	        if (updateImage1) {
	            Map<String,Object> fMap1 = this.saveFile(request, image1);
	            FoodProductFile fProductFile = new FoodProductFile();
	            fProductFile.setRefFProductId(refFProductId);
	            fProductFile.setfProductFileorder(1);
	            fProductFile.setfProductFilename((String) fMap1.get("fileName"));
	            fProductFile.setfProductFilerename((String) fMap1.get("fileRename"));
	            fProductFile.setfProductFilepath((String) fMap1.get("filePath"));
	            fList.add(fProductFile);
	        }

	        if (updateImage2) {
	            Map<String,Object> fMap2 = this.saveFile(request, image2);
	            FoodProductFile fProductFile = new FoodProductFile();
	            fProductFile.setRefFProductId(refFProductId);
	            fProductFile.setfProductFileorder(2);
	            fProductFile.setfProductFilename((String) fMap2.get("fileName"));
	            fProductFile.setfProductFilerename((String) fMap2.get("fileRename"));
	            fProductFile.setfProductFilepath((String) fMap2.get("filePath"));
	            fList.add(fProductFile);
	        }

	        if (updateImage3) {
	            Map<String,Object> fMap3 = this.saveFile(request, image3);
	            FoodProductFile fProductFile = new FoodProductFile();
	            fProductFile.setRefFProductId(refFProductId);
	            fProductFile.setfProductFileorder(3);
	            fProductFile.setfProductFilename((String) fMap3.get("fileName"));
	            fProductFile.setfProductFilerename((String) fMap3.get("fileRename"));
	            fProductFile.setfProductFilepath((String) fMap3.get("filePath"));
	            fList.add(fProductFile);
	        }
	        System.out.println(fList);
	        int result = FPService.modifyProductFiles(fList);

	        if(result > 0) {
	            // 리스트로 이동
	            mv.setViewName("redirect:/foodProduct/productdetail.do?refFProductId=" + refFProductId);
	        } else {
	            // 에러페이지
	            mv.addObject("msg", "파일 수정이 완료되지 않았습니다");
	            mv.addObject("error", "파일 수정 실패");
	            mv.addObject("url", "/foodProduct/list.do");
	            mv.setViewName("common/errorPage");
	        }
	    } catch (Exception e) {
	        mv.addObject("msg", "파일 수정이 완료되지 않았습니다");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage");
	    }

	    return mv;
	}

	
	// 포토리뷰 정보등록
	@RequestMapping(value="/foodProduct/photoRevInfoReg.do", method=RequestMethod.POST)
	public ModelAndView photoRevInfoRegister(ModelAndView mv
			,@ModelAttribute FoodProductPhotoRev FProductPhotoRev
			,@RequestParam(value="fProductId") int fProductId
			,HttpSession session
			) {
		String fUserId = (String)session.getAttribute("userId");
		FProductPhotoRev.setfUserId(fUserId);
		FProductPhotoRev.setRefFProductId(fProductId);
		int result = FPService.insertPhotoRevInfo(FProductPhotoRev);
		try {
			if(result>0) {
				//성공
				mv.setViewName("redirect:/foodProduct/photoRevFileRegister.do?fProductId=" + fProductId);
			}else {
				//실패
				mv.addObject("msg", "포토리뷰 정보이 완료되지 않았습니다");
				mv.addObject("error", "포토리뷰 정보 등록 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// 에러처리
			mv.addObject("msg", "포토리뷰 정보등록 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/showphotorevform.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 포토리뷰 파일등록
	@RequestMapping(value="/foodProduct/photorevfilereg.do", method=RequestMethod.POST)
	public ModelAndView photoRevFileRegister(ModelAndView mv			
			,@RequestParam(value="revImageName1", required=false) MultipartFile image1
			,@RequestParam(value="revImageName2", required=false) MultipartFile image2
			,HttpServletRequest request
			,HttpSession session) {
		try {
			int refFProductRevId = FPService.getCurrentFProductRevId();
			// 이미지 2개가 값이 들어갔으면
	        List<FoodProductPhotoRevFile> fPhotoRevList = new ArrayList<>();
			if(image1 != null && !image1.getOriginalFilename().equals("")
					&& image2 != null && !image2.getOriginalFilename().equals("")
					) {
				Map<String,Object> fMap1 = this.saveFile(request, image1);
				Map<String,Object> fMap2 = this.saveFile(request, image2);				
				for (int i = 1; i <= 2; i++) {
					FoodProductPhotoRevFile fPhotoRevFile = new FoodProductPhotoRevFile();
					fPhotoRevFile.setRefFProductRevId(refFProductRevId); // 연관 상품 ID 설정
					fPhotoRevFile.setfProductRevFileno(i);; // 파일 순서 설정
				    
				    // 각 이미지에 따라 파일 정보 설정
				    if (i == 1) {
				    	fPhotoRevFile.setfProductRevFilename((String) fMap1.get("fileName"));
				    	fPhotoRevFile.setfProductRevFilerename((String) fMap1.get("fileRename"));
				    	fPhotoRevFile.setfProductRevFilepath((String) fMap1.get("filePath"));
				    } else if (i == 2) {
				    	fPhotoRevFile.setfProductRevFilename((String) fMap2.get("fileName"));
				    	fPhotoRevFile.setfProductRevFilerename((String) fMap2.get("fileRename"));
				    	fPhotoRevFile.setfProductRevFilepath((String) fMap2.get("filePath"));
				    }				    
				    fPhotoRevList.add(fPhotoRevFile);
				}
			}
	        int result = FPService.insertPhotoRevFiles(fPhotoRevList);
			
			if(result == 2) {
				// 리스트로 이동 해야함
				mv.setViewName("redirect:/foodProduct/list.do");
				/////수정해야함 안넘어옴값
			}else {
				// 에러페이지
				mv.addObject("msg", "파일등록이 완료되지 않았습니다");
				mv.addObject("error", "게시글 등록 실패");
				mv.addObject("url", "/foodProduct/showphotorevform.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/foodProduct/showphotorevform.do");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}

	//포토리뷰 삭제
	@RequestMapping(value="/foodProduct/deletePhotoRev.do", method=RequestMethod.GET)
	public ModelAndView photoRevDelete(ModelAndView mv
			,@RequestParam("fProductRevId") int fProductRevId
			,@RequestParam("fProductId") int fProductId			
			,HttpSession session) {
		try {
				FoodProductPhotoRev FPPhotoRev = new FoodProductPhotoRev();
				FPPhotoRev.setfProductRevId(fProductRevId);
				FPPhotoRev.setRefFProductId(fProductId);
				int result =FPService.photoRevDelete(FPPhotoRev);		
				if(result>0) {
					mv.setViewName("redirect:/foodProduct/revlist.do?fProductId=" + fProductId);					
				}else {
					mv.addObject("msg", "리뷰삭제가 완료되지 않았습니다");
					mv.addObject("error", "리뷰삭제 실패");
					mv.addObject("url", "redirect:/index.jsp");
					mv.setViewName("common/errorPage");					
				}			
		} catch (Exception e) {
			mv.addObject("msg", "리뷰삭제 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "redirect:/index.jsp");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	// 한줄리뷰 등록
	@RequestMapping(value="/foodProduct/submitReview.do", method=RequestMethod.POST)
	public ModelAndView oneReviewRegister(ModelAndView mv
			,@ModelAttribute FoodProductOneRev fPOneRev
			,@RequestParam("refFProductId") int fProductId
			,HttpSession session) {
		try {
			String fProductOneRevWriter = (String)session.getAttribute("userId");
			fPOneRev.setfProductOneRevWriter(fProductOneRevWriter);
			int result = FPService.oneReviewRegister(fPOneRev);
			if(result>0) {
				mv.setViewName("redirect:/foodProduct/revlist.do?fProductId="+ fProductId);
			}else {
				mv.addObject("msg", "한줄리뷰 등록이 완료되지 않았습니다");
				mv.addObject("error", "한줄리뷰 등록 실패");
				mv.addObject("url", "/foodProduct/list.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "상품목록 불러오기 실패");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/foodProduct/list.do");
	        mv.setViewName("common/errorPage"); 
		}
		return mv;
	}
	
	//한줄리뷰 삭제
	@RequestMapping(value="/foodProduct/deleteOneRev.do", method=RequestMethod.GET)
	public ModelAndView oneRevDelete(ModelAndView mv
			,@RequestParam("No") int fOneRevNo
			,@RequestParam("refId") int refId
			,HttpSession session) {
		try {
				FoodProductOneRev fPOneRev= new FoodProductOneRev();
				fPOneRev.setRefFProductId(refId);
				fPOneRev.setfProductOneRevNo(fOneRevNo);
				int result =FPService.oneRevDelete(fPOneRev);		
				if(result>0) {
					mv.setViewName("redirect:/foodProduct/list.do");					
				}else {
					mv.addObject("msg", "리뷰삭제가 완료되지 않았습니다");
					mv.addObject("error", "리뷰삭제 실패");
					mv.addObject("url", "redirect:/index.jsp");
					mv.setViewName("common/errorPage");					
				}			
		} catch (Exception e) {
			mv.addObject("msg", "리뷰삭제 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "redirect:/index.jsp");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}	
	
	
	
	// 상품 세트 목록을 생성하는 메서드
	private List<FoodProductSet> createFoodProductSets(List<FoodProduct> fPInfoList, List<FoodProductFile> fPFileList) {
	    List<FoodProductSet> foodProductSetList = new ArrayList<>();
	    for (FoodProduct foodProduct : fPInfoList) {
	        FoodProductSet foodProductSet = new FoodProductSet();
	        foodProductSet.setFoodProduct(foodProduct);
	
	        // 대표 이미지를 찾기 위한 루프
	        for (FoodProductFile file : fPFileList) {
	            if (file.getRefFProductId() == foodProduct.getfProductId() && file.getfProductFileorder() == 1) {
	                // 대표 이미지를 찾았을 때, 해당 파일을 세트에 추가합니다.
	               foodProductSet.setFoodProductFile(file);
	                break; // 대표 이미지를 찾았으므로 더 이상 루프를 돌 필요가 없습니다.
	            }
	        }
	        // 세트를 목록에 추가합니다.
	        foodProductSetList.add(foodProductSet);
	    }
	    return foodProductSetList;
	}

    
    
	private List<FoodProductRevSet> createPhotoRevSets(List<FoodProductPhotoRev> fPRevInfoList, List<FoodProductPhotoRevFile> fPRevFileList) {
	    List<FoodProductRevSet> foodProductRevSetList = new ArrayList<>();
	    for (FoodProductPhotoRev revInfo  : fPRevInfoList) {
	    	FoodProductRevSet foodProductRevSet = new FoodProductRevSet();
	        foodProductRevSet.setFPPhotoRev(revInfo);
	        List<FoodProductPhotoRevFile> revFiles = new ArrayList<>();
	        for (FoodProductPhotoRevFile revFile : fPRevFileList) {
	            if (revFile.getRefFProductRevId() == revInfo.getfProductRevId()) {
	                revFiles.add(revFile);
	            }
	        }	
	        foodProductRevSet.setFPPhotoRevFile(revFiles);
	        foodProductRevSetList.add(foodProductRevSet);	        
	    }
	    return foodProductRevSetList;
	}	
	
	public Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception{
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("/resources");
	    System.out.println("root 경로: " + root); // root 경로 출력
		// 파일 저장 경로 구하기
		String savePath = root + "\\fuploadFiles";
	    System.out.println("파일 저장 경로: " + savePath); // 파일 저장 경로 출력
		// 파일 이름 구하기
		String fileName = uploadFile.getOriginalFilename();
	    System.out.println("파일 이름: " + fileName); // 파일 이름 출력
		// 파일 확장자 구하기
		String extension = fileName.substring(fileName.lastIndexOf(".")+1);
	    System.out.println("파일 확장자: " + extension); // 파일 확장자 출력
		// 시간으로 파일 리네임하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis()))+ getRandomString()+"."+extension;
	    System.out.println("리네임된 파일 이름: " + fileRename); // 리네임된 파일 이름 출력
		// 파일 저장 전 폴더 만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
			System.out.println("폴더가 존재하지 않아 생성됨");
		}
		// 파일 저장
		File saveFile = new File(savePath+"\\"+fileRename);
		uploadFile.transferTo(saveFile);
		// 파일 정보 리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/fuploadFiles/"+fileRename);
		return fileMap;
	}
	
	private String getRandomString() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder randomString = new StringBuilder();
	    int length = 5; // 원하는 길이로 변경
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        randomString.append(characters.charAt(random.nextInt(characters.length())));
	    }
	    return randomString.toString();
	}
	
	public PageInfo getPageInfo(Integer currentPage, Integer totalCount,int recordCountPerPage) {
		// 한페이지당 네비갯수
		int naviCountPerPage = 5;
		// 네비 전체 수
		int naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		// 시작 네비
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		// 끝 네비
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		
		return pInfo;
	}	
	
	
	
	
	
}


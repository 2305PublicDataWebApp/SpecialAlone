package com.alone.special.diner.controller;

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

import com.alone.special.diner.domain.Diner;
import com.alone.special.diner.domain.DinerFile;
import com.alone.special.diner.domain.DinerRev;
import com.alone.special.diner.domain.DinerSet;
import com.alone.special.diner.service.DinerService;
import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductFile;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.FoodProductRevSet;
import com.alone.special.foodProduct.domain.FoodProductSet;
import com.alone.special.foodProduct.domain.PageInfo;



@Controller
public class DinerController {
	
	@Autowired
	private DinerService FDService;
	
	// 추천식당 정보등록 페이지 이동
	@RequestMapping(value="/diner/register.do", method=RequestMethod.GET)
	public ModelAndView showRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/diner/dinerReg");
		return mv;
	}	

	// 추천식당 메뉴파일등록 페이지 이동
	@RequestMapping(value="/diner/menufileregister.do", method=RequestMethod.GET)
	public ModelAndView showMenuRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/diner/dinerMenuFileReg");
		return mv;
	}	
	
	// 추천식당 메뉴파일등록 페이지 이동
	@RequestMapping(value="/diner/infofileregister.do", method=RequestMethod.GET)
	public ModelAndView showInfoFileRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/diner/dinerInfoFileReg");
		return mv;
	}	

	// 리뷰 등록 페이지 이동
	@RequestMapping(value="/foodProduct/showrevform.do", method=RequestMethod.GET)
	public ModelAndView showRevRegisterForm(ModelAndView mv
			,@RequestParam("fDinerId") int fDinerId) {
		Diner diner = FDService.selectDetailInfoByFDinerId(fDinerId);
		mv.addObject("diner", diner);
		mv.setViewName("food/diner/dinerRevReg");
		return mv;
	}		
	
	
	
	
	
	// 추천식당 등록
	@RequestMapping(value="/diner/register.do", method=RequestMethod.POST)
	public ModelAndView productInfoRegister(ModelAndView mv
			,@ModelAttribute Diner diner			
			) {
		int result = FDService.insertDinerInfo(diner);
		try {
			if(result>0) {
				//성공
				mv.setViewName("redirect:/diner/menufileregister.do");
			}else {
				//실패
				mv.addObject("msg", "추천식당 정보 등록이 완료되지 않았습니다");
				mv.addObject("error", "추천식당 정보 등록 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// 에러처리
			mv.addObject("msg", "추천식당 정보 등록이 완료되지 않았습니다");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/diner/register.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 추천식당 메뉴파일등록
	@RequestMapping(value="/diner/menufileregister.do", method=RequestMethod.POST)
	public ModelAndView dinerMenuFileRegister(ModelAndView mv
			,@RequestParam(value="imagename1", required=false) MultipartFile image1
			,@RequestParam(value="imagename2", required=false) MultipartFile image2
			,@RequestParam(value="imagename3", required=false) MultipartFile image3
			,@RequestParam(value="imagename4", required=false) MultipartFile image4
			,HttpServletRequest request
			) {
		try {
			int refFDinerId = FDService.getCurrentDinerId();
			// 연관아이디 가져오기
			// 이미지 3개가 값이 들어갔으면
	        List<DinerFile> dList = new ArrayList<>();
			if(image1 != null && !image1.getOriginalFilename().equals("")
					&& image2 != null && !image2.getOriginalFilename().equals("")
					&& image3 != null && !image3.getOriginalFilename().equals("")) {
				Map<String,Object> dMap1 = this.saveFile(request, image1);
				Map<String,Object> dMap2 = this.saveFile(request, image2);
				Map<String,Object> dMap3 = this.saveFile(request, image3);
				Map<String,Object> dMap4 = this.saveFile(request, image4);
				
				for (int i = 1; i <= 4; i++) {
				    DinerFile fDinerFile = new DinerFile();
				    fDinerFile.setRefFDinerId(refFDinerId); // 연관 상품 ID 설정
				    fDinerFile.setfDinerFileorder(i); // 파일 순서 설정
				    fDinerFile.setfDinerFiletype(1); // 1번 > 메뉴이미지
				    
				    // 각 이미지에 따라 파일 정보 설정
				    if (i == 1) {
				    	fDinerFile.setfDinerFilename((String) dMap1.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap1.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap1.get("filePath"));
				    } else if (i == 2) {
				    	fDinerFile.setfDinerFilename((String) dMap2.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap2.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap2.get("filePath"));
				    } else if (i == 3) {
				    	fDinerFile.setfDinerFilename((String) dMap3.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap3.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap3.get("filePath"));
				    } else if (i == 4) {
				    	fDinerFile.setfDinerFilename((String) dMap4.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap4.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap4.get("filePath"));
				    }
				    
				    dList.add(fDinerFile);
				}
			}
	        int result = FDService.insertDinerFiles(dList);
			
			if(result == 4) {
				// 리스트로 이동 해야함
				mv.setViewName("redirect:/diner/infofileregister.do");
			}else {
				// 에러페이지
				mv.addObject("msg", "식당 메뉴이미지등록이 완료되지 않았습니다");
				mv.addObject("error", "식당 메뉴이미지등록 실패");
				mv.addObject("url", "/diner/register.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "식당 메뉴이미지등록 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/diner/register.do");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}	

	// 추천식당 식당이미지 파일등록
	@RequestMapping(value="/diner/infofileregister.do", method=RequestMethod.POST)
	public ModelAndView dinerInfoFileRegister(ModelAndView mv
			,@RequestParam(value="imagename1", required=false) MultipartFile image1
			,@RequestParam(value="imagename2", required=false) MultipartFile image2
			,@RequestParam(value="imagename3", required=false) MultipartFile image3
			,HttpServletRequest request
			) {
		try {
			int refFDinerId = FDService.getCurrentDinerId();
			// 연관아이디 가져오기
			// 이미지 3개가 값이 들어갔으면
	        List<DinerFile> dList = new ArrayList<>();
			if(image1 != null && !image1.getOriginalFilename().equals("")
					&& image2 != null && !image2.getOriginalFilename().equals("")
					&& image3 != null && !image3.getOriginalFilename().equals("")) {
				Map<String,Object> dMap1 = this.saveFile(request, image1);
				Map<String,Object> dMap2 = this.saveFile(request, image2);
				Map<String,Object> dMap3 = this.saveFile(request, image3);
				
				for (int i = 1; i <= 3; i++) {
				    DinerFile fDinerFile = new DinerFile();
				    fDinerFile.setRefFDinerId(refFDinerId); // 연관 상품 ID 설정
				    fDinerFile.setfDinerFileorder(i); // 파일 순서 설정
				    fDinerFile.setfDinerFiletype(2); // 2번 > 식당이미지
				    
				    // 각 이미지에 따라 파일 정보 설정
				    if (i == 1) {
				    	fDinerFile.setfDinerFilename((String) dMap1.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap1.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap1.get("filePath"));
				    } else if (i == 2) {
				    	fDinerFile.setfDinerFilename((String) dMap2.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap2.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap2.get("filePath"));
				    } else if (i == 3) {
				    	fDinerFile.setfDinerFilename((String) dMap3.get("fileName"));
				    	fDinerFile.setfDinerFilerename((String) dMap3.get("fileRename"));
				    	fDinerFile.setfDinerFilepath((String) dMap3.get("filePath"));
				    }				    
				    dList.add(fDinerFile);
				}
			}
	        int result = FDService.insertDinerFiles(dList);
			
			if(result == 3) {
				// 리스트로 이동 해야함
				mv.setViewName("redirect:/diner/list.do");
			}else {
				// 에러페이지
				mv.addObject("msg", "식당 정보이미지등록이 완료되지 않았습니다");
				mv.addObject("error", "식당 정보이미지등록 실패");
				mv.addObject("url", "/diner/register.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "식당 정보이미지등록 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/diner/register.do");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}		

	// 리뷰 정보등록
	@RequestMapping(value="/diner/revInfoReg.do", method=RequestMethod.POST)
	public ModelAndView revInfoRegister(ModelAndView mv
			,@ModelAttribute DinerRev dinerRev
			,@RequestParam(value="fdinerId") int fdinerId
			,HttpSession session
			,HttpServletRequest request) {
		String fUserId = (String)session.getAttribute("userId");
		dinerRev.setRefFDinerId(fdinerId);
		dinerRev.setfUserId(fUserId);
		int result = FDService.insertRevInfo(dinerRev);
		try {
			if(result>0) {
				//성공
				mv.addObject("msg", "리뷰 정보등록 성공");
			}else {
				//실패
				mv.addObject("msg", "리뷰 정보등록이 완료되지 않았습니다");
				mv.addObject("error", "리뷰 정보 등록 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// 에러처리
			mv.addObject("msg", "리뷰 정보등록 에러");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/diner/showphotorevform.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}		
	
	// 리뷰 파일등록
//	@RequestMapping(value="/diner/revInfoReg.do", method=RequestMethod.POST)
//	public ModelAndView photoRevFileRegister(ModelAndView mv
//			,@ModelAttribute Diner diner
//			,@RequestParam(value="revImageName1", required=false) MultipartFile image1
//			,@RequestParam(value="revImageName2", required=false) MultipartFile image2
//			,HttpServletRequest request
//			,HttpSession session) {
//		try {
//			int refFDinerId = FDService.getCurrentFDinerRevId();
//			// 이미지 2개가 값이 들어갔으면
//	        List<FoodProductFile> fList = new ArrayList<>();
//			if(image1 != null && !image1.getOriginalFilename().equals("")
//					&& image2 != null && !image2.getOriginalFilename().equals("")
//					) {
//				Map<String,Object> fMap1 = this.saveFile(request, image1);
//				Map<String,Object> fMap2 = this.saveFile(request, image2);				
//				for (int i = 1; i <= 2; i++) {
//				    FoodProductFile fProductFile = new FoodProductFile();
//				    fProductFile.setRefFProductId(refFProductRevId); // 연관 상품 ID 설정
//				    fProductFile.setfProductFileorder(i); // 파일 순서 설정
//				    
//				    // 각 이미지에 따라 파일 정보 설정
//				    if (i == 1) {
//				        fProductFile.setfProductFilename((String) fMap1.get("fileName"));
//				        fProductFile.setfProductFilerename((String) fMap1.get("fileRename"));
//				        fProductFile.setfProductFilepath((String) fMap1.get("filePath"));
//				    } else if (i == 2) {
//				        fProductFile.setfProductFilename((String) fMap2.get("fileName"));
//				        fProductFile.setfProductFilerename((String) fMap2.get("fileRename"));
//				        fProductFile.setfProductFilepath((String) fMap2.get("filePath"));
//				    }				    
//				    fList.add(fProductFile);
//				}
//			}
//	        int result = FPService.insertPhotoRevFiles(fList);
//			
//			if(result == 2) {
//				// 리스트로 이동 해야함
//				mv.setViewName("redirect:/foodProduct/photorevlist.do");
//			}else {
//				// 에러페이지
//				mv.addObject("msg", "파일등록이 완료되지 않았습니다");
//				mv.addObject("error", "게시글 등록 실패");
//				mv.addObject("url", "/foodProduct/showphotorevform.do");
//				mv.setViewName("common/errorPage");
//			}
//		} catch (Exception e) {
//			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다");
//			mv.addObject("error", e.getMessage());
//			mv.addObject("url", "/foodProduct/showphotorevform.do");
//			mv.setViewName("common/errorPage");
//		}
//		
//		return mv;
//	}		
	
	
	
	
	
	
	// 추천식당 목록 조회
	@RequestMapping(value = "/diner/list.do", method = RequestMethod.GET)
	public ModelAndView showDinerList(
	        ModelAndView mv,
	        @RequestParam(value = "region", required = false) String region,
	        @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
	    try {
	        Integer totalCount;
	        if (region != null) {
	        	totalCount = FDService.getListCountByRegion(region);
	        }else {
	        	totalCount = FDService.getListCount();
	        }
	        PageInfo pInfo = this.getPageInfo(currentPage, totalCount,5);
	        
	        // 카테고리가 지정되지 않았을 때 전체 목록을 가져옵니다.
	        List<Diner> dInfoList;
	        List<DinerFile> dFileList;
	        
	        // 파일전체리스트 불러오기
	        dFileList = FDService.selectDinerFileList();
	        System.out.println(dFileList);
	        if (region == null) {
	        	// 전체 식당정보 불러오기
	            dInfoList = FDService.selectDinerInfoList(pInfo);
	        } else {
	            // 지역에 해당하는 식당 목록을 가져옵니다.
	            dInfoList = FDService.selectDinerInfoListByRegion(region, pInfo);
	        }
	        System.out.println(dInfoList);
	        // 상품 세트 목록을 생성합니다.
	        List<DinerSet> dinerSetList = createDinerSets(dInfoList, dFileList);
	        System.out.println(dinerSetList);
	        mv.addObject("dinerSetList", dinerSetList);
	        mv.addObject("pInfo", pInfo);
	        mv.setViewName("food/diner/dinerList");
	    } catch (Exception e) {
	        // 예외 처리 로직 추가
	        mv.addObject("msg", "식당목록 불러오기 실패");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "food/diner/dinerList");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
	    }
	    return mv;
	}
	

	// 상품 상세정보 조회
	@RequestMapping(value="/diner/dinerDetail.do", method=RequestMethod.GET)
	public ModelAndView showDinerDetail(ModelAndView mv
			,@RequestParam("fDinerId") Integer fDinerId
			,@RequestParam("refFDinerId") Integer refFDinerId) {	
		try {
			Diner Diner = FDService.selectDetailInfoByFDinerId(fDinerId);
			List<DinerFile> dFileList = FDService.selectDetailFileByRefFDinerId(refFDinerId);
			if(Diner !=null && dFileList !=null) {
				mv.addObject("Diner", Diner);
				mv.addObject("dFileList", dFileList);
				mv.setViewName("food/diner/dinerDetail");
			}else {
				mv.addObject("msg", "추천상품 상세조회가 완료되지 않았습니다");
				mv.addObject("error", "추천상품 상세조회 실패");
				mv.addObject("url", "");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "추천상품 상세조회 에러");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/diner/list.do");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
		}
		return mv;
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 상품 세트 목록을 생성하는 메서드
	private List<DinerSet> createDinerSets(List<Diner> dInfoList, List<DinerFile> dFileList) {
	    List<DinerSet> dinerSetList = new ArrayList<>();
	    for (Diner diner : dInfoList) {
	    	DinerSet dinerSet = new DinerSet();
	    	dinerSet.setDiner(diner);
	
	        // 대표 이미지를 찾기 위한 루프
	        for (DinerFile file : dFileList) {
	            if (file.getRefFDinerId() == diner.getfDinerId() && file.getfDinerFileorder() == 1
	            		&&file.getfDinerFiletype() ==2) {
	                // 대표 이미지를 찾았을 때, 해당 파일을 세트에 추가합니다.
	               dinerSet.setDinerFile(file);
	                break; // 대표 이미지를 찾았으므로 더 이상 루프를 돌 필요가 없습니다.
	            }
	        }
	        // 세트를 목록에 추가합니다.
	        dinerSetList.add(dinerSet);
	    }
	    return dinerSetList;
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
}
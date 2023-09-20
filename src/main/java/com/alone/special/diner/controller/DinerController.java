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
import com.alone.special.diner.domain.DinerRevFile;
import com.alone.special.diner.domain.DinerRevSet;
import com.alone.special.diner.domain.DinerSet;
import com.alone.special.diner.service.DinerService;
import com.alone.special.foodProduct.domain.FoodProduct;
import com.alone.special.foodProduct.domain.FoodProductOneRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRev;
import com.alone.special.foodProduct.domain.FoodProductPhotoRevFile;
import com.alone.special.foodProduct.domain.FoodProductRevSet;
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
	
	// 추천식당 메뉴정보등록 페이지 이동
	@RequestMapping(value="/diner/infofileregister.do", method=RequestMethod.GET)
	public ModelAndView showInfoFileRegisterForm(ModelAndView mv) {		
		mv.setViewName("food/diner/dinerInfoFileReg");
		return mv;
	}	

	// 리뷰 정보 등록 페이지 이동
	@RequestMapping(value="/diner/showRevInfoRegForm.do", method=RequestMethod.GET)
	public ModelAndView showRevInfoRegisterForm(ModelAndView mv
			,@RequestParam("fDinerId") int fDinerId) {
		Diner diner = FDService.selectDetailInfoByFDinerId(fDinerId);	
		mv.addObject("diner", diner);
		mv.setViewName("food/diner/dinerRevInfoReg");
		return mv;
	}		

	// 리뷰 파일 등록 페이지 이동
	@RequestMapping(value="/diner/showRevFileRegForm.do", method=RequestMethod.GET)
	public ModelAndView showRevFileRegisterForm(ModelAndView mv
			,@RequestParam("fDinerId") int fDinerId) {
		Diner diner = FDService.selectDetailInfoByFDinerId(fDinerId);	
		mv.addObject("diner", diner);
		mv.setViewName("food/diner/dinerRevFileReg");
		return mv;
	}		
	
	// 추천식당 등록
	@RequestMapping(value="/diner/register.do", method=RequestMethod.POST)
	public ModelAndView dinerInfoRegister(ModelAndView mv
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
			,@RequestParam(value="fDinerId") int fDinerId
			,HttpSession session
			,HttpServletRequest request) {
		String fUserId = (String)session.getAttribute("userId");
		dinerRev.setRefFDinerId(fDinerId);
		dinerRev.setfUserId(fUserId);
		int result = FDService.insertRevInfo(dinerRev);
		try {
			if(result>0) {
				//성공
				mv.addObject("fDinerId", fDinerId);
				mv.setViewName("redirect:/diner/showRevFileRegForm.do");
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
			mv.addObject("url", "/diner/list.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}		
	
	// 리뷰 파일등록
	@RequestMapping(value="/diner/revFileReg.do", method=RequestMethod.POST)
	public ModelAndView revFileRegister(ModelAndView mv
			,@ModelAttribute Diner diner
			,@RequestParam(value="revImageName1", required=false) MultipartFile image1
			,@RequestParam(value="revImageName2", required=false) MultipartFile image2
			,HttpServletRequest request
			,HttpSession session) {
		try {
			int refFDinerId = FDService.getCurrentFDinerRevId();
			// 이미지 2개가 값이 들어갔으면
	        List<DinerRevFile> dRevList = new ArrayList<>();
			if(image1 != null && !image1.getOriginalFilename().equals("")
					&& image2 != null && !image2.getOriginalFilename().equals("")
					) {
				Map<String,Object> dRevMap1 = this.saveFile(request, image1);
				Map<String,Object> dRevMap2 = this.saveFile(request, image2);				
				for (int i = 1; i <= 2; i++) {
				    DinerRevFile dRevFile = new DinerRevFile();
				    dRevFile.setRefFDinerId(refFDinerId);; // 연관 상품 ID 설정
				    dRevFile.setfDinerRevFileorder(i);; // 파일 순서 설정
				    
				    // 각 이미지에 따라 파일 정보 설정
				    if (i == 1) {
				    	dRevFile.setfDinerRevFilename((String) dRevMap1.get("fileName"));
				    	dRevFile.setfDinerRevFilerename((String) dRevMap1.get("fileRename"));
				    	dRevFile.setfDinerRevFilepath((String) dRevMap1.get("filePath"));
				    } else if (i == 2) {
				    	dRevFile.setfDinerRevFilename((String) dRevMap2.get("fileName"));
				    	dRevFile.setfDinerRevFilerename((String) dRevMap2.get("fileRename"));
				    	dRevFile.setfDinerRevFilepath((String) dRevMap2.get("filePath"));
				    }				    
				    dRevList.add(dRevFile);
				}
			}
			System.out.println(dRevList);
	        int result = FDService.insertRevFiles(dRevList);
			
			if(result == 2) {
				// 리스트로 이동 해야함
				mv.setViewName("redirect:/diner/list.do");
			}else {
				// 에러페이지
				mv.addObject("msg", "리뷰파일 등록이 완료되지 않았습니다");
				mv.addObject("error", "리뷰 파일 등록 실패");
				mv.addObject("url", "/diner/list.do");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.addObject("error", e.getMessage());
			
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}		
	
	
	
	
	
	
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
	

	// 추천식당 리뷰목록 조회
	@RequestMapping(value = "/diner/revList.do", method = RequestMethod.GET)
	public ModelAndView showDinerRevList(
	        ModelAndView mv,
	        @RequestParam(value = "fDinerId") int fDinerId
	        ,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
	    try {
	    	// fDinerId 쿼리스트링으로 받아옴
			Diner diner = FDService.selectDetailInfoByFDinerId(fDinerId);
			mv.addObject("diner", diner);	    	
	        Integer totalCount;
	        totalCount = FDService.getRevListCount();
	        PageInfo pInfo = this.getPageInfo(currentPage, totalCount,5);	        
	        List<DinerRev> dRevList;
	        List<DinerRevFile> dRevFileList;
	        dRevList = FDService.selectRevListByFdinerId(fDinerId,pInfo);	        	        
	        dRevFileList = FDService.selectRevFileList();
	        System.out.println(dRevFileList);
	        // 리뷰 세트 목록을 생성합니다.
	        List<DinerRevSet> dinerRevSet = createDinerRevSets(dRevList, dRevFileList);
	        mv.addObject("dinerRevSet", dinerRevSet);
	        mv.addObject("pInfo", pInfo);
	        mv.setViewName("food/diner/dinerRevList");
	    } catch (Exception e) {
	        // 예외 처리 로직 추가
	        mv.addObject("msg", e.getMessage());
	        
	        mv.addObject("url", "/diner/list.do");
	        mv.setViewName("common/errorPage"); // 에러 페이지로 리다이렉트 또는 예외 처리
	    }
	    return mv;
	}	
	
	
	//식당 리뷰 삭제
	@RequestMapping(value="/diner/deleteDinerRev.do", method=RequestMethod.GET)
	public ModelAndView revDelete(ModelAndView mv
			,@RequestParam("fDinerRevId") int fDinerRevId
			,@RequestParam("refFDinerId") int refFDinerId			
			,HttpSession session) {
		try {
				DinerRev dinerRev = new DinerRev();
				dinerRev.setfDinerRevId(fDinerRevId);
				dinerRev.setRefFDinerId(refFDinerId);
				int result =FDService.deleteRev(dinerRev);		
				if(result>0) {
					mv.setViewName("redirect:/diner/revList.do?fDinerId=" + refFDinerId);					
				}else {
					mv.addObject("msg", "리뷰삭제가 완료되지 않았습니다");
					mv.addObject("url", "redirect:/index.jsp");
					mv.setViewName("common/errorPage");					
				}			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "redirect:/index.jsp");
			mv.setViewName("common/errorPage");
		}	
		return mv;
	}

	// 상품 상세정보 조회
	@RequestMapping(value="/diner/dinerDetail.do", method=RequestMethod.GET)
	public ModelAndView showDinerDetail(ModelAndView mv
			,@RequestParam("fDinerId") Integer fDinerId
			,@RequestParam("refFDinerId") Integer refFDinerId) {	
		try {
			Diner diner = FDService.selectDetailInfoByFDinerId(fDinerId);
			List<DinerFile> dFileList = FDService.selectDetailFileByRefFDinerId(refFDinerId);
			if(diner !=null && dFileList !=null) {
				mv.addObject("diner", diner);
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
	
	
	
	// 추천식당 삭제(연관 파일,리뷰 모두삭제)
	@RequestMapping(value="/diner/deleteDiner.do", method=RequestMethod.GET)
	public ModelAndView deleteDiner(ModelAndView mv
			,@RequestParam("fDinerId") int fDinerId) {
		try {
			int result = FDService.deleteDiner(fDinerId);
				if(result>0) {
					mv.setViewName("redirect:/diner/list.do");
				}else {
					mv.addObject("msg", "추천상품 삭제가 완료되지 않았습니다");
					mv.addObject("url", "/diner/list.do");
					mv.setViewName("common/errorPage");
				}			
			} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.addObject("url", "/diner/list.do");
			mv.setViewName("common/errorPage");			
		}		
		return mv;
	}	
	
	
	// 식당 전체 목록 조회를 위한 정보+이미지 세트 생성
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
	
	// 리뷰 조회를 위한 정보+이미지 세트생성 
	private List<DinerRevSet> createDinerRevSets(List<DinerRev> dRevList, List<DinerRevFile> dRevFileList) {
	    List<DinerRevSet> dinerRevSetList = new ArrayList<>();
	    for (DinerRev dinerRev : dRevList) {
	    	DinerRevSet dinerRevSet = new DinerRevSet();
	    	dinerRevSet.setDinerRev(dinerRev);	
	        List<DinerRevFile> dRevFiles = new ArrayList<>();
	        for(DinerRevFile dRevFile : dRevFileList) {
	        	if(dRevFile.getRefFDinerId() == dinerRev.getfDinerRevId()) {
	        		dRevFiles.add(dRevFile);
	        	}
	        }
	    	dinerRevSet.setdRevFileList(dRevFiles);
	    	dinerRevSetList.add(dinerRevSet);
	   
	    }
	    return dinerRevSetList;
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

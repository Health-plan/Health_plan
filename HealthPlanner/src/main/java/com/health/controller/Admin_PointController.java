package com.health.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.MemberDTO;
import com.health.domain.PointPoliceDTO;
import com.health.mapper.Admin_PointMapper;
import com.health.service.Admin_PointService;

//Admin_Point Page
@Controller
public class Admin_PointController {

	@Autowired
	private Admin_PointService adminPointService;

	// List
	@GetMapping(value = "admin_point.do")
	public String adminPoint(@ModelAttribute("params") MbrPointRecordDTO params, Model model) {
		List<MbrPointRecordDTO> list = adminPointService.getPointList(params);
		model.addAttribute("list", list);

		return "Admin_Point";
	}

	@Autowired
	private Admin_PointMapper adminPointMapper;

	@GetMapping(value = "admin_pointDetail.do")
	public String adminPointUpdate(@RequestParam(value = "mbrId", required = false) String mbrId,
			@RequestParam(value = "mbrNm", required = false) String mbrNm,
			@ModelAttribute("params") MbrPointRecordDTO params, PointPoliceDTO pointPoliceDTO, Model model) {
		// 조회작업
		params.setMbrId(mbrId);
		MemberDTO member = new MemberDTO();
		member.setMbrNm(mbrNm);
		params.setMemberDTO(member);

		List<MbrPointRecordDTO> list = adminPointService.getPointDetailList(params);

		model.addAttribute("mbrId", mbrId);
		model.addAttribute("mbrNm", mbrNm);
		model.addAttribute("list", list);

		// 현재 포인트 값
		int totalPoint = adminPointMapper.selectPointTotal(params);
		model.addAttribute("point", totalPoint);

		// 포인트정책
		pointPoliceDTO.setRecordsPerPage(99); // 포인트 목록이 99개가 나올 일이 없다 생각하여 임의 지정
		List<PointPoliceDTO> police = adminPointService.getPointPoliceList(pointPoliceDTO);
		model.addAttribute("police", police);

		return "Admin_PointDetail";
	}

	@GetMapping(value = "admin_insertPointRecord.do")
	public String adminInsertPointRecord(MbrPointRecordDTO params, @RequestParam(value = "mbrNm") String mbrNm) {
		// 한글깨짐처리
		try {
			mbrNm = URLEncoder.encode(mbrNm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 관리자권한 포인트기록 추가
		if (params.getMbrId() == null || params.getPointId() == 0) {
			return "redirect:admin_point.do";
		}
		try {
			boolean isInserted = adminPointService.addPointRecord(params);
			if (isInserted == false) {
				System.out.println("메소드 오류");
				return "redirect:admin_point.do";
			}
		} catch (DataAccessException e) {
			System.out.println("디비오류" + e);
		} catch (Exception e) {
			System.out.println("시스템오류" + e);
		}

		return "redirect:admin_pointDetail.do?" + "mbrId=" + params.getMbrId() + "&mbrNm=" + mbrNm;
	}

	@GetMapping(value = "admin_pointPolice.do")
	public String adminPointPolice(@ModelAttribute("params") PointPoliceDTO params, Model model) {
		List<PointPoliceDTO> police = adminPointService.getPointPoliceList(params);
		model.addAttribute("police", police);

		return "Admin_PointPolice";
	}

	@GetMapping(value = "admin_updatePointPolice.do")
	public String adminUpdatePointPolice(PointPoliceDTO params, String currentPageNo, Model model) {

		if (params.getPointId() == 0) {
			System.out.println("식별자 없음");
			return "redirect:admin_pointPolice.do";
		}
		try {
			boolean isUpdated = adminPointService.modifyPointPolice(params);
			if (isUpdated == false) {
				System.out.println("서비스 오류");
				return "redirect:admin_pointPolice.do";
			}
		} catch (DataAccessException e) {
			System.out.println("DB오류" + e);
			return "redirect:admin_pointPolice.do";
		} catch (Exception e) {
			System.out.println("시스템 오류" + e);
			return "redirect:admin_pointPolice.do";
		}
		return "redirect:admin_pointPolice.do?CurrentPageNo=" + params.getCurrentPageNo();
	}

	@GetMapping(value = "admin_addPointPolice.do")
	public String adminAddPointPolice(PointPoliceDTO params, Model model) {
		try {
			boolean isAdded = adminPointService.addPointPolice(params);
			if (isAdded == false) {
				System.out.println("메소드 에러");
				return "redirect:admin_pointPolice.do";
			}
		} catch (DataAccessException e) {
			System.out.println("디비 에러 " + e);
			return "redirect:admin_pointPolice.do";
		} catch (Exception e) {
			System.out.println("시스템 에러 " + e);
			return "redirect:admin_pointPolice.do";
		}
		return "redirect:admin_pointPolice.do";
	}

}

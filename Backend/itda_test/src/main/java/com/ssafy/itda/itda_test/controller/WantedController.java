package com.ssafy.itda.itda_test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.itda.itda_test.help.CompanyResult;
import com.ssafy.itda.itda_test.help.Result;
import com.ssafy.itda.itda_test.help.WantedResult;
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Wanted;
import com.ssafy.itda.itda_test.service.IWantedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class WantedController {
	public static final Logger logger = LoggerFactory.getLogger(WantedController.class);

	@Autowired
	private IWantedService wantedService;

	@ApiOperation(value = " 공고 정보를 확인한다.", response = List.class)
	@RequestMapping(value = "/getWantedAll", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedAll() throws Exception {
		logger.info("5-------------getWantedAll-----------------------------" + new Date());
		List<Integer> widList = wantedService.getWantedAll();
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wrlist.add(wr);
		}
		return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
	}

	@ApiOperation(value = " 공고 정보를 확인한다.", response = WantedResult.class)
	@RequestMapping(value = "/getWantedByID/{wid}", method = RequestMethod.GET)
	public ResponseEntity<WantedResult> getWantedByID(@PathVariable int wid) throws Exception {
		logger.info("5-------------getWantedByID-----------------------------" + new Date());
		int cid = wantedService.getCompanyId(wid);
		Company company = wantedService.getCompanyInfo(cid);
		Wanted wanted = wantedService.getWantedInfo(wid);
		List<Job> jobs = wantedService.getJobsInfo(wid);
		WantedResult wr = new WantedResult();
		wr.setCompany(company);
		wr.setWanted(wanted);
		wr.setJobs(jobs);
		return new ResponseEntity<WantedResult>(wr, HttpStatus.OK);
	}

	@ApiOperation(value = "최신 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByRecent/", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByRecent() throws Exception {
		logger.info("5-------------getWantedByRecent-----------------------------" + new Date());
		List<Integer> widList = wantedService.getWantedByRecent();
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wrlist.add(wr);
		}
		return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
	}

	@ApiOperation(value = "마감순 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByCloseEnd/", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByCloseEnd() throws Exception {
		logger.info("5-------------getWantedByCloseEnd-----------------------------" + new Date());
		List<Integer> widList = wantedService.getWantedByCloseEnd();
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wrlist.add(wr);
		}
		return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
	}

	@ApiOperation(value = "조회순 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByView/", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByView() throws Exception {
		logger.info("5-------------getWantedByView-----------------------------" + new Date());
		List<Integer> widList = wantedService.getWantedByView();
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wrlist.add(wr);
		}
		return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자가 공고를 스크랩한다.", response = Result.class)
	@RequestMapping(value = "/scrapWanted/", method = RequestMethod.POST)
	public ResponseEntity<Result> scrapWanted(@RequestBody Scrap model) throws Exception {
		logger.info("5-------------scrapWanted-----------------------------" + new Date());
		Scrap scrap = wantedService.isScraped(model);
		Result r = new Result();
		if (scrap != null) {
			wantedService.unScrap(model);
			r.setMsg("스크랩 해지");
			r.setState("success");
			r.setScrap(false);
		} else {
			wantedService.scrap(model);
			r.setMsg("스크랩 성공");
			r.setState("success");
			r.setScrap(true);
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "공고를 추가한다.", response = Wanted.class)
	@RequestMapping(value = "/createWanted", method = RequestMethod.POST)
	public ResponseEntity<Result> createWanted(@RequestBody Wanted model) throws Exception {
		logger.info("6-------------createWanted-----------------------------" + new Date());
		logger.info("6-------------createWanted-----------------------------" + model);
		Result r = new Result();
		if (model.getWantedTitle() == null || model.getWantedTitle().equals("") 
		|| model.getStartDate() == null || model.getStartDate().equals("") || model.getEndDate() == null 
		|| model.getEndDate().equals("") || model.getProcess() == null || model.getProcess().equals("")) {
			r.setMsg("필수 입력값이 누락되었습니다.");
			r.setState("fail");
		}
		wantedService.createWanted(model);
		r.setMsg("공고 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공고를 삭제한다.", response = Wanted.class)
	@RequestMapping(value = "/deleteWanted", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteWanted(@RequestBody Integer wid) throws Exception {
		logger.info("7-------------deleteWanted-----------------------------" + new Date());
		logger.info("7-------------deleteWanted-----------------------------" + wid);
		Result r = new Result();
		Wanted wanted = wantedService.getWantedInfo(wid);
		if (wid == 0 || wanted == null) {
			r.setMsg("존재하지 않는 wid값입니다.");
			r.setState("fail");
		}
		wantedService.deleteWanted(wid);
		r.setMsg("공고 삭제가 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공고를 수정한다.", response = Wanted.class)
	@RequestMapping(value = "/updateWanted", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteWanted(@RequestBody Wanted model) throws Exception {
		logger.info("8-------------updateWanted-----------------------------" + new Date());
		logger.info("8-------------updateWanted-----------------------------" + model);
		Result r = new Result();
		int wid = model.getWid();
		Wanted wanted = wantedService.getWantedInfo(wid);
		if (wid == 0 || wanted == null) {
			r.setMsg("존재하지 않는 wid값입니다.");
			r.setState("fail");
		}
		else if (model.getWantedTitle() == null || model.getWantedTitle().equals("") 
				|| model.getStartDate() == null || model.getStartDate().equals("") || model.getEndDate() == null 
				|| model.getEndDate().equals("") || model.getProcess() == null || model.getProcess().equals("")) {
			r.setMsg("입력되지 않은 필수값이 있습니다.");
			r.setState("fail");
		}
		wantedService.updateWanted(model);
		r.setMsg("공고 수정이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}
}

package com.ssafy.itda.itda_test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.itda.itda_test.help.Result;
import com.ssafy.itda.itda_test.help.WantedResult;
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.JobStack;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.model.Wanted;
import com.ssafy.itda.itda_test.service.IJobService;
import com.ssafy.itda.itda_test.service.IStackService;
import com.ssafy.itda.itda_test.service.IWantedService;
import com.ssafy.itda.itda_test.service.JwtServiceImpl;

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

	@Autowired
	private IJobService jobService;

	@Autowired
	private IStackService stackService;
	
	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = " 공고 정보를 확인한다.", response = List.class)
	@RequestMapping(value = "/getWantedAll", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedAll(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedAll-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedAll();
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			List<Integer> widList = wantedService.getWantedAll();
			List<WantedResult> wrlist = getWantedListFunction(widList);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		}
	}

	@ApiOperation(value = " 공고 정보를 확인한다.", response = WantedResult.class)
	@RequestMapping(value = "/getWantedByID/{wid}", method = RequestMethod.GET)
	public ResponseEntity<WantedResult> getWantedByID(@PathVariable int wid, HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByID-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		WantedResult wr = new WantedResult();
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			int cid = wantedService.getCompanyId(wid);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(wid);
			List<Job> jobs = wantedService.getJobsInfo(wid);
			List<Stack> wantedStacks = wantedService.getWantedStackInfo(wid);
			for (Job j : jobs) {
				j.setStacks(wantedService.getStackInfo(j.getJid()));
			}
			Scrap model = new Scrap();
			model.setUid(uid);
			model.setWid(wid);
			Scrap scrap = wantedService.isScraped(model);
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wr.setStacks(wantedStacks);
			if (scrap == null) {
				wr.setScrap(false);
			} else {
				wr.setScrap(true);
			}
		} else {
			int cid = wantedService.getCompanyId(wid);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(wid);
			List<Job> jobs = wantedService.getJobsInfo(wid);
			List<Stack> wantedStacks = wantedService.getWantedStackInfo(wid);
			for (Job j : jobs) {
				j.setStacks(wantedService.getStackInfo(j.getJid()));
			}
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wr.setStacks(wantedStacks);
		}
		// vcnt 조회수 update
		wantedService.updateVcnt(wid);

		return new ResponseEntity<WantedResult>(wr, HttpStatus.OK);
	}

	@ApiOperation(value = "최신 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByRecent", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByRecent(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByRecent-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedByRecent();
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			List<Integer> widList = wantedService.getWantedByRecent();
			List<WantedResult> wrlist = getWantedListFunction(widList);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "마감순 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByCloseEnd", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByCloseEnd(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByCloseEnd-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedByCloseEnd();
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			List<Integer> widList = wantedService.getWantedByCloseEnd();
			List<WantedResult> wrlist = getWantedListFunction(widList);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "조회순 공고 목록을 요청 받아 응답한다.", response = List.class)
	@RequestMapping(value = "/getWantedByView", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByView(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByView-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedByView();
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			List<Integer> widList = wantedService.getWantedByView();
			List<WantedResult> wrlist = getWantedListFunction(widList);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "내 기술스택에 맞는 공고 정보를 확인한다.", response = List.class)
	@RequestMapping(value = "/getWantedByStack", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByStack(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByStack-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedByStack(uid);
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "내 스크랩 공고에 맞는 공고 정보를 확인한다.", response = List.class)
	@RequestMapping(value = "/getWantedByScrap", method = RequestMethod.GET)
	public ResponseEntity<List<WantedResult>> getWantedByScrap(HttpServletRequest req) throws Exception {
		logger.info("5-------------getWantedByScrap-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int uid = (int) resultMap.get("uid");
			List<Integer> widList = wantedService.getWantedByScrap(uid);
			List<WantedResult> wrlist = getWantedListFunction(widList, uid);
			return new ResponseEntity<List<WantedResult>>(wrlist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "사용자가 공고를 스크랩한다.", response = Result.class)
	@RequestMapping(value = "/scrapWanted", method = RequestMethod.POST)
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
		// Wanted Input Condition
		if (model.getCid() == 0 || model.getWantedTitle() == null || model.getWantedTitle().equals("")
				|| model.getStartDate() == null || model.getStartDate().equals("") || model.getEndDate() == null
				|| model.getEndDate().equals("") || model.getProcess() == null || model.getProcess().equals("")) {
			r.setMsg("공고 필수 입력값이 누락되었습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		// Job Input Condition
		for (Job j : model.getJobs()) {
			if (j.getTo() == null || j.getTo().equals("") || j.getJname() == null || j.getJname().equals("")) {
				r.setMsg("직무 필수 입력값이 누락되었습니다.");
				r.setState("fail");
				return new ResponseEntity<Result>(r, HttpStatus.OK);
			}
		}

		int wid = wantedService.createWanted(model);
		for (Job j : model.getJobs()) {
			if (j.getTo().contains("명")) {
				j.setTo(j.getTo().substring(0, j.getTo().length() - 1));
			}
			j.setWid(wid);
			int jid = jobService.createJobReturnJid(j);
			for(Stack s : j.getStacks()) {
				JobStack js = new JobStack();
				js.setJid(jid);
				js.setSid(s.getSid());
				stackService.createJobStack(js);
			}
		}

		r.setMsg("공고 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "공고를 삭제한다.", response = Wanted.class)
	@RequestMapping(value = "/deleteWanted", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteWanted(@RequestBody int wid) throws Exception {
		logger.info("7-------------deleteWanted-----------------------------" + new Date());
		logger.info("7-------------deleteWanted-----------------------------" + wid);
		Result r = new Result();
		Wanted wanted = wantedService.getWantedInfo(wid);
		if (wid == 0 || wanted == null) {
			r.setMsg("존재하지 않는 wid값입니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		wantedService.deleteWanted(wid);
		r.setMsg("공고 삭제가 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "공고를 수정한다.", response = Wanted.class)
	@RequestMapping(value = "/updateWanted", method = RequestMethod.PUT)
	public ResponseEntity<Result> deleteWanted(@RequestBody Wanted model) throws Exception {
		logger.info("8-------------updateWanted-----------------------------" + new Date());
		logger.info("8-------------updateWanted-----------------------------" + model);
		Result r = new Result();
		int wid = model.getWid();
		Wanted wanted = wantedService.getWantedInfo(wid);
		if (wid == 0 || wanted == null) {
			r.setMsg("존재하지 않는 wid값입니다.");
			r.setState("fail");
		} else if (model.getCid() == 0 || model.getWantedTitle() == null || model.getWantedTitle().equals("")
				|| model.getStartDate() == null || model.getStartDate().equals("") || model.getEndDate() == null
				|| model.getEndDate().equals("") || model.getProcess() == null || model.getProcess().equals("")) {
			r.setMsg("입력되지 않은 필수값이 있습니다.");
			r.setState("fail");
		} else {
			wantedService.updateWanted(model);
			r.setMsg("공고 수정이 성공적으로 완료되었습니다.");
			r.setState("success");
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	private List<WantedResult> getWantedListFunction(List<Integer> widList, int uid) {
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			List<Stack> wantedStacks = wantedService.getWantedStackInfo(i);
			for (Job j : jobs) {
				j.setStacks(wantedService.getStackInfo(j.getJid()));
			}
			Scrap model = new Scrap();
			model.setUid(uid);
			model.setWid(i);
			Scrap scrap = wantedService.isScraped(model);
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wr.setStacks(wantedStacks);
			if (scrap == null) {
				wr.setScrap(false);
			} else {
				wr.setScrap(true);
			}
			wrlist.add(wr);
		}
		return wrlist;
	}

	private List<WantedResult> getWantedListFunction(List<Integer> widList) {
		List<WantedResult> wrlist = new ArrayList<>();
		for (int i : widList) {
			int cid = wantedService.getCompanyId(i);
			Company company = wantedService.getCompanyInfo(cid);
			Wanted wanted = wantedService.getWantedInfo(i);
			List<Job> jobs = wantedService.getJobsInfo(i);
			List<Stack> wantedStacks = wantedService.getWantedStackInfo(i);
			for (Job j : jobs) {
				j.setStacks(wantedService.getStackInfo(j.getJid()));
			}
			WantedResult wr = new WantedResult();
			wr.setCompany(company);
			wr.setWanted(wanted);
			wr.setJobs(jobs);
			wr.setStacks(wantedStacks);
			wrlist.add(wr);
		}
		return wrlist;
	}
}

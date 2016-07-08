package com.tust.lamp.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.tust.lamp.entity.Bespeak;
import com.tust.lamp.entity.Device;
import com.tust.lamp.entity.User;
import com.tust.lamp.orm.Page;
import com.tust.lamp.service.BespeakService;
import com.tust.lamp.utils.DataUtils;

@Controller
@RequestMapping("/bespeak")
public class BespeakHandler {
	
	@Autowired
	private BespeakService bespeakService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, 
			Map<String, Object> map, 
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = DataUtils.encodeParamsToQueryString(params);
		request.setAttribute("queryString", queryString);
		User user = (User) request.getSession().getAttribute("user");
		if (user.getRole().getId() == 2) {
			params.put("EQO_createBy", user);
		}
		Page<Bespeak> page = bespeakService.getPage(pageNo, params);
		request.setAttribute("page", page);
		return "bespeak/list";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("deviceId") Integer deviceId, 
			@RequestParam("bespeakDate") Date bespeakDate, 
			@RequestParam("stage") Integer stage, 
			HttpServletRequest request, 
			RedirectAttributes attributes, 
			Locale locale) {
		Bespeak bespeak = new Bespeak();
		bespeak.setBespeakDate(bespeakDate);
		bespeak.setDate(new Date());
		bespeak.setStage(stage);
		Device device = new Device();
		device.setId(deviceId);
		bespeak.setDevice(device);
		User user = (User) request.getSession().getAttribute("user");
		bespeak.setUser(user);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String bespeakDateStr = format.format(bespeakDate);
		Bespeak bespeak2 = bespeakService.checking(bespeakDateStr, stage);
		if (bespeak2 != null) {
			String code = "error.bespeak.checking";
			String message = messageSource.getMessage(code, null, locale);
			attributes.addFlashAttribute("message", message);
			return "redirect:/device/list";
		}
		bespeakService.save(bespeak);
		return "redirect:/bespeak/list";
	}
	
}

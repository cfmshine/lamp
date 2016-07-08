package com.tust.lamp.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.tust.lamp.entity.Bespeak;
import com.tust.lamp.entity.Device;
import com.tust.lamp.entity.Maintenance;
import com.tust.lamp.orm.Page;
import com.tust.lamp.service.BespeakService;
import com.tust.lamp.service.DeviceService;
import com.tust.lamp.service.MaintenanceService;
import com.tust.lamp.utils.DataProcessUtils;
import com.tust.lamp.utils.DataUtils;

@Controller
@RequestMapping("/device")
public class DeviceHandler {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private BespeakService bespeakService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/detail/{id}")
	public String detail(Map<String, Object> map, @PathVariable("id") Integer id) {
		Device device = deviceService.getById(id);
		map.put("device", device);
		List<Bespeak> bespeaks = bespeakService.getByDeviceId(id);
		map.put("bespeaks", bespeaks);
		return "device/detail";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session, Device device, @RequestParam("img") MultipartFile img,
		RedirectAttributes attributes, Locale locale) throws IOException {
		//如果没有上传文件size为0，不进行检查
		long size = img.getSize();
		if(size > 0) {
			//检查文件类型
			//设置允许的类型
			Set<String> allowedTypes = new HashSet<String>();
			allowedTypes.add("image/jpg");
			allowedTypes.add("image/gif");
			allowedTypes.add("image/png");
			allowedTypes.add("image/bmp");
			allowedTypes.add("image/jpeg");
			
			//设置上传文件的类型
			String contentType = img.getContentType();
			
			//检查上传文件类型是否正确
			if(!allowedTypes.contains(contentType)) {
				//不正确返回表单页面
				String code = "error.device.img";
				String message = messageSource.getMessage(code, null, locale);
				attributes.addFlashAttribute("message", message);
				return "device/input";
			}
		}
		
		//保存图片
		//声明虚拟路径
		String virtualPath = "/images";
		//获取servletContext对象
		ServletContext servletContext = session.getServletContext();
		//将虚拟路径转换为真实路径
		String realPath = servletContext.getRealPath(virtualPath);
		//从上传文件中获取输入流对象
		InputStream inputStream = img.getInputStream();
		//压缩图片，返回logoPath
		String logoPath = DataProcessUtils.resizeImages(inputStream, realPath);
		
		//设置logoPath
		if(logoPath != null) {
			device.setImgPath(logoPath);
		}
		deviceService.update(device);
		return "redirect:/device/list";
	}
	
	@RequestMapping("/toEditUI/{id}")
	public String toEditUI(@PathVariable("id") Integer id, Map<String, Object> map) {
		Device device = deviceService.getById(id);
		map.put("device", device);
		List<Maintenance> maintenances = maintenanceService.getAll();
		map.put("maintenances", maintenances);
		return "device/input";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, Device device, @RequestParam("img") MultipartFile img,
			RedirectAttributes attributes, Locale locale) throws IOException {
		//如果没有上传文件size为0，不进行检查
		long size = img.getSize();
		if(size > 0) {
			//检查文件类型
			//设置允许的类型
			Set<String> allowedTypes = new HashSet<String>();
			allowedTypes.add("image/jpg");
			allowedTypes.add("image/gif");
			allowedTypes.add("image/png");
			allowedTypes.add("image/bmp");
			allowedTypes.add("image/jpeg");
			
			//设置上传文件的类型
			String contentType = img.getContentType();
			
			//检查上传文件类型是否正确
			if(!allowedTypes.contains(contentType)) {
				//不正确返回表单页面
				String code = "error.device.img";
				String message = messageSource.getMessage(code, null, locale);
				attributes.addFlashAttribute("message", message);
				return "device/input";
			}
		}
		
		//保存图片
		//声明虚拟路径
		String virtualPath = "/images";
		//获取servletContext对象
		ServletContext servletContext = session.getServletContext();
		//将虚拟路径转换为真实路径
		String realPath = servletContext.getRealPath(virtualPath);
		//从上传文件中获取输入流对象
		InputStream inputStream = img.getInputStream();
		//压缩图片，返回logoPath
		String logoPath = DataProcessUtils.resizeImages(inputStream, realPath);
		
		//设置logoPath
		if(logoPath != null) {
			device.setImgPath(logoPath);
		}
		device.setStatus(1);
		//保存Survey对象
		deviceService.save(device);
		return "redirect:/device/list";
	}
	
	@RequestMapping("/toAddUI")
	public String toAddUI(Map<String, Object> map) {
		Device device = new Device();
		map.put("device", device);
		List<Maintenance> maintenances = maintenanceService.getAll();
		map.put("maintenances", maintenances);
		return "device/input";
	}
	
	@ResponseBody
	@RequestMapping(value="/give/{id}", method=RequestMethod.PUT)
	public String give(@PathVariable("id") Integer id) {
		deviceService.give(id);
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.PUT)
	public String delete(@PathVariable("id") Integer id) {
		deviceService.delete(id);
		return "1";
	}
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, 
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = DataUtils.encodeParamsToQueryString(params);
		request.setAttribute("queryString", queryString);
		Page<Device> page = deviceService.getPage(pageNo, params);
		request.setAttribute("page", page);
		return "device/list";
	}
	
}

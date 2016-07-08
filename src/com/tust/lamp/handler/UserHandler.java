package com.tust.lamp.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.tust.lamp.entity.Authority;
import com.tust.lamp.entity.User;
import com.tust.lamp.orm.Navigation;
import com.tust.lamp.orm.Page;
import com.tust.lamp.service.UserService;
import com.tust.lamp.utils.DataUtils;

@Controller
@RequestMapping("/user")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/register")
	public String register() {
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigation> navigate(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Navigation> navigations = new ArrayList<Navigation>();
		String contextPath = session.getServletContext().getContextPath();
		Navigation top = new Navigation(Integer.MAX_VALUE, "实验室设备预约管理系统");
		navigations.add(top);
		Map<Integer, Navigation> parentNavigations = new HashMap<Integer, Navigation>();
		for (Authority authority : user.getRole().getAuthorities()) {
			Navigation navigation = new Navigation(authority.getId(), authority.getDisplayName());
			navigation.setUrl(contextPath + authority.getUrl());
			Authority parentAuthority = authority.getParentAuthority();
			Navigation parentNavigation = parentNavigations.get(parentAuthority.getId());
			if (parentNavigation == null) {
				parentNavigation = new Navigation(parentAuthority.getId(), parentAuthority.getDisplayName());
				parentNavigation.setState("closed");
				parentNavigations.put(parentAuthority.getId(), parentNavigation);
				top.getChildren().add(parentNavigation);
			}
			parentNavigation.getChildren().add(navigation);
		}
		return navigations;
	}
	
	@RequestMapping(value="/shiro-login", method=RequestMethod.POST)
	public String login2(@RequestParam(value="name",required=false) String name,
			@RequestParam(value="password", required=false) String password,
			HttpSession session,
			RedirectAttributes attributes,
			Locale locale){
		// 获取当前 User: 调用了 SecurityUtils.getSubject() 方法. 
		Subject currentUser = SecurityUtils.getSubject();

		// 检测用户是否已经被认证. 即用户是否登录. 调用 Subject 的 isAuthenticated()
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为一个 UsernamePasswordToken 对象. 
		    UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		    token.setRememberMe(true);
		    try {
		    	// 执行登录. 调用 Subject 的 login(UsernamePasswordToken) 方法.
		        currentUser.login(token);
		    } 
		    // 认证时的异常. 所有的认证时的异常的父类. 
		    catch (AuthenticationException ae) {
		    	String code = "error.user.login";
				String message = messageSource.getMessage(code, null, locale);
				attributes.addFlashAttribute("message", message);
				return "redirect:/index";
		    }
		}
		//可以通过调用 Subject 的 .getPrincipals().getPrimaryPrincipal() 获取到
		//在 realm 中创建 SimpleAuthenticationInfo 对象时的 principal 实例. 
		session.setAttribute("user", currentUser.getPrincipals().getPrimaryPrincipal());
		return "home/success";
	}
	
	@ResponseBody
	@RequestMapping(value="/give/{id}", method=RequestMethod.PUT)
	public String give(@PathVariable("id") Integer id) {
		userService.give(id);
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.PUT)
	public String delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return "1";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, 
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = DataUtils.encodeParamsToQueryString(params);
		request.setAttribute("queryString", queryString);
		Page<User> page = userService.getPage(pageNo, params);
		request.setAttribute("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/login")
	public String login(@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password, HttpSession session,
			RedirectAttributes attributes, Locale locale) {
		User user = userService.login(name, password);
		if (user == null) {
			String code = "error.user.login";
			String message = messageSource.getMessage(code, null, locale);
			attributes.addFlashAttribute("message", message);
			return "redirect:/index";
		}

		session.setAttribute("user", user);
		return "home/success";
	}

}

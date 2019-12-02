package com.hhit.system.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.FileDO;
import com.hhit.common.domain.Tree;
import com.hhit.common.service.FileService;
import com.hhit.common.utils.MD5Utils;
import com.hhit.common.utils.R;
import com.hhit.common.utils.ShiroUtils;
import com.hhit.system.domain.MenuDO;
import com.hhit.system.domain.UserDO;
import com.hhit.system.service.MenuService;
import com.hhit.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Resource
	UserService userService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/index";
	}


	@GetMapping({ "/index_v1" })
	String welcome_site(Model model) {

		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {

		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);

		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_ss.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_ss.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@Log("登录")
	@GetMapping("/m/login/{username}/{name}/{password}")
	@ResponseBody
	R ajaxLogin(@PathVariable("username") String username,
				@PathVariable("name") String name,@PathVariable("password") String password) {

		password = MD5Utils.encrypt(username, password);

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok().put("name",name);
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@Log("注册")
	@GetMapping("/m/register/login/{username}/{name}/{password}")
	@ResponseBody
	R ajaxRegister(@PathVariable("username") String username,
				@PathVariable("name") String name,@PathVariable("password") String password) {

		password = MD5Utils.encrypt(username, password);
		UserDO user = new UserDO();
		user.setUsername(username);
		user.setName(name);
		user.setPassword(password);
		user.setStatus(1);
		user.setDeptId((long) 6);
		ArrayList<Long> list = new ArrayList<>();
		list.add((long) 59);
		user.setRoleIds(list);
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error(1,"注册失败");
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}

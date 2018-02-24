package com.rmswebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmswebservice.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
private static final String SERVICE_LOGIN="login";
private static final String SERVICE_CHANGE_PASSWORD="changePassword/{userName}/{oldPassword}/{newPassword}";
@ResponseBody
@RequestMapping(value=SERVICE_LOGIN,method=RequestMethod.POST)
public String login(@RequestBody String jsonUserAuth){
	  jsonUserAuth=userService.login(jsonUserAuth);
	  return jsonUserAuth;
}
@ResponseBody
@RequestMapping(value=SERVICE_CHANGE_PASSWORD,method=RequestMethod.GET)
public String changePassword
(@PathVariable("userName") String userName,
		@PathVariable("oldPassword") String oldPassword
		,@PathVariable("newPassword") String newPassword){
	String jsonResponse=userService.changePassword(userName,oldPassword,newPassword);
	return jsonResponse;
}
}

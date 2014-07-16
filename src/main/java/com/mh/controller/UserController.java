package com.mh.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mh.exception.helper.ErrorInfo;
import com.mh.model.User;
import com.mh.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService messageService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createUserPage() {
		ModelAndView mav = new ModelAndView("users/new-user");
		mav.addObject("sUser", new User());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User createUser(@RequestBody @Valid User user) {
		java.util.Date date = new Date();
		user.setCreated_at(new java.sql.Timestamp(date.getTime()));
		user.setRole_id(2);
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);
		return messageService.create(user);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("users/edit-user");
		User user = messageService.get(id);
		mav.addObject("sUser", user);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User editUser(@PathVariable int id, @Valid @RequestBody User user) {
		User userToUpdate = messageService.get(id);
		user.setId(id);
		user.setEmail(userToUpdate.getEmail());
		user.setPassword(userToUpdate.getPassword());
		user.setCreated_at(userToUpdate.getCreated_at());
		user.setRole_id(userToUpdate.getRole_id());
		return messageService.update(user);
	}
	
	@RequestMapping(value="/edit/{id}/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(HttpServletRequest request, @RequestParam("profile-picture") MultipartFile profile){
        if (!profile.isEmpty()) {
            try {
            	
            	String filePath = request.getServletContext().getRealPath("/resources/profiles-pictures/"); 
            	profile.transferTo(new File(filePath + "/" + profile.getOriginalFilename()));
                
                return "You successfully uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + e.getMessage();
            }
        } else {
            return "You failed to upload 2";
        }
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User deleteUser(@PathVariable int id) {
		return messageService.delete(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> allUsers() {
		return messageService.getAll();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView allUsersPage() {
		ModelAndView mav = new ModelAndView("users/all-users");
		List<User> users = new ArrayList<User>();
		users.addAll(allUsers());
		mav.addObject("users", users);
		return mav;
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req,
			TypeMismatchException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.user.id",
				null, locale);

		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();

		return new ErrorInfo(errorURL, errorMessage);
	}

}

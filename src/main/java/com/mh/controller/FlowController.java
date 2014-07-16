package com.mh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mh.exception.helper.ErrorInfo;
import com.mh.model.Flow;
import com.mh.service.FlowService;

@Controller
@RequestMapping(value="/flows")
public class FlowController {
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createFlowPage() {
		ModelAndView mav = new ModelAndView("flows/new-flow");
		mav.addObject("sFlow", new Flow());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Flow createFlow(@RequestBody @Valid Flow flow) {
		java.util.Date date = new Date();
		flow.setCreated_at(new java.sql.Timestamp(date.getTime()));
		flow.setLikes(0);
		
		return flowService.create(flow);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editFlowPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("flows/edit-flow");
		Flow flow = flowService.get(id);
		mav.addObject("sFlow", flow);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Flow editFlow(@PathVariable int id, @Valid @RequestBody Flow flow) {
		flow.setId(id);
		return flowService.update(flow);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Flow deleteFlow(@PathVariable int id) {
		return flowService.delete(id);
	}
	
	@RequestMapping(value="/like/{id}", method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Flow likeFlow(@PathVariable int id) {
		return flowService.like(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Flow> allFlows() {
		return flowService.getAll();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView allFlowsPage() {
		ModelAndView mav = new ModelAndView("flows/all-flows");
		List<Flow> flows = new ArrayList<Flow>();
		flows.addAll(allFlows());
		mav.addObject("flows", flows);
		return mav;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.flow.id", null, locale);
		
		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();
		
		return new ErrorInfo(errorURL, errorMessage);
	}

}

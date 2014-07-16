package com.mh.controller;

import java.util.ArrayList;
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
import com.mh.model.Relation;
import com.mh.service.RelationService;

@Controller
@RequestMapping(value="/relations")
public class RelationController {
	
	@Autowired
	private RelationService relationService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createRelationPage() {
		ModelAndView mav = new ModelAndView("relations/new-relation");
		mav.addObject("sRelation", new Relation());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relation createRelation(@RequestBody @Valid Relation relation) {
		relation.setEnabled(false);
		return relationService.create(relation);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editRelationPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("relations/edit-relation");
		Relation relation = relationService.get(id);
		mav.addObject("sRelation", relation);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relation editRelation(@PathVariable int id, @Valid @RequestBody Relation relation) {
		relation.setId(id);
		return relationService.update(relation);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relation deleteRelation(@PathVariable int id) {
		return relationService.delete(id);
	}
	
	@RequestMapping(value="/enable/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relation enableRelation(@PathVariable int id) {
		System.out.println(id);
		return relationService.enable(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Relation> allRelations() {
		return relationService.getAll();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView allRelationsPage() {
		ModelAndView mav = new ModelAndView("relations/all-relations");
		List<Relation> relations = new ArrayList<Relation>();
		relations.addAll(allRelations());
		mav.addObject("relations", relations);
		return mav;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.relation.id", null, locale);
		
		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();
		
		return new ErrorInfo(errorURL, errorMessage);
	}

}

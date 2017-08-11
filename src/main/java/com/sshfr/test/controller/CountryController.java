package com.sshfr.test.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sshfr.test.entity.Country;
import com.sshfr.test.service.CountryService;

@Controller
@RequestMapping("/country")
public class CountryController {

	@Resource(name="countryService")
	private CountryService countryService;
	
	@RequestMapping(value="/add")
	public String addCountryPage(){
		return "addCountryPage";
	}
	
	@RequestMapping(value="/{cid}",method=RequestMethod.POST)
	public String addCountry(Country country){
		countryService.addCountry(country);
		return "redirect:/country";
	}
	
	@RequestMapping(value="/{cid}",method=RequestMethod.DELETE)
	public String deleteCountry(@PathVariable Integer cid){
		countryService.deleteCountry(cid);
		return "redirect:/country";
	}
	
	@RequestMapping("/{cid}/update")
	public String updateCountryPage(@PathVariable Integer cid,Model model){
		model.addAttribute("cid",cid);
		return "updateCountryPage";
	}
	
	@RequestMapping(value="/{cid}",method=RequestMethod.PUT)
	public String updateCountry(Country country){
		countryService.updateCountry(country);
		return "redirect:/country";
	}
	
	@RequestMapping(value="/{cid}",method=RequestMethod.GET)
	public String queryCountry(@PathVariable Integer cid,Model model){
		Country country  = countryService.queryCountry(cid);
		model.addAttribute("countrys",country);
		return "showCountrys";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String queryAll(Model model){
		List<Country>countrys = countryService.queryAll();
		model.addAttribute("countrys",countrys);
		return "showCountrys";
	}
}

/**
 * @author Lijingwen
 *
 */
package com.lijwen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lijwen.entity.Seckill;
import com.lijwen.service.SeckillService;

@Controller // @Service @Component
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> seckillList = seckillService.getSeckillList();
		model.addAttribute("list", seckillList);
		System.out.println("enter Controller");
		return "list";
	}

	@RequestMapping(value = "/{seckill_id}/detail", method = RequestMethod.GET)
	public String getSeckillDetail(@PathVariable("seckill_id") Long seckill_id, Model model) {
		Seckill sk = seckillService.getById(seckill_id);
		model.addAttribute("seckill", sk);
		return "detail";
	}

}
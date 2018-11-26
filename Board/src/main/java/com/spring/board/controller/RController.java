package com.spring.board.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.domain.ReplyVO;
import com.spring.board.persistent.ReplyDAO;

@Controller
public class RController {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	
	@RequestMapping(value="/reply/write",method=RequestMethod.POST)
	public @ResponseBody String replyWrite(@RequestBody ReplyVO replyVO) throws Exception{
		System.out.println(replyVO.getrContent());
		replyDAO.insert(replyVO);
		int rNum = replyDAO.selectMaxRnum();
		return rNum+"";
	}
	
	@RequestMapping("/reply/delete")
	public String replyDelete(@RequestParam int rNum,@RequestParam int bNum,RedirectAttributes redirect) throws Exception{
		
		replyDAO.delete(rNum);
		redirect.addAttribute("bNum", bNum); // 함수로 이동 할떄 리다이렉트로 사용 
		
		return "redirect:/board/detail";
	}
	
}

package com.yisisoftware.action.bgInfo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.Feedback;
import com.yisisoftware.service.business.bginfo.FeedbackServiceI;

/**
 * 意见反馈控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class FeedbackAction extends BaseAction {

	@Autowired
	private FeedbackServiceI feedbackService;	//意见反馈控制服务
	

	/**
	 * 意见反馈信息保存
	 * 
	 */
	public void saveFeedback(){
		
		boolean flag = false;
		Feedback feedback = new Feedback();
		String msg = "";
		String feedBackStyle = getRequest().getParameter("feedBackStyle");	//意见类型
		String feedBackContent = getRequest().getParameter("feedBackContent");	//意见内容
		String feedBackContact = getRequest().getParameter("feedBackContact");	//联系方式
		String feedBackMobileType = getRequest().getParameter("feedBackMobileType");	//手机类型
		String feedBackImei = getRequest().getParameter("feedBackImei");	//	手机Imei
		
		if(StringUtils.isNotEmpty(feedBackStyle) || StringUtils.isNotEmpty(feedBackContent) || 
				StringUtils.isNotEmpty(feedBackContact) || StringUtils.isNotEmpty(feedBackMobileType) || 
				StringUtils.isNotEmpty(feedBackImei)){
			
			if(StringUtils.isNotEmpty(feedBackStyle)){
				feedback.setFeedBackStyle(feedBackStyle);
			}
			
			if(StringUtils.isNotEmpty(feedBackContent)){
				feedback.setFeedBackContent(feedBackContent);
			}
			
			if(StringUtils.isNotEmpty(feedBackContact)){
				feedback.setFeedBackContact(feedBackContact);
			}
			
			if(StringUtils.isNotEmpty(feedBackMobileType)){
				feedback.setFeedBackMobileType(feedBackMobileType);
			}
			
			if(StringUtils.isNotEmpty(feedBackImei)){
				feedback.setFeedBackImei(feedBackImei);
			}
			
			this.feedbackService.save(feedback);
			flag = true;
			msg = "数据保存成功!";
		}else{
			msg = "数据保存失败!";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", flag);
//		map.put("msg", msg);
		
		writeJson(map);
	}
}

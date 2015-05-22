package com.yisisoftware.service.business.bginfo.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.Feedback;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.business.bginfo.FeedbackServiceI;

@Service("FeedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements
		FeedbackServiceI {

}

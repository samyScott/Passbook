package cn.edu.hdu.passbook.controller;

import cn.edu.hdu.passbook.vo.GainPassTemplateRequest;
import cn.edu.hdu.passbook.log.LogConstants;
import cn.edu.hdu.passbook.log.LogGenerator;
import cn.edu.hdu.passbook.service.IFeedbackService;
import cn.edu.hdu.passbook.service.IGainPassTemplateService;
import cn.edu.hdu.passbook.service.IInventoryService;
import cn.edu.hdu.passbook.service.IUserPassService;
import cn.edu.hdu.passbook.vo.CreateFeedbackRequest;
import cn.edu.hdu.passbook.vo.Pass;
import cn.edu.hdu.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>Passbook Rest Controller</h1>
 * @author samy
 * @date 2019/12/6 18:25
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class PassbookController {

    /** 用户优惠劵服务 */
    private final IUserPassService userPassService;

    /** 优惠劵库存服务 */
    private final IInventoryService inventoryService;

    /** 领取优惠劵服务 */
    private final IGainPassTemplateService gainPassTemplateService;

    /** 反馈服务 */
    private final IFeedbackService feedbackService;

    /** HttpServletRequest */
    private final HttpServletRequest httpServletRequest;

    public PassbookController(IUserPassService userPassService, IInventoryService inventoryService, IGainPassTemplateService gainPassTemplateService, IFeedbackService feedbackService, HttpServletRequest httpServletRequest) {
        this.userPassService = userPassService;
        this.inventoryService = inventoryService;
        this.gainPassTemplateService = gainPassTemplateService;
        this.feedbackService = feedbackService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * <h2>获取用户个人优惠劵信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/userPassInfo")
    Response userPassInfo(Integer userId) throws Exception{
        LogGenerator.genLog(httpServletRequest,userId, LogConstants.ActionName.USER_PASS_INFO,null);
        return userPassService.getUserPassInfo(userId);
    }

    /**
     * <h2>获取用户使用过的优惠劵信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/userUsedPassInfo")
    Response userUsedPassInfo(Integer userId) throws Exception{
        LogGenerator.genLog(httpServletRequest,userId, LogConstants.ActionName.USER_USED_PASS_INFO,null);
        return userPassService.getUserUsedPassInfo(userId);
    }

    /**
     * <h2>用户使用优惠劵</h2>
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/userUsePass")
    Response userUsePass(@RequestBody Pass pass){
        LogGenerator.genLog(httpServletRequest,pass.getUserId(), LogConstants.ActionName.USER_USE_PASS,pass);
        return userPassService.userUsePass(pass);
    }

    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/inventoryInfo")
    Response inventoryInfo(Integer userId) throws Exception{
        LogGenerator.genLog(httpServletRequest,userId, LogConstants.ActionName.INVENTORY_INFO,null);
        return inventoryService.getInventoryInfo(userId);
    }

    /**
     * <h2>用户领取优惠劵</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/gainPassTemplate")
    Response gainPassTemplate(@RequestBody GainPassTemplateRequest request) throws Exception{
        LogGenerator.genLog(httpServletRequest,request.getUserId(), LogConstants.ActionName.GAIN_PASS_TEMPLATE,request);
        return gainPassTemplateService.gainPassTemplate(request);
    }

    /**
     * <h2>用户创建评论</h2>
     * @param feedback {@link CreateFeedbackRequest}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/createFeedback")
    Response createFeedback(@RequestBody CreateFeedbackRequest feedback){
        LogGenerator.genLog(httpServletRequest,feedback.getUserId(), LogConstants.ActionName.CREATE_FEEDBACK,feedback);
        return feedbackService.createFeedback(feedback);
    }

    /**
     * <h2>用户获取评论信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/getFeedback")
    Response getFeedback(Integer userId) {
        LogGenerator.genLog(httpServletRequest,userId, LogConstants.ActionName.GET_FEEDBACK,null);
        return feedbackService.getFeedback(userId);
    }

    /**
     * <h2>异常展示接口</h2>
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/exception")
    Response exception() throws Exception {
        throw new Exception("Welcome to HDU");
    }


}

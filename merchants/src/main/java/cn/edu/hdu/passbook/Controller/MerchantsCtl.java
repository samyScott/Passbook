package cn.edu.hdu.passbook.Controller;

import cn.edu.hdu.passbook.service.IMerchantsServ;
import cn.edu.hdu.passbook.vo.CreateMerchantsRequest;
import cn.edu.hdu.passbook.vo.PassTemplate;
import cn.edu.hdu.passbook.vo.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务 Controller</h1>
 * @author samy
 * @date 2019/11/25 20:42
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {

    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo: {}",id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate: {}", JSON.toJSONString(passTemplate));
        return merchantsServ.dropPassTemplate(passTemplate);
    }


}

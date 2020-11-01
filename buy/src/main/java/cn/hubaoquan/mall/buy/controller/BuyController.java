package cn.hubaoquan.mall.buy.controller;

import cn.hubaoquan.mall.orderservice.mapper.OrderDetailMapper;
import cn.hubaoquan.mall.orderservice.mapper.OrderMapper;
import cn.hubaoquan.mall.productservice.mapper.ProductMapper;
import cn.hubaoquan.mall.productservice.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mall")
public class BuyController {

//    @Resource
//    OrderMapper orderMapper;
//    @Resource
//    OrderDetailMapper orderDetailMapper;
//    @Resource
//    ProductMapper productMapper;

    @RequestMapping("buy")
    public String buy(@RequestParam Integer userId,@RequestParam Integer productId,@RequestParam Integer quantity)
    {

        return "";
    }
}

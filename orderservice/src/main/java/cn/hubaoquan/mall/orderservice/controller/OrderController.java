package cn.hubaoquan.mall.orderservice.controller;


import cn.hubaoquan.mall.orderservice.OrderserviceApplication;
import cn.hubaoquan.mall.orderservice.mapper.OrderDetailMapper;
import cn.hubaoquan.mall.orderservice.mapper.OrderMapper;
import cn.hubaoquan.mall.orderservice.model.Order;
import cn.hubaoquan.mall.orderservice.model.OrderDetail;
import cn.hubaoquan.mall.productservice.model.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderDetailMapper orderDetailMapper;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/create")
    public String createOrder(@RequestParam Integer userId,@RequestParam Integer productId,@RequestParam Integer quantity)
    {
        String url = "http://product-server/product/"+productId;
        Product product= restTemplate.getForObject(url, Product.class);
        Order order = new Order();
        order.setTotal(product.getPrice()*quantity);
        order.setUserId(userId);
        orderMapper.insert(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(productId);
        orderDetail.setQuantity(quantity);
        orderDetailMapper.insert(orderDetail);
        return "ok";
    }
}

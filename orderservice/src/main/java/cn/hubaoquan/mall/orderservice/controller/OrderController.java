package cn.hubaoquan.mall.orderservice.controller;

import cn.hubaoquan.mall.orderservice.client.ProductClient;
import cn.hubaoquan.mall.orderservice.mapper.OrderDetailMapper;
import cn.hubaoquan.mall.orderservice.mapper.OrderMapper;
import cn.hubaoquan.mall.orderservice.model.Order;
import cn.hubaoquan.mall.orderservice.model.OrderDetail;
import cn.hubaoquan.mall.productservice.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderDetailMapper orderDetailMapper;

    //Ribbon远程调用负载均衡方式一：在启动类声明RestTemplate Bean,然后使用RestTemplate
    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/create")
    public String createOrder(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        String url = "http://product-server/product/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        Order order = new Order();
        if (product != null)
            order.setTotal(product.getPrice() * quantity);
        order.setUserId(userId);
        orderMapper.insert(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(productId);
        orderDetail.setQuantity(quantity);
        orderDetailMapper.insert(orderDetail);
        return "ok";
    }
    // Ribbon负载均衡实现二,LoadBalancerClient
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @RequestMapping("/create2")
    public String createOrder2(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        ServiceInstance instance = loadBalancerClient.choose("product-server");

        String serviceUrl = String.format("http://%s:%s/product/" + productId, instance.getHost(), instance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(serviceUrl);
        Product product = restTemplate.getForObject(serviceUrl, Product.class);
        Order order = new Order();
        if (product != null)
            order.setTotal(product.getPrice() * quantity);
        order.setUserId(userId);
        orderMapper.insert(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(3);
        orderDetail.setProductId(productId);
        orderDetail.setQuantity(quantity);
        orderDetailMapper.insert(orderDetail);
        return "ok";
    }

    //注入商品服务客户端
    @Autowired
    ProductClient productClient;

    //远程调用实现三
    @RequestMapping("/createByFeign")
    public String createByFeign(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        Product product = productClient.findById(productId);
        Order order = new Order();
        if (product != null)
            order.setTotal(product.getPrice() * quantity);
        order.setUserId(userId);
        orderMapper.insert(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(5);
        orderDetail.setProductId(productId);
        orderDetail.setQuantity(quantity);
        orderDetailMapper.insert(orderDetail);
        return "ok Feign";
    }

    @RequestMapping("/failTest")
    //@HystrixCommand(fallbackMethod = "failTestFail")
    public String failTest(String s)
    {
        return productClient.failTest();
    }

//    private String failTestFail(String s)
//    {
//        System.out.println("调用productClient::failTestFail失败");
//        return "500";
//    }



}

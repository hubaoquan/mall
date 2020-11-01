package cn.hubaoquan.mall.orderservice.client;

import cn.hubaoquan.mall.productservice.model.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//商品服务客户端
@FeignClient(name = "product-server")
public interface ProductClient {
    @RequestMapping("/product/{id}")
    Product findById(@PathVariable Integer id);
}

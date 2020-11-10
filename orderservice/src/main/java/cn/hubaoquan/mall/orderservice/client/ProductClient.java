package cn.hubaoquan.mall.orderservice.client;

import cn.hubaoquan.mall.orderservice.failback.ProductFailBack;
import cn.hubaoquan.mall.productservice.model.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//商品服务客户端,需要指定要访问的服务名
@FeignClient(name = "product-server",fallback = ProductFailBack.class)
public interface ProductClient {
    @RequestMapping("/product/{id}")
    Product findById(@PathVariable Integer id);

    @RequestMapping("product/failtest")
    String failTest();
}

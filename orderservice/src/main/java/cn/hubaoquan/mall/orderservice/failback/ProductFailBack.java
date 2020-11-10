package cn.hubaoquan.mall.orderservice.failback;

import cn.hubaoquan.mall.orderservice.client.ProductClient;
import cn.hubaoquan.mall.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFailBack implements ProductClient {
    @Override
    public Product findById(Integer id) {
        System.out.println("调用findById失败");
        return null;
    }

    @Override
    public String failTest() {
        System.out.println("调用failTest失败");
        return "调用failTest失败";
    }
}

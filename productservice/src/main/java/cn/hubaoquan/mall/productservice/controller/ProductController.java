package cn.hubaoquan.mall.productservice.controller;

import cn.hubaoquan.mall.productservice.mapper.ProductMapper;
import cn.hubaoquan.mall.productservice.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    ProductMapper productMapper;
    @RequestMapping("{id}")
    public Product getProductById(@PathVariable(required = false) Integer id)
    {

        return productMapper.selectByPrimaryKey(id);
    }

    //由于Fei设置了超时时间2秒，所以调用这个方法必然会出错
    @RequestMapping("failtest")
    public String failTest()
    {
        System.out.println("进入product failtest");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出product failtest");
        return "failtest";
    }


}

package cn.hubaoquan.mall.productservice.controller;

import cn.hubaoquan.mall.productservice.mapper.ProductMapper;
import cn.hubaoquan.mall.productservice.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    ProductMapper productMapper;
    @RequestMapping("{id}")
    public Product getProductById(@PathVariable Integer id)
    {
        return productMapper.selectByPrimaryKey(id);
    }
}

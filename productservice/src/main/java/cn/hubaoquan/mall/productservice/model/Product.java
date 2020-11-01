package cn.hubaoquan.mall.productservice.model;

public class Product {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 商品名称
    */
    private String name;

    /**
    * 商品价格(单位:¥分)
    */
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
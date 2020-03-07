package com.leyou.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_brand")
public class Brand {
//    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        //反射创建对象一
//        Class aClass = Class.forName("Brand");
//        System.out.println(aClass);
//        //反射创建对象二
//        Class<Brand> brandClass = Brand.class;
//        System.out.println(brandClass);
//        //反射创建对象三
//        Brand brand = new Brand();
//        Class<? extends Brand> aClass1 = brand.getClass();
//        System.out.println(aClass1);
//
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// 品牌名称
    private String image;// 品牌图片
    private Character letter;//首字母
    // getter setter 略

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", letter=" + letter +
                '}';
    }
}

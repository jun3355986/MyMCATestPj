package com.jun.patterm.factory.abstractfactory;

/**
 * @decription: 家装风格抽象工厂
 * @date: 2022/4/8 17:29
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public interface HomeStyleAbstractFactory {

    Floor getFloor();

    Light getLight();

    Sofa getSofa();

}

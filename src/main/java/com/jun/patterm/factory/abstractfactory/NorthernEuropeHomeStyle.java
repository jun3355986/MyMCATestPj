package com.jun.patterm.factory.abstractfactory;

/**
 * @decription: 北欧风格
 * @date: 2022/4/8 17:46
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class NorthernEuropeHomeStyle implements HomeStyleAbstractFactory{
    @Override
    public Floor getFloor() {
        return null;
    }

    @Override
    public Light getLight() {
        return null;
    }

    @Override
    public Sofa getSofa() {
        return null;
    }
}

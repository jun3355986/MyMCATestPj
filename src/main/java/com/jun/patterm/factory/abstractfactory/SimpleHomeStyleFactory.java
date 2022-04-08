package com.jun.patterm.factory.abstractfactory;

/**
 * @decription: 简约风格的家装
 * @date: 2022/4/8 17:44
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class SimpleHomeStyleFactory implements HomeStyleAbstractFactory{
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

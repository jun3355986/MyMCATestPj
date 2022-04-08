package com.jun.patterm.factory.abstractfactory;

/**
 * @decription: 田园风
 * @date: 2022/4/8 17:47
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class CountrysideHomeStyle implements HomeStyleAbstractFactory{
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

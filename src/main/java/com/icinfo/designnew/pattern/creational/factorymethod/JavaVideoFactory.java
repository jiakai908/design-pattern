package com.icinfo.designnew.pattern.creational.factorymethod;

/**
 * @program: design-pattern
 * @description: ${description}
 * @author: jkk
 * @create: 2019-03-16 20:35
 */
public class JavaVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}

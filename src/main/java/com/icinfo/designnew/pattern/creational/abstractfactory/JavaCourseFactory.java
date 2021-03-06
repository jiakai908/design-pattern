package com.icinfo.designnew.pattern.creational.abstractfactory;

/**
 * @program: design-pattern
 * @description: ${description}
 * @author: jkk
 * @create: 2019-03-17 18:27
 */
public class JavaCourseFactory implements CourseFactory{
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}

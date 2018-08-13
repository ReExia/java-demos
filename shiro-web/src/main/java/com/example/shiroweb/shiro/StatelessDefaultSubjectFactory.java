package com.example.shiroweb.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    /**
     * SubjectContext在创建的时候,需要关闭session的创建,
     * 主要是由DefaultWebSubjectFactory的createSubject进行管理
     * @param context
     * @return
     */
    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}

package com.ku.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.yulichang.injector.MPJSqlInjector;
import com.github.yulichang.method.SelectJoinList;
import com.github.yulichang.method.SelectJoinOne;
import com.github.yulichang.method.SelectJoinPage;
import org.apache.ibatis.session.Configuration;

import java.util.List;

public class MybatisSqlInjector extends MPJSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methods = super.getMethodList(configuration, mapperClass, tableInfo);
        methods.add(new SelectJoinOne());
        methods.add(new SelectJoinPage());
        methods.add(new SelectJoinList());

        return super.getMethodList(configuration, mapperClass, tableInfo);
    }
}

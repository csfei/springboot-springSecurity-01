package com.cuisf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class CodeGenerator {

     public static String scanner(String tip){
         Scanner scanner = new Scanner(System.in);

         StringBuilder help = new StringBuilder();

         help.append("请输入" +tip+ ": ");

         System.out.println(help.toString());

         if (scanner.hasNext()){
             String ipt = scanner.next();
             if (StringUtils.isNotBlank(ipt)){
                 return ipt;
             }
         }
        throw  new MybatisPlusException("请输入正确的 "+ tip + "!");
     }


    public static void main(String[] args) {
        //创建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(scanner("请输入你的项目路径")+"/src/main/java");

        gc.setAuthor("cuisf");
        //生成之后是否打开资源管理器
        gc.setOpen(false);
        //生成之后是否覆盖文件
        gc.setFileOverride(false);
        //%s 为占位符
        //mp 生成的service层代码，默认接口名称钱第一个字符是有I
        gc.setServiceName("%sService");
        //生成主键策略 自动增长
        gc.setIdType(IdType.AUTO);
        //设置date 的类型
        gc.setDateType(DateType.ONLY_DATE);
        //开启实体属性 Swagger2的注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/security?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
        sourceConfig.setDriverName("com.mysql.jdbc.Driver");
        sourceConfig.setUsername("root");
        sourceConfig.setPassword("root");

        sourceConfig.setDbType(DbType.MYSQL);
        mpg.setDataSource(sourceConfig);


        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.cuisf");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        packageConfig.setXml("mapper");
        mpg.setPackageInfo(packageConfig);

        //策略模式
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置哪些表需要自动生成
        strategyConfig.setInclude(scanner("表名，多个 英文逗号分隔").split(","));
        //实体类驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //列命名驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //使用简化GETTER SETTER
        strategyConfig.setEntityLombokModel(true);
        //设置 REst controller 风格
        strategyConfig.setRestControllerStyle(true);

        //驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        //忽略表中生成实体类的前缀

        mpg.setStrategy(strategyConfig);
        mpg.execute();




    }



}

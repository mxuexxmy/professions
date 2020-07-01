package com.professions.professions;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus 代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 配置 GlobalConfig
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E:\\Project\\professions\\src\\main\\java"); // 绝对路径写法
        gc.setAuthor("mxuexxmy");
        gc.setOpen(false); // 生成后是否打开资源管理器
        gc.setFileOverride(true); // 重新生成文件时是否覆盖
        gc.setServiceName("%sService"); // 去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); // 主键策略
        gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        // gc.setSwagger2(false); // 开启Swagger2模式
        gc.setBaseResultMap(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

       // 配置 DataSourceConfig
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/profession?useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("f9ab5014");
        dsc.setDbType(DbType.MYSQL);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.professions"); // 包名
        pc.setModuleName("professions"); // 模块名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        mpg.setDataSource(dsc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("tb_user","tb_teach_all","tb_school","tb_profession","tb_grade","tb_credit_time","tb_course","tb_content_category","tb_content","tb_college");

        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); // 生成实体是去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain=true) setter 链式操作

        strategy.setRestControllerStyle(true); // restful api 风格
        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}

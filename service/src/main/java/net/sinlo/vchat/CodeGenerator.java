//package net.sinlo.vchat;
//
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class CodeGenerator {
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//     public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("ldr");
//        gc.setOpen(false);
//         gc.setSwagger2(true);// 实体属性 Swagger2 注解
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/vchat?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT");
//         dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
//        pc.setParent("net.sinlo.vhat");
//        mpg.setPackageInfo(pc);
//
//         // 策略配置
//         StrategyConfig strategy = new StrategyConfig();
//         strategy.setNaming(NamingStrategy.underline_to_camel);
//         strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////         strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//         strategy.setEntityLombokModel(true);
//         strategy.setRestControllerStyle(true);
//         // 公共父类
////         strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//         // 写于父类中的公共字段
//         strategy.setSuperEntityColumns("id");
//         strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//         strategy.setControllerMappingHyphenStyle(true);
//         strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.execute();
//    }
//
//}

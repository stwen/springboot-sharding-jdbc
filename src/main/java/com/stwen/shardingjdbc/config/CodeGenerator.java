package com.stwen.shardingjdbc.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成Controller、service、dao、mapper.xml文件
 * 注意会覆盖
 */
public class CodeGenerator {
    //逗号分隔，如t_user,t_product
    private static String tables = "t_user";
    private static String tablePrefix = "t_";
    private static String author = "stwen_gan";
    private static String parentPackage = "com.stwen.shardingjdbc";

    private static String outputPath = "/src/main/java";
    private static String mapperOutputDir = "/src/main/resources/mapper";

    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://192.168.239.128:3007/test?useUnicode=true&characterEncoding=utf8";


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //获取当前项目父根路径
        gc.setOutputDir(projectPath + outputPath)
                .setAuthor(author)
                .setOpen(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setMapperName("%sDao")
                .setFileOverride(true);
//            .setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url)
                .setDriverName(driver)
                .setUsername(username)
                .setPassword(password);
        // dsc.setSchemaName("public");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setMapper("dao");//设置dao文件放的包名
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + mapperOutputDir
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)//下划线转驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(tablePrefix) //去掉表名的前缀
                //.setSuperEntityClass("com.baomidou.ant.common.BaseEntity")
                .setEntityLombokModel(true)  //是否为lombok 模型
                .setRestControllerStyle(true)
                // 公共父类
                //.setSuperControllerClass("com.baomidou.ant.common.BaseController")
                // 写于父类中的公共字段
//                .setSuperEntityColumns("id")
                .setInclude(tables.split(","))
                .setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {


    public static void main(String[] args) {
        String mysqlHost = System.getenv("MYSQL-HOST");
        String mysqlPort = System.getenv("MYSQL-PORT");
        String mysqlPass = System.getenv("MYSQL-PASS");
        String mysqlUser = System.getenv("MYSQL-USER");
        String database = System.getenv("DATABASE");
        String module = System.getenv("MODULE");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "E:\\generate_code";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("107");
        gc.setOpen(true);
        gc.setIdType(IdType.AUTO);
        gc.setSwagger2(true);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setMapperName("%sMapper");
        gc.setFileOverride(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");

        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + database + "?useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false" +
                "&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(mysqlUser);
        dsc.setPassword(mysqlPass);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(module);
        pc.setEntity("entity.po");
        pc.setMapper("mapper");

        pc.setParent("com.linchtech.gjzz");
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
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // focList.add(new FileOutConfig(service_templatePath) {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        //         return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
        //                 + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
        //     }
        // });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
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
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setLogicDeleteFieldName("delete_flag");
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        // 需要生成的表名
        // strategy.setInclude("plate_no");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntitySerialVersionUID(true);
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("delete_flag");
        strategy.setSuperEntityClass(BasePO.class);
        strategy.setSuperEntityColumns("id","createTime","updateTime","deleteFlag","version");
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        // strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
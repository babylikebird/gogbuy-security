package com.gogbuy.security.admin;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * Created by Mr.Yangxiufeng on 2018/1/29.
 * Time:11:08
 * ProjectName:gogbuy-security
 */
public class GogGenerator {
    @Test
    public void generateCode(){
        String packageName = "com.gogbuy.security.admin.modules.sys";
        generateByTables(packageName, "sys_user", "sys_role");
    }
    private void generateByTables(String packageName, String... tableNames){

        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setAuthor("杨秀峰")
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true)
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setBaseColumnList(true)
                .setBaseResultMap(true);

        String dbUrl = "jdbc:mysql://localhost:3306/gog-security?characterEncoding=utf-8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(false)
                .setNaming(NamingStrategy.underline_to_camel);
//                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(config);
        generator.setDataSource(dataSourceConfig);
        generator.setStrategy(strategyConfig);
        generator.setPackageInfo(
                new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
        );
        generator.execute();
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent(packageName)
//                                .setController("controller")
//                                .setEntity("entity")
//                ).execute();
    }
}

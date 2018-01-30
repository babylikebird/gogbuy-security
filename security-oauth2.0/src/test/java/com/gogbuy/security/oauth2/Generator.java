package com.gogbuy.security.oauth2;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-01-30
 * Time: 17:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Generator {
    @Test
    public void generateCode(){
        String packageName = "com.gogbuy.security.oauth2.modules.sys";
        generateByTables(packageName, "sys_user", "sys_role");
    }
    private void generateByTables(String packageName, String... tableNames){

        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setAuthor("杨秀峰")
                .setOutputDir("d:\\oauth")
                .setFileOverride(true)
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sDao")
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


        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}

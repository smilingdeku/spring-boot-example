package org.example.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

public class CodeGenerator {

    private static AutoGenerator mpg  = new AutoGenerator();

    public static void main(String[] args) {
        mpg.setTemplateEngine(new BeetlTemplateEngine())
            .setDataSource(new DataSourceConfig()
                .setUrl(String.format("jdbc:mysql://%s/%s",
                        "47.103.195.154:3306", "base"))
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("Qwe#123456"))
            .setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setTablePrefix("t_")
                .setInclude("t_sys_user")
                .setLogicDeleteFieldName("deleted_at"))
            .setGlobalConfig(new GlobalConfig()
                .setAuthor("linzhaoming")
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                .setOpen(false))
            .setPackageInfo(new PackageConfig().setParent("org.example.module.system.user"))
            .execute();
    }

}

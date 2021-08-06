package com.example.picserver

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException
import com.baomidou.mybatisplus.core.toolkit.StringPool
import com.baomidou.mybatisplus.core.toolkit.StringUtils
import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.InjectionConfig
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder
import com.baomidou.mybatisplus.generator.config.po.TableFill
import com.baomidou.mybatisplus.generator.config.po.TableInfo
import com.baomidou.mybatisplus.generator.config.rules.FileType
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import java.io.File
import java.util.*

/**
 * Created by jesse on 2021/5/12 上午10:53
 */

/**
 *
 *
 * 读取控制台内容
 *
 */
fun scanner(tip: String): String {
    val scanner = Scanner(System.`in`)
    val help = StringBuilder()
    help.append("请输入$tip：")
    println(help)
    if (scanner.hasNext()) {
        val ipt = scanner.next()
        if (StringUtils.isNotBlank(ipt)) {
            return ipt
        }
    }
    throw MybatisPlusException("请输入正确的$tip！")
}

fun main() {
    // 代码生成器
    val mpg = AutoGenerator()

    // 全局配置
    val gc = GlobalConfig()
    val projectPath = System.getProperty("user.dir")
    gc.outputDir = "$projectPath/src/main/kotlin"
    gc.author = "jesse"
    gc.isOpen = false
    gc.isSwagger2 = true
    gc.isFileOverride = true
    gc.serviceName = "%sService"
//    gc.mapperName = "%sDao"
    gc.idType = IdType.AUTO
    gc.isKotlin = true
    mpg.globalConfig = gc

    // 数据源配置
    val dsc = DataSourceConfig()
    dsc.url =
        "jdbc:mysql://localhost:3306/pic?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8"
    // dsc.setSchemaName("public");
    dsc.driverName = "com.mysql.cj.jdbc.Driver"
    dsc.username = "root"
    dsc.password = "123"
    mpg.dataSource = dsc

    // 包配置
    val pc = PackageConfig()
    //        pc.setModuleName(scanner("模块名"));
    pc.parent = "com.example.picserver"
    pc.mapper = "mapper"
    mpg.packageInfo = pc

    // 自定义配置
    val cfg: InjectionConfig = object : InjectionConfig() {
        override fun initMap() {
            // to do nothing
        }
    }

    // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    val templatePath = "/templates/mapper.xml.vm"

    // 自定义输出配置
    val focList: MutableList<FileOutConfig> = ArrayList()
    // 自定义配置会被优先输出
    focList.add(object : FileOutConfig(templatePath) {
        override fun outputFile(tableInfo: TableInfo): String {
            // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
            return (projectPath + "/src/main/resources/mapper/" + pc.moduleName
                    + "/" + tableInfo.entityName + "Mapper" + StringPool.DOT_XML)
        }
    })
    cfg.fileCreate = IFileCreate { _: ConfigBuilder?, fileType: FileType, filePath: String ->
        // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
        if (fileType == FileType.CONTROLLER) {
            return@IFileCreate false
        }
        if (fileType == FileType.MAPPER || fileType == FileType.SERVICE || fileType == FileType.SERVICE_IMPL || fileType == FileType.XML) {
            // 已经生成 mapper 文件判断存在，不想重新生成返回 false
            return@IFileCreate !File(filePath).exists()
        }
        true
    }
    cfg.fileOutConfigList = focList
    mpg.cfg = cfg

    // 配置模板
    val templateConfig = TemplateConfig()

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();
    templateConfig.xml = null
    mpg.template = templateConfig

    // 策略配置
    val strategy = StrategyConfig()
    strategy.naming = NamingStrategy.underline_to_camel
    strategy.columnNaming = NamingStrategy.underline_to_camel
    //        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
    strategy.isEntityLombokModel = true
    strategy.isRestControllerStyle = true
    // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
    // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
    strategy.setInclude(*scanner("表名，多个英文逗号分割").split(",").toTypedArray())
    strategy.isControllerMappingHyphenStyle = true
    strategy.setTablePrefix(pc.moduleName + "_")
//    strategy.isChainModel = true

    // 自动填充
    val tableFill = TableFill("create_time", FieldFill.INSERT)
    val tableFill2 = TableFill("update_time", FieldFill.INSERT_UPDATE)
    val arr: MutableList<TableFill> = ArrayList()
    arr.add(tableFill)
    arr.add(tableFill2)
    strategy.tableFillList = arr
    mpg.strategy = strategy
    mpg.execute()
}

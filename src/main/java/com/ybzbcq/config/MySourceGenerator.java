package com.ybzbcq.config;

import org.mybatis.generator.api.ShellRunner;

import java.io.IOException;

//利用mybatis.generator自动生成代码  打包的时候main方法需要注释掉
public class MySourceGenerator {

    public static void main(String[] args) throws IOException {

        // SetTable.class.getClassLoader().getResourceAsStream(path);

//        String filePath = "/config/generatorConfig.xml";
//
//        String file = MySourceGenerator.class.getResource(filePath).getFile();
//        System.out.println(file);


        /*InputStream resourceAsStream = MySourceGenerator.class.getClassLoader().getResourceAsStream(filePath);
        BufferedReader  br = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));

        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();*/

        args = new String[] { "-configfile", "src\\main\\resources\\config\\generatorConfig.xml", "-overwrite" };
//        args = new String[] { "-configfile", "D:\\test_idea\\springboot-mybatis\\src\\main\\resources\\config\\generatorConfig.xml", "-overwrite" };
        ShellRunner.main(args);
    }

}

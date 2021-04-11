package com.example.demo.config.loader;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : lp225484
 * @date : 2021/04/04
 * @description:
 */
public class CustomClassLoader extends ClassLoader{

    private static CustomClassLoader customClassLoader = null;
    private CustomClassLoader(){

    }

    public static CustomClassLoader getCustomClassLoader(){
        synchronized (CustomClassLoader.class){
            if(customClassLoader == null){
                synchronized (CustomClassLoader.class){
                    customClassLoader = new CustomClassLoader();
                }
            }
        }
        return customClassLoader;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = CustomClassLoader.class.getResource("/").getPath(); // 得到classPath
        String fileName = name.replace(".", "/") + ".class";
        File  classFile = new File(classPath, fileName);
        if(!classFile.exists()){
            throw new ClassNotFoundException(classFile.getPath() + "不存在");
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        FileInputStream fis = null;
        FileChannel fc = null;

        try{
            fis = new FileInputStream(classFile);
            fc = fis.getChannel();
            while (fc.read(bf) > 0){
                bf.flip();
                bos.write(bf.array(), 0, bf.limit());
                bf.clear();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                bos.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Class<?>  clazz = findLoadedClass(name);
        if(clazz == null){
            clazz = super.loadClass(name);
        }
        return clazz;
    }

}

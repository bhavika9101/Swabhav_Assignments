package com.tss;

import java.io.*;

public class Files {
    public static void main(String[] args) throws Exception {

////        reading writing image
//        File file = new File("C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement\\src\\com\\tss\\cat.jpg");
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//        BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream("CopiedImage.jpg"));
//        int ch = bis.read();
//        while (ch!=-1){
//            bos.write(ch);
//            ch = bis.read();
//        }

////      text file
//        File file = new File("C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement\\src\\com\\tss\\Demo.txt");
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedWriter bw = new BufferedWriter(new FileWriter("CopiedDemo.txt"));
//        int c = br.read();
//        while (c != -1){
//            bw.write(c);
//            c = br.read();
//        }
//        br.close();
//        bw.close();



////        serialization
//        SerialUser serialUser = new SerialUser();
//        serialUser.setId(10);
//        serialUser.setName("Bhavika");
//        serialUser.setAge(21);
//
//
//        FileOutputStream fos = new FileOutputStream("serialUser.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(serialUser);
//        oos.close();

//        deserialization
        FileInputStream fis = new FileInputStream("serialUser.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerialUser user = (SerialUser) ois.readObject();
        System.out.println(user.toString());


//
//        File file = new File("C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement");
////        File file = new File("C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement\\src\\com\\tss\\Demo.txt");
//        if(file.isDirectory()){
//            expand(file);
//        }else {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            int c = br.read();
//            while (c != -1){
//                System.out.print((char)c);
//                c = br.read();
//            }
//            br.close();
//        }
    }
//    public static void expand(File f){
//        for (File f1: f.listFiles()){
//            if(f1.isDirectory()){
//                System.out.println(f1.getName());
//                expand(f1);
//            }else{
//                System.out.println(f1.getName());
//            }
//        }
//    }
}

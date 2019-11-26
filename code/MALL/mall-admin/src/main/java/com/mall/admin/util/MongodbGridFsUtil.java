package com.mall.admin.util;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.sun.imageio.plugins.common.I18N;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * GridFS通用类
 */
@Component
public class MongodbGridFsUtil {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    private GridFSBucket gridFSBucket;

    /**
     * 向Girdfs存储文件
     * @param pathFile
     */
    public void contextLoads(String pathFile,String name) throws FileNotFoundException {
        File file = new File(pathFile);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId=gridFsTemplate.store(fileInputStream,name);
        System.out.println("Girdfs存储文件"+objectId.toString());
    }

    /**
     * 向Girdfs删除文件
     */
    public void testDelFile() throws Exception{
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("123")));

    }


    /**
     * Girdfs通过文件名读取
     */
    public void findFileByName(String name) throws Exception{
        Query query = new Query();
        //根据名字查询
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("filename").is("测试")));
        System.out.println("根据名字查询:"+gridFSFile);
        if(gridFSFile!=null){
            System.out.println("gridFSFile.getId():"+gridFSFile.getId());
            System.out.println("gridFSFile.getObjectId():"+gridFSFile.getObjectId());
            GridFSDownloadStream in = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            System.out.println("GridFSDownloadStream::::::::::::"+in);
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,in);

            InputStream inputStream = gridFsResource.getInputStream();
            byte []  bytes = getBytes(inputStream);

            FileOutputStream outputStream = new FileOutputStream("F:\\IDEA_GIT_PROJECT\\LL_MALL\\code\\Mall-Vue\\static\\img\\"+gridFSFile.getFilename()+".png");
            outputStream.write(bytes);
        }
    }

    private byte[] getBytes(InputStream inputStream) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte [] b = new byte[1024];
        int i = 0;
        while (-1!=(i = inputStream.read(b))){
            byteArrayOutputStream.write(b,0,i);
        }
        return  byteArrayOutputStream.toByteArray();
    }
}

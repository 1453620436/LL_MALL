package com.mall.admin.util;

import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * GridFS通用类
 */
@Component
public class MongodbGridFsUtil {
    @Autowired
    private GridFsTemplate gridFsTemplate;

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
    public void testDelFile(){
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("123")));

    }


    /**
     * Girdfs通过文件名读取
     */
    public void findFileByName(String name){
        Query query = new Query();
        //根据名字查询
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("name").is(name)));
    }
}

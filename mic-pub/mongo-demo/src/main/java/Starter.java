import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


/**
 *  MongoDB 的API操作
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
public class Starter {
    private static MongoClient mongoClient;//服务器
    private static MongoDatabase db;//数据库
    static{
        mongoClient = MongoClients.create("mongodb://192.168.1.129:27017");
        db =  mongoClient.getDatabase("testdb");

    }
    public static void main(String[] args) {

    }

    /**
     * 添加List
     */
    public void insertMany(){

        List<Document> list = new ArrayList<Document>();
        for (int i = 0;  i< 100;i++){
            list.add(new Document("name",i));
        }
        MongoCollection collection = getMongoCollection("t_member");
        collection.insertMany(list);
    }

    /**
     * 查询所有
     */
    public void select(){
        MongoCollection collection = getMongoCollection("t_member");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }


    public static MongoCollection getMongoCollection(String collection){
        return db.getCollection(collection);
    }
}

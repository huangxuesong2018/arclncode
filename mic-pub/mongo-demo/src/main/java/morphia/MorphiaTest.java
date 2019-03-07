package morphia;

import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.internal.MongoClientImpl;
import morphia.entity.Member;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * Morphia 对MongoDB的支持
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
public class MorphiaTest {
    public static void main(String[] args) {
        final Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(new MongoClient("192.168.1.129:27017"),"testDB");

        Member member = new Member();
        member.setAddr("h n");
        member.setName("hxs");
        member.setAge(33);
        Key<Member> key = ds.save(member);
        System.out.println(key.getId());
    }
}

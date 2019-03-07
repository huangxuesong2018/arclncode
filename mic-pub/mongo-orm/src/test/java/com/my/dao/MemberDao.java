package com.my.dao;

import com.my.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.core.common.mongo.BaseDaoSupport;
import javax.core.common.mongo.QueryRule;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
@Repository
public class MemberDao extends BaseDaoSupport<Member,ObjectId> {

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        super.setMongoTemplate(mongoTemplate);
    }

    @Override
    protected String getPKColumn() {
        return "_id";
    }

    public List<Member> select(QueryRule queryRule) {
        return super.find(queryRule);
    }


    public int saveAll(List<Member> list) {
        return super.saveAll(list);
    }

    public Member getOne(ObjectId id){
        return super.get(id);
    }
}

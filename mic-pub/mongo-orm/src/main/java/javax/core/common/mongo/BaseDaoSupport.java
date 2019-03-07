package javax.core.common.mongo;

import com.mongodb.QueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.core.common.utils.GenericsUtils;
import java.io.Serializable;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
public abstract class BaseDaoSupport<T extends Serializable,PK extends Serializable> {
    private MongoTemplate mongoTemplate;
    private EntityOperation<T> operation;

    public BaseDaoSupport() {
        Class<T> tClass =  GenericsUtils.getSuperClassGenricType(getClass(),0);
        operation = new EntityOperation<>(tClass);
    }

    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    protected abstract String getPKColumn();

    protected List<T> find(QueryRule queryRule){
        QueryRuleBuilder queryRuleBuilder = new QueryRuleBuilder(queryRule);
        return mongoTemplate.find(queryRuleBuilder.getQuery(),operation.entityClass);
    }

    protected int saveAll(List<T> list){
        mongoTemplate.insertAll(list);
        return list.size();
    }

    protected T get(PK pk){
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.orEqual(getPKColumn(),pk);
        QueryRuleBuilder queryRuleBuilder = new QueryRuleBuilder(queryRule);
        return mongoTemplate.findOne(queryRuleBuilder.getQuery(),operation.entityClass);
    }

    protected int delete(T entity){
        return mongoTemplate.remove(entity).getN();
    }
}

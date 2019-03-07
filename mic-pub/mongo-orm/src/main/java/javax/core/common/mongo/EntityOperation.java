package javax.core.common.mongo;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-06
 */
public class EntityOperation<T> {
    public Class<T> entityClass = null;

    public EntityOperation(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}

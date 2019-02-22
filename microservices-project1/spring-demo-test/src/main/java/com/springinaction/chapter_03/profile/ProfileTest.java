package com.springinaction.chapter_03.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@ActiveProfiles(value = "dev")
public class ProfileTest {
    /**
     * 可以发现在能实例化出来了
     */
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource);
    }
}

package com.my.test;

import com.my.dao.MemberDao;
import com.my.entity.Member;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.core.common.mongo.QueryRule;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class TestDao {
    @Autowired
    private MemberDao memberDao;

    @Test
    public void testFind(){
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual("nickname","黄");
        List<Member> list = memberDao.select(queryRule);
        System.out.println(list);
    }

    @Test
    public void getOne(){
        Member member =  memberDao.getOne(new ObjectId("5c7f39afae93a24851780a3f"));
        System.out.println(member);
    }

    @Test
    public void testInsertAll(){
        List<Member> list = new ArrayList<>();
        list.add(new Member("黄","qqqqqq",1,33));
        list.add(new Member("ab","qqqqqq",1,33));
        list.add(new Member("ccd","qqqqqq",1,33));
        int r =  memberDao.saveAll(list);
        System.out.println(r);
    }
}

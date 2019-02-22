package com.springinaction.chapter_02.soundsystem.main;

import com.springinaction.chapter_02.soundsystem.bean.BlankDisc;
import com.springinaction.chapter_02.soundsystem.bean.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/PropertyRefTest-context.xml")
@ContextConfiguration(locations={"classpath:PropertyRefTest-context.xml"})
public class XmlTest {
    @Autowired
    private BlankDisc compactDisc;

    @Test
    public void t(){
        compactDisc.play();
    }
}

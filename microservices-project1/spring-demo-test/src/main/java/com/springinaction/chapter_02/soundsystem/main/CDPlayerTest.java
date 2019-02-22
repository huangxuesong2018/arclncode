package com.springinaction.chapter_02.soundsystem.main;

import com.springinaction.chapter_02.soundsystem.bean.BlankDisc;
import com.springinaction.chapter_02.soundsystem.bean.CompactDisc;
import com.springinaction.chapter_02.soundsystem.bean.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SoundSystemConfig.class)
public class CDPlayerTest {

  @Autowired
  private CompactDisc compactDisc;

  @Test
  public void cdShouldNotBeNull() {
      compactDisc.play();
  }


}

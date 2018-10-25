package com.berryjam.springcore;

import static org.junit.Assert.assertEquals;

import com.berryjam.springcore.beans.Client;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestContext {
    @Test
    public void testPropertyPlaceholderSystemOverride() {
        System.setProperty("id", "35");
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Client client = ctx.getBean(Client.class);
        ctx.close();

        assertEquals("35", client.getId());
    }

}

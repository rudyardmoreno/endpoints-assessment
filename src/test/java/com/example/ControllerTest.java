package com.example;

/*
 * Created by Rudyard Moreno on 04/02/2017
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Test Endpoints Controller
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EndpointsController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    //Test Endpoint 1 - Camelize
    @Test
    public void testCamelizeDefaultInitialCapVal() throws Exception{
        this.mvc.perform(get("/camelize?original=this_is_a_thing").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("thisIsAThing"));
    }
    @Test
    public void testCamelizeIniCapTrue() throws Exception{
        this.mvc.perform(get("/camelize?original=this_is_a_thing&initialCap=true").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("ThisIsAThing"));
    }
    @Test
    public void testCamelizeIniCapFalse() throws Exception{
        this.mvc.perform(get("/camelize?original=this_is_a_thing&initialCap=false").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("thisIsAThing"));
    }
    @Test
    public void testCamelizeIniCapX() throws Exception{
        this.mvc.perform(get("/camelize?original=this_is_a_thing&initialCap=X").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }

    //Test Endpoint 2 - Redact
    @Test
    public void testRedactTwoBadWords() throws Exception{
        this.mvc.perform(get("/redact?original=A little of this and a little of that&badWord=little&badWord=this").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("A ****** of **** and a ****** of that"));
    }
    @Test
    public void testRedactOneBadWord() throws Exception{
        this.mvc.perform(get("/redact?original=A little of this and a little of that&badWord=little").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("A ****** of this and a ****** of that"));
    }
    @Test
    public void testRedactNoneBadWord1() throws Exception{
        this.mvc.perform(get("/redact?original=A little of this and a little of that").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("A little of this and a little of that"));
    }
    @Test
    public void testRedactOneBadWord2() throws Exception{
        this.mvc.perform(get("/redact?original=A little of this and a little of that&original=Nothing&badWord=little").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Nothing"));
    }
    //Endpoint 3 - Encode
    @Test
    public void testEncode() throws Exception{
        MockHttpServletRequestBuilder request1 = post("/encode")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "a little of this and a little of that")
                .param("key", "mcxrstunopqabyzdefghijklvw");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("m aohhas zt hnog myr m aohhas zt hnmh"));
    }
    @Test
    public void testEncodeInvalidKey() throws Exception{
        String key="stunopqabyzdefghijklvw";
        String ALPHABET="abcdefghijklmnopqrstuvwzyzABCDEFGHIJKLMNOPQRSTUVWZYZ";
        String expectedResult=String.format("Invalid key (%s). It does not have same length than English alphabet (%s)",key + key.toUpperCase(),ALPHABET);

        MockHttpServletRequestBuilder request1 = post("/encode")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "a little of this and a little of that")
                .param("key", key);

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    //Endpoint 4 - Sed
    public void testSed() throws Exception{
        MockHttpServletRequestBuilder request1 = post("/s/little/lot")
                .contentType(MediaType.TEXT_PLAIN)
                .content("a little of this and a little of that");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("a lot of this and a lot of that"));
    }
}

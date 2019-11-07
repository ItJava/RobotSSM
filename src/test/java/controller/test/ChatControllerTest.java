package controller.test;

import com.alibaba.fastjson.JSONObject;
import com.ssm.promotion.core.entity.Buser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring-context.xml", "classpath*:/spring-context-mvc.xml", "classpath*:/mybatis-config.xml"})
public class ChatControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void addChatMsg() throws Exception {

        Random random = new Random(1);

        for(int i=0;i<10000;i++){
            String responseString = mockMvc.perform(
                    post("/chat/addChatMsg")    //请求的url,请求的方法是get
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("deviceId","deviceId"+random.nextInt(1000))
                             .param("chatQuestion","问题："+i)
                            .param("chatAnswer","答案:"+i)
                            .param("chatType",""+random.nextInt(10))
            )
                    .andExpect(status().isOk())    //返回的状态是200
                    .andDo(print())         //打印出请求和相应的内容
                    .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
            System.out.println("--------返回的json = " + responseString);
        }

    }
}
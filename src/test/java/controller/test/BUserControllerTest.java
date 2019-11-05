package controller.test;

import com.alibaba.fastjson.JSONObject;
import com.ssm.promotion.core.entity.Article;
import com.ssm.promotion.core.entity.Buser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Springmvc 单元测试类
 *
 * @author 13 2016-07-06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring-context.xml", "classpath*:/spring-context-mvc.xml", "classpath*:/mybatis-config.xml"})
public class BUserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

  /*  @Test
    public void testArticleList() throws Exception {
        //创建文章列表的请求
        //请求方式为get
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, "/articles");
        //此请求并不需要添加请求参数
        mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
                .andDo(print());

    }*/

    @Test
    public void testAddUserNoPic() throws Exception {
        //创建文章对象
        Buser buser = new Buser();

        buser.setUserPhone("15217764126");
        buser.setUserName("汪方晨");
        buser.setUserPwd("123456");
        buser.setRevision(0);
        buser.setUserAddress("上海");

        /*
        @RequestParam("userName") String buserName,
                               @RequestParam("pwd") String bPwd,
                               @RequestParam("bUserPhone") String bPhone,
                               @RequestParam("bUserAddress") String bAddress) {
         */

        String requestParam = JSONObject.toJSONString(buser);
        System.out.println("requestParam:->"+requestParam);
        //请求方式为post
//        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/busers/addUserNoPic");
//        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED).content(requestParam)).andExpect(status().isOk())
//                .andDo(print());
//



         String responseString = mockMvc.perform(
                post("/busers/addUserNoPic")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName","wangfangchen")
                        .param("pwd","pwd12313412")
                        //.param("bUserPhone","15217764126")
                        .param("bUserAddress","上海！")

         )
                 .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);




    }
}
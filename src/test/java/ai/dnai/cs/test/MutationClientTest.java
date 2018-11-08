package ai.dnai.cs.test;

import ai.dnai.cs.Application;
import ai.dnai.cs.api.rest.HotelController;
import ai.dnai.cs.client.ImageProperties;
import ai.dnai.cs.client.MutationClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
@TestPropertySource("classpath:application.yml")
public class MutationClientTest {

    @Autowired
    MutationClient mcli;


    @Test
    public void mutationClientTest() throws IOException {
        String r = mcli.concatReqBody(new ImageProperties("pisture.jpg", 126554654654L, 10, 20, "url://xxxxxxx.xxx"), getFeatures());
        System.out.println(r);
    }

    private String getFeatures() {
        return "{\n" +
                "  \"type\": \"FeatureCollection\",\n" +
                "  \"properties\": {\n" +
                "  },\n" +
                "  \"features\": [\n" +
                "    {\n" +
                "      \"type\": \"Feature\",\n" +
                "      \"geometry\": {\n" +
                "        \"type\": \"Polygon\",\n" +
                "        \"coordinates\": [\n" +
                "          [[1020, 886]],\n" +
                "          [[1018, 887]],\n" +
                "          [[1010, 887]],\n" +
                "          [[1010, 888]],\n" +
                "          [[1012, 889]],\n" +
                "          [[1012, 891]],\n" +
                "          [[1025, 891]],\n" +
                "          [[1026, 889]],\n" +
                "          [[1032, 889]],\n" +
                "          [[1032, 887]],\n" +
                "          [[1026, 887]],\n" +
                "          [[1025, 886]],\n" +
                "          [[1023, 887]],\n" +
                "          [[1021, 886]]\n" +
                "        ]\n" +
                "      },\n" +
                "      \"properties\": {\n" +
                "        \"uuid\": \"9357a3bc-e373-11e8-892f-4cedfb5e9794\",\n" +
                "        \"type\": \"aa\",\n" +
                "        \"severity\": \"HARD\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"Feature\",\n" +
                "      \"geometry\": {\n" +
                "        \"type\": \"Polygon\",\n" +
                "        \"coordinates\": [\n" +
                "          [[314, 886]],\n" +
                "          [[312, 887]],\n" +
                "          [[317, 891]],\n" +
                "          [[317, 892]],\n" +
                "          [[318, 893]],\n" +
                "          [[320, 892]],\n" +
                "          [[320, 889]],\n" +
                "          [[321, 888]],\n" +
                "          [[323, 888]],\n" +
                "          [[325, 887]],\n" +
                "          [[329, 887]],\n" +
                "          [[325, 887]],\n" +
                "          [[323, 886]]\n" +
                "        ]\n" +
                "      },\n" +
                "      \"properties\": {\n" +
                "        \"uuid\": \"9357a3bd-e373-11e8-892f-4cedfb5e9794\",\n" +
                "        \"type\": \"aa\",\n" +
                "        \"severity\": \"HARD\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"Feature\",\n" +
                "      \"geometry\": {\n" +
                "        \"type\": \"Polygon\",\n" +
                "        \"coordinates\": [[[825, 835]], [[826, 835]]]\n" +
                "      },\n" +
                "      \"properties\": {\n" +
                "        \"uuid\": \"9357a3be-e373-11e8-892f-4cedfb5e9794\",\n" +
                "        \"type\": \"aa\",\n" +
                "        \"severity\": \"HARD\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}

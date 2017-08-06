package tnmoc.punchedcard.awslambda;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class PowersTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = "{ card_content: \"ABC\" }";
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("GetCard");

        return ctx;
    }

    @Test
    public void testPowers() {
        Powers40ColumnCard handler = new Powers40ColumnCard();
        Context ctx = createContext();

        String output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("{\"interpretation\":[\"A\",\"B\",\"C\",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \",\" \"],\"rows\":12,\"columnFormat\":[\"B\",\"A\",\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"card\":[9,17,33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}", output);
    }
}

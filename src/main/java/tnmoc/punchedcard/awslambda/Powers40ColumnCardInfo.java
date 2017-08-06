package tnmoc.punchedcard.awslambda;

import org.json.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import tnmoc.punchedcards.powers.Powers40ColumnCardFormat;

public class Powers40ColumnCardInfo implements RequestHandler<Object, String> {
	Powers40ColumnCardFormat format = Powers40ColumnCardFormat.create();

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("introduction", format.getAboutInfo());
        jsonObject.put("card_format", format.getFormatInfo());
        jsonObject.put("rows", format.getRows());
        jsonObject.put("columns", format.getColumns() );
        jsonObject.put("imageUrl", "PowersCard2.jpg" );
        jsonObject.put("height", format.getHeight() );
        jsonObject.put("width", format.getWidth() );
        jsonObject.put("lPad", format.getLeftPad() );
        jsonObject.put("rPad", format.getRightPad() );
        jsonObject.put("tPad", format.getTopPad() );
        jsonObject.put("bPad", format.getBottomPad() );
        jsonObject.put("font", format.getColumnFormat() );
        jsonObject.put("holeClass", format.getHoleFormat().getClass().getName() );
        jsonObject.put("holeWidth", format.getHoleFormat().getBounds().width );
        jsonObject.put("holeHeight", format.getHoleFormat().getBounds().width );
    	return jsonObject.toString();
       

    }

}

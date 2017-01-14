package forest.rice.field.k.lambda;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ToysrusCheckRequestHandler implements RequestHandler<String, String> {

	@Override
	public String handleRequest(String input, Context context) {

		try {
			new ToysrusCheckRequestStreamHandler().handleRequest(null, null, context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

}

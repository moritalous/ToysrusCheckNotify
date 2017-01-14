package forest.rice.field.k.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import forest.rice.field.k.check.toysrus.ToysrusStockChecker;
import forest.rice.field.k.linenotify.LineNotify;
import forest.rice.field.k.linenotify.LineNotify.Sticker;

public class ToysrusCheckRequestStreamHandler implements RequestStreamHandler {

	private static final String CONTENTS_NO = System.getenv("CONTENTS_NO");

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		try {
			ToysrusStockChecker checker = new ToysrusStockChecker();
			String result = checker.check(CONTENTS_NO);

			if (result.contains("在庫あり")) {
				LineNotify.sendMessage(result, Sticker.POSITIVE);
			} else {
				LineNotify.sendMessage(result, Sticker.NEGATIVE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

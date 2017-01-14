package forest.rice.field.k.check.toysrus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ToysrusStockChecker {

	public static void main(String[] args) {
		new ToysrusStockChecker().check("dsg-504845100");
	}

	public String check(String contentsNo) {
		try {
			Document document = Jsoup.connect(createUrl(contentsNo)).get();

			List<Element> elements = extractStockElement(document);
			Element stock = elements.stream().filter(p -> !p.hasAttr("hidden")).findFirst().get();

			return stock.text();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "チェック失敗";
	}

	private String createUrl(String contentsNo) {
		return String.format("http://www.toysrus.co.jp/s/%s", contentsNo);
	}

	private List<Element> extractStockElement(Document document) {
		List<Element> elements = new ArrayList<>();

		elements = addElement(elements, document, "isStock_a");
		elements = addElement(elements, document, "isStock_b");
		elements = addElement(elements, document, "isStock_c");
		elements = addElement(elements, document, "isStock_d");

		return elements;
	}

	private List<Element> addElement(List<Element> elements, Document document, String id) {
		Element stockAElement = document.getElementById(id);
		if (stockAElement != null)
			elements.add(stockAElement);

		return elements;
	}

}

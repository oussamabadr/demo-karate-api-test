package or.badr.demokarate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class BackendServer {

	private static final WireMockServer WIRE_MOCK_SERVER
			= new WireMockServer(WireMockConfiguration.options().port(9090));


	public static void main(String[] args) {
		WIRE_MOCK_SERVER.start();
		configureFor("localhost", 9090);
		stubFor(
				get(urlEqualTo("/user/get"))
						.willReturn(aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "application/json")
								.withBody("{ \"id\": \"9876\", name: \"Bob Jack\" }")));

		stubFor(
				post(urlEqualTo("/user/create"))
						.withHeader("content-type", equalTo("application/json; charset=UTF-8"))
						.withRequestBody(containing("id"))
						.willReturn(aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "application/json")
								.withBody("{ \"id\": \"9876\", name: \"Fernandez Smith\" }")));
	}

	static void stopServer() {
		WIRE_MOCK_SERVER.stop();
	}

}

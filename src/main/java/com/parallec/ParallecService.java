package com.parallec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTask;
import io.parallec.core.ResponseOnSingleTask;

/**
 * @author mohitk
 *
 */
public class ParallecService {

	public void getResponse(String filePath) {
		ParallelClient pc = new ParallelClient();
		ParallelTask pt = pc.prepareHttpGet("").setConcurrency(1000).setTargetHostsFromString("www.ebay.com")
				.execute(new ParallecResponseHandler() {
					public void onCompleted(ResponseOnSingleTask res, Map<String, Object> responseContext) {
						File file = new File(filePath);
						if (!file.isDirectory()) {
							OutputStream os = null;
							try {
								os = new FileOutputStream(file);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try (OutputStreamWriter osw = new OutputStreamWriter(os)) {
								osw.write(res.getResponseContent());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				});
		pc.releaseExternalResources();
	}
}
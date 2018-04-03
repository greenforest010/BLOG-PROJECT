package com.growingitskill.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;
import com.growingitskill.config.WebAppInitializer;
import com.growingitskill.domain.GoogleAnalyticsReportingAPIVO;
import com.growingitskill.service.GoogleAnalyticsReportingAPIService;

@Component
public class AnalyticsReportingUtils {

	@Autowired
	private GoogleAnalyticsReportingAPIService googleAnalyticsReportingAPIService;

	private static final String APPLICATION_NAME = "BLOG Analytics Reporting";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private final String apiName = "Service Account";

	/**
	 * Initializes an Analytics Reporting API V4 service object.
	 *
	 * @return An authorized Analytics Reporting API V4 service object.
	 * @throws Exception 
	 */
	public AnalyticsReporting initializeAnalyticsReporting() throws Exception {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(getKeyFileLocation()))
				.createScoped(AnalyticsReportingScopes.all());

		// Construct the Analytics Reporting service object.
		return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Queries the Analytics Reporting API V4.
	 *
	 * @param service
	 *            An authorized Analytics Reporting API V4 service object.
	 * @return GetReportResponse The Analytics Reporting API V4 response.
	 * @throws Exception 
	 */
	public GetReportsResponse getReport(AnalyticsReporting service) throws Exception {
		// Create the DateRange object.
		DateRange dateRange = new DateRange();
		dateRange.setStartDate("2017-09-17");
		dateRange.setEndDate("today");

		// Create the Metrics object.
		Metric sessions = new Metric().setExpression("ga:newUsers").setAlias("newUsers");

		// Create the ReportRequest object.
		ReportRequest request = new ReportRequest().setViewId(getViewId()).setDateRanges(Arrays.asList(dateRange))
				.setMetrics(Arrays.asList(sessions));

		ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
		requests.add(request);

		// Create the GetReportsRequest object.
		GetReportsRequest getReport = new GetReportsRequest().setReportRequests(requests);

		// Call the batchGet method.
		GetReportsResponse response = service.reports().batchGet(getReport).execute();

		// Return the response.
		return response;
	}

	/**
	 * Parses and prints the Analytics Reporting API V4 response.
	 *
	 * @param response
	 *            An Analytics Reporting API V4 response.
	 * @throws Exception 
	 */
	public void printResponse(GetReportsResponse response) throws Exception {

		for (Report report : response.getReports()) {
			ColumnHeader header = report.getColumnHeader();
			List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
			List<ReportRow> rows = report.getData().getRows();

			if (rows == null) {
				System.out.println("No data found for " + getViewId());
				return;
			}

			for (ReportRow row : rows) {
				List<DateRangeValues> metrics = row.getMetrics();

				for (int j = 0; j < metrics.size(); j++) {
					System.out.print("Date Range (" + j + "): ");
					DateRangeValues values = metrics.get(j);
					for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
						System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
					}
				}
			}
		}
	}

	public String getNewVisitors(GetReportsResponse response) throws Exception {
		String result = "";

		for (Report report : response.getReports()) {
			ColumnHeader header = report.getColumnHeader();
			List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
			List<ReportRow> rows = report.getData().getRows();

			if (rows == null) {
				result = "No data found for " + getViewId();
				return result;
			}

			for (ReportRow row : rows) {
				List<DateRangeValues> metrics = row.getMetrics();

				for (int j = 0; j < metrics.size(); j++) {
					DateRangeValues values = metrics.get(j);

					for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
						result = values.getValues().get(k);
					}
				}
			}
		}

		return result;
	}
	
	private GoogleAnalyticsReportingAPIVO getGoogleAnalyticsReportingAPIVO(String apiName) throws Exception {
		return googleAnalyticsReportingAPIService.findGoogleAnalyticsReportingAPI(apiName);
	}

	private String getKeyFileLocation() throws Exception {
		String keyFileLocation = getGoogleAnalyticsReportingAPIVO(apiName).getKeyFileLocation();

		return WebAppInitializer.appBasePath + keyFileLocation;
	}
	
	private String getViewId() throws Exception {
		String viewId = getGoogleAnalyticsReportingAPIVO(apiName).getViewId();
		
		return viewId;
	}

}

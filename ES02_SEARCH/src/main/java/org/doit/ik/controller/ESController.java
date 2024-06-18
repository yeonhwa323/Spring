package org.doit.ik.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/es/*")
public class ESController {

	//목록보기
	@GetMapping(value="/list")
	public void list(Model model) {
		try {

			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
			/*
			 RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost or 엘라스틱서치 내부 IP", 9200, "http")));
			 */
			// GET <인덱스명> 요청객체
			SearchRequest searchRequest = new SearchRequest("spring");

			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(100);
			// 모든 도큐먼트 SELECT
			searchSourceBuilder.query(QueryBuilders.matchAllQuery());

			searchRequest.source(searchSourceBuilder);

			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
			// 
			SearchHits searchHits = response.getHits();

			for (SearchHit hit : searchHits) {
				Map<String,Object> sourceMap = hit.getSourceAsMap();
				sourceMap.put("id", hit.getId());
				list.add(sourceMap);
			}

			model.addAttribute("list", list);

			client.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//추가하기(폼)
	@GetMapping(value="/add")
	public void add() {


	}

	//추가하기(처리)
	@PostMapping(value="/addok")
	public String addok(Model model
			, @RequestParam("id") String id
			, @RequestParam("message") String message) {
		try {
			// 1.
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
			// 2.
			String data = String.format("{\"message\":\"%s\"}", message);
			// 3.
			IndexRequest request = new IndexRequest("spring")
					.source(data, XContentType.JSON)
					.setRefreshPolicy("wait_for");
			request.id(id);

			IndexResponse response = client.index(request, RequestOptions.DEFAULT);

			client.close();

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("e", e);
		}

		return "redirect:/es/list";

	}

}

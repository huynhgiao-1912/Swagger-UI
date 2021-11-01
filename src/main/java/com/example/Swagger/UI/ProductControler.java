package com.example.Swagger.UI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.Lambda;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.aggregations.metrics.TopHitsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductControler {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private  ProductRepository productRepository;

    @GetMapping(value = "/")
    public List<Product> findAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.size(100);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Product> products = new ArrayList<>();
        Gson gson = new Gson();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            Product product = gson.fromJson(searchHit.getSourceAsString(),Product.class);
            products.add(product);
        }
        return products;
    }
    @GetMapping(value = "/category/{id}")
    public  List<Product> findByCategory(@PathVariable String id) throws  IOException{
        List<Product> products = new ArrayList<>();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("CategoryID", id)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(2)
                .maxExpansions(10);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(20);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        Gson gson = new Gson();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            Product product = gson.fromJson(searchHit.getSourceAsString(),Product.class);
            products.add(product);
        }
        return products;
    }
    @GetMapping(value = "/add")
    public  String addProduct() throws IOException{
        List<Product> lst = new ArrayList<>();
        lst.add(new Product("1","Mi Hao Hao",4,"1"));
        lst.add(new Product("2","Mi 3 Mien",3,"1"));
        lst.add(new Product("3","Mi Omachi",6,"1"));
        lst.add(new Product("4","Mi Đe Nhat",4,"1"));
        lst.add(new Product("5","Mi Lau Thai",5,"1"));
        lst.add(new Product("6","Mi Kokomi",7,"1"));
        lst.add(new Product("7","Mi Cay Han Quoc",12,"1"));
        lst.add(new Product("8","Mi MiLiket",8,"1"));
        lst.add(new Product("9","Bia Sai Gon",9,"2"));
        lst.add(new Product("10","Bia Tiger",10,"2"));
        lst.add(new Product("11","Bia Heineken",13,"2"));
        lst.add(new Product("12","Bia Budweiser",11,"2"));
        lst.add(new Product("13","Bia Sapporo",17,"2"));
        lst.add(new Product("14","Bia Beck's",11,"2"));
        lst.add(new Product("15","Bia Larue",11,"2"));
        lst.add(new Product("16","Bia Hoegaarden",12,"2"));
        lst.add(new Product("17","Bia 333",8,"2"));
        lst.add(new Product("18","Ruou Soju",56,"3"));
        lst.add(new Product("19","Ruou Bokbunja",47,"3"));
        lst.add(new Product("20","Ruou Makgeolli-Makkolli",37,"3"));
        lst.add(new Product("21","Ruou Sansachun",58,"3"));
        lst.add(new Product("22","Ruou Baeseju",40,"3"));
        lst.add(new Product("23","Nuoc Ngot Han Quoc",15,"4"));
        lst.add(new Product("24","Pesi",7,"4"));
        lst.add(new Product("25","CocaCola",7,"4"));
        lst.add(new Product("26","Tra Xanh",6,"4"));
        lst.add(new Product("27","7up",6,"4"));
        lst.add(new Product("28","NumberOne",10,"4"));
        lst.add(new Product("29","Sting",10,"4"));
        lst.add(new Product("30","Mirinda",10,"4"));
        lst.add(new Product("31","Reb bull",15,"4"));
        lst.add(new Product("32","Nuoc Cam Ep",9,"4"));
        lst.add(new Product("33","Tra C2",8,"4"));
        lst.add(new Product("34","Tra Olong",7,"4"));
        lst.add(new Product("35","Xa Xi",8,"4"));
        lst.add(new Product("36","Rong Đo",6,"4"));
        lst.add(new Product("37","Tra Sua",9,"4"));
        lst.add(new Product("38","Wake up",9,"4"));
        lst.add(new Product("39","Cafe Viet",10,"4"));
        lst.add(new Product("40","Soda",6,"4"));
        lst.add(new Product("41","Banh My Otto",9,"5"));
        lst.add(new Product("42","Banh My Staff",9,"5"));
        lst.add(new Product("43","Banh My Scotti",8,"5"));
        lst.add(new Product("44","Banh Bao Tho Phat",12,"5"));
        lst.add(new Product("45","Banh Bao Phu My",12,"5"));
        lst.add(new Product("46","Banh Quy Kem",22,"5"));
        lst.add(new Product("47","Banh Que",18,"5"));
        lst.add(new Product("48","Chao Yen Viet",9,"6"));
        lst.add(new Product("49","Chao Gau Đo",5,"6"));
        lst.add(new Product("50","Chao Thit Bam",5,"6"));
        lst.add(new Product("51","Chao Đau Xanh",5,"6"));
        lst.add(new Product("52","Chao Bi Đo",5,"6"));
        productRepository.saveAll(lst);
        return "Add Product Succesfull";
    }

    @GetMapping(value = "/thongke")
    public SearchResponse count() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("product");
        SearchSourceBuilder sb = new SearchSourceBuilder();
        sb.fetchSource(new String[]{"Price","ProductName","ProductID","CategoryID"},null);
        sb.aggregation(AggregationBuilders.terms("categoryID")
                .field("CategoryID.keyword")
                .subAggregation(new TopHitsAggregationBuilder("topHistProduct")
                        .fetchSource(new String[]{"Price","ProductName","ProductID","CategoryID"},null)
                        .size(100)));
        searchRequest.source(sb);
        SearchResponse searchResponse = null;
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Terms genders = searchResponse.getAggregations().get("categoryID");
        genders.getBuckets().stream().map(v -> (TopHits) v.getAggregations().get("topHistProduct"))
                .forEach(h -> h.getHits().forEach(v -> {

                }));
        return searchResponse;
    }
}

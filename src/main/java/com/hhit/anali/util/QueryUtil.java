package com.hhit.anali.util;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;


@Component
public class QueryUtil {
    @Resource
    private ElasticsearchOperations es;

    /**
     * 查询遍历抽取  "health"   "logs"
     * @param queryBuilder
     */
    public SearchResponse searchFunction(QueryBuilder queryBuilder, String index, String types) {
        SearchResponse response = es.getClient()
                .prepareSearch(index)
                .setTypes(types)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .setSize(100).execute().actionGet();

//        SearchResponse response1 = null;
//        try {
//            response1 = es.getClient()
//                    .prepareSearch("jun").setQuery(queryBuilder)
//                    .execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        while(true) {
//            response1 = es.getClient()
//                    .prepareSearchScroll(response1.getScrollId())
//                    .setScroll(new TimeValue(60000))
//                    .execute().actionGet();
//        for (SearchHit hit : response.getHits()) {
//            for (Map.Entry<String, Object> next : hit.getSource().entrySet()) {
//                System.out.println(next.getKey() + ": " + next.getValue());
//                if (response.getHits().totalHits == 0) {
//                    break;
//                }
//            }
//        }
        return  response;

    }

    /**
     * 对response结果的分析
     * @param response
     */
    public SearchHits testResponse(SearchResponse response) {
        // 命中的记录数
        long totalHits = response.getHits().getHits().length;

        return response.getHits();
//        for (SearchHit searchHit : response.getHits()) {
//            // 打分
//            float score = searchHit.getScore();
//            // 文章id
//            int id = Integer.parseInt(searchHit.getSource().get("id").toString());
//            // title
////            String title = searchHit.getSource().get("type").toString();
//            // 内容
////            String content = searchHit.getSource().get("doseage").toString();
////            // 文章更新时间
////            String updatetime = searchHit.getSource().get("releaseDate").toString();
//
//            System.out.println(score+"::"+id);
//        }
//        System.out.println(totalHits);
    }

    public String analy(String text) {

//        String text = "适用于敏感葡萄球菌、链球菌属和肺炎链球菌所致的轻、中度感染，如咽炎、扁桃体炎、鼻窦炎、中耳炎、牙周炎、急性支气管炎、慢性支气管炎急性发作、肺炎、非淋菌性尿道炎、皮肤软组织感染，亦可用于隐孢子虫病、或作为治疗妊娠期妇女弓形体病的选用药物。";
        StringReader sr = new StringReader(text);
        IKSegmenter ik = new IKSegmenter(sr, true);
        Lexeme lex = null;
        StringBuilder ana = new StringBuilder();
        while (true) {
            try {
                if ((lex = ik.next()) == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert lex != null;
           ana.append(lex.getLexemeText()).append(",");
        }
        return String.valueOf(ana);
    }


    /**
     * 对结果设置高亮显示
     */
    public void testHighLighted() {
        /*  5.0 版本后的高亮设置
         * es.getClient().#().#().highlighter(hBuilder).execute().actionGet();
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.preTags("<h2>");
        hBuilder.postTags("</h2>");
        hBuilder.field("user");        // 设置高亮显示的字段
        */
        // 加入查询中
        SearchResponse response = es.getClient().prepareSearch("health")
                .setQuery(QueryBuilders.matchAllQuery())
//                .addHighlightedField("user")        // 添加高亮的字段
//                .setHighlighterPreTags("<h1>")
//                .setHighlighterPostTags("</h1>")
                .execute().actionGet();

        // 遍历结果, 获取高亮片段
        SearchHits searchHits = response.getHits();
        for(SearchHit hit:searchHits){
            System.out.println("String方式打印文档搜索内容:");
            System.out.println(hit.getSourceAsString());
            System.out.println("Map方式打印高亮内容");
            System.out.println(hit.getHighlightFields());

            System.out.println("遍历高亮集合，打印高亮片段:");
            Text[] text = hit.getHighlightFields().get("title").getFragments();
            for (Text str : text) {
                System.out.println(str.string());
            }
        }
    }
}

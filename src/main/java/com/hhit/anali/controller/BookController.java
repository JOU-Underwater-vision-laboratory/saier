package com.hhit.anali.controller;

import com.hhit.Application;
import com.hhit.anali.pojo.Book;
import com.hhit.anali.service.BookService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

// alter table drug drop column type;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class BookController {
    @Resource
    private ElasticsearchOperations es;
    @Resource
    private BookService bookService;

    @Test
    public void demo01(){
        printElasticSearchInfo();

//        Book b1=bookService.save(new Book("1001", "这是一个Spring Boot1.0的书籍", "Rambabu Posa", "23-FEB-2017"));
//        Book b2=bookService.save(new Book("1002", "Apache的一本书籍", "Rambabu Posa", "13-MAR-2017"));
        bookService.save(new Book("1003", "Solr是一个搜索引擎", "Rambabu Posa", "21-MAR-2017"));
        bookService.save(new Book("1004", "elasticsearch是一个搜索引擎", "Rambabu pppa", "21-MAD-2017"));
        //fuzzey search
//        Page<Book> books = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));

        Page<Book> books = bookService.findByTitle("Rambabu", new PageRequest(0, 10));

        books.forEach(System.out::println);
    }

    //useful for debug
    private void printElasticSearchInfo() {

        System.out.println("<--ElasticSearch-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();
        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearch-->");
    }

    /**
     *  index , type
     */
    @Test
    public void testGet() {
        GetRequestBuilder requestBuilder = es.getClient().prepareGet("jun", "books", "1001");
        GetResponse response = requestBuilder.execute().actionGet();
        GetResponse getResponse = requestBuilder.get();
        ListenableActionFuture<GetResponse> execute = requestBuilder.execute();

        System.out.println(response.getSourceAsString());
        System.out.println(getResponse .getSourceAsString());
    }

    /**
     * 使用QueryBuilder
     * termQuery("key", obj) 完全匹配
     * termsQuery("key", obj1, obj2..)   一次匹配多个值
     * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
     * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
     * matchAllQuery();         匹配所有文件
     */
    @Test
    public void testQueryBuilder() {
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("id", "1001");
//        QueryBuilders.termsQuery("user", new ArrayList<String>().add("kimchy"));
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id", "1001");
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("kimchy", "user", "message", "gender");
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        searchFunction(queryBuilder);

    }

    /**
     * 组合查询
     * must(QueryBuilders) :   AND
     * mustNot(QueryBuilders): NOT
     * should:                  : OR
     */
    @Test
    public void testQueryBuilder2() {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("id", "75213"));
//                .mustNot(QueryBuilders.termQuery("message", "nihao"))
//                .should(QueryBuilders.termQuery("gender", "male"));
        searchFunction(queryBuilder);
    }

    /**
     * 只查询一个id的
     * QueryBuilders.idsQuery(String...type).ids(Collection<String> ids)
     */
//    @Test
//    public void testIdsQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.idsQuery().ids();
//        searchFunction(queryBuilder);
//    }

    /**
     * 包裹查询, 高于设定分数, 不计算相关性
     */
    @Test
    public void testConstantScoreQuery() {
        QueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("id", "1001")).boost(2.0f);
        searchFunction(queryBuilder);
        // 过滤查询
//        QueryBuilders.constantScoreQuery(FilterBuilders.termQuery("name", "kimchy")).boost(2.0f);

    }

    /**
     * disMax查询
     * 对子查询的结果做union, score沿用子查询score的最大值,
     * 广泛用于muti-field查询
     */
    @Test
    public void testDisMaxQuery() {
        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.termQuery("title", "Apache"))  // 查询条件
                .add(QueryBuilders.termQuery("author", "Rambabu"))
                .boost(1.3f)
                .tieBreaker(0.7f);
        searchFunction(queryBuilder);
    }

    /**
     * 模糊查询
     *      匹配filed中的value，类似于String.contains();不做分词
     */
    @Test
    public void testFuzzyQuery() {
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("dosage", "帕金森症，偏头痛");
        searchFunction(queryBuilder);
    }

    /**
     * moreLikeThisQuery: 实现基于内容推荐, 支持实现一句话相似文章查询
     * {
     "more_like_this" : {
     "fields" : ["title", "content"],   // 要匹配的字段, 不填默认_all
     "like_text" : "text like this one",   // 匹配的文本
     }
     }

     percent_terms_to_match：匹配项（term）的百分比，默认是0.3

     min_term_freq：一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2

     max_query_terms：一条查询语句中允许最多查询词语的个数，默认是25

     stop_words：设置停止词，匹配时会忽略停止词

     min_doc_freq：一个词语最少在多少篇文档中出现，小于这个值的词会将被忽略，默认是无限制

     max_doc_freq：一个词语最多在多少篇文档中出现，大于这个值的词会将被忽略，默认是无限制

     min_word_len：最小的词语长度，默认是0

     max_word_len：最多的词语长度，默认无限制

     boost_terms：设置词语权重，默认是1

     boost：设置查询权重，默认是1

     analyzer：设置使用的分词器，默认是使用该字段指定的分词器
     */
//    @Test
//    public void testMoreLikeThisQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery(new String[]{"user"},"kimchy");
//
////                            .minTermFreq(1)         //最少出现的次数
////                            .maxQueryTerms(12);        // 最多允许查询的词语
//        searchFunction(queryBuilder);
//    }

    /**
     * 前缀查询
     * match query and multi-match query //and match-all query and minimum should match query
     *
     * match queries没有“query parsing”的过程，field不支持通配符，前缀等高级特性，只是参照指定的文本进行analysis，执行query，因此失败几率极小，适合search-box。
     *
     * analyzed类型的query，故可指定analyzer
     *
     * operator可指定or/and
     *
     * zero-terms-query可指定none/all
     *
     * cutoff-frequency可指定absolute值或者relative值
     *
     * match-phase query可指定slot值，参见后续的search-in-depth
     *
     * match-phase-prefix query可指定max_expansion
     */
    @Test
    public void testPrefixQuery() {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("dosage", "帕金森症，偏头痛,心血管疾病，增加，反应调节剂量，麦角衍生物").analyzer("ik_smart");
        searchFunction(queryBuilder);
    }

    /**
     * 查询解析查询字符串
     *  document中匹配
     */
    @Test
    public void testQueryString() {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("帕金森+");
        searchFunction(queryBuilder);
    }

    /**
     * 范围内查询
     */
    public void testRangeQuery() {
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("author")
                .from("Posa")
                .to("pppa")
                .includeLower(true)     // 包含上界
                .includeUpper(true);      // 包含下届
        searchFunction(queryBuilder);
    }

    /**
     * 跨度查询
     */
    @Test
    public void testSpanQueries() {
        QueryBuilder queryBuilder1 = QueryBuilders.spanFirstQuery(QueryBuilders.spanTermQuery("dosage", "帕金森症"), 30000);     // Max查询范围的结束位置
        // Span Term
        QueryBuilder queryBuilder5 = QueryBuilders.spanTermQuery("dosage", "帕金森症，偏头痛");
    }

    /**
     * 测试子查询
     */
//    @Test
//    public void testTopChildrenQuery() {
//        QueryBuilders.hasChildQuery("tweet",
//                QueryBuilders.termQuery("user", "kimchy"))
//                .scoreMode("max");
//    }

    /**
     * 通配符查询, 支持 *
     * 匹配任何字符序列, 包括空
     * 避免* 开始, 会检索大量内容造成效率缓慢
     */
    @Test
    public void testWildCardQuery() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("dosage", "帕金森症**，帕金森症**，**偏头痛**");
        searchFunction(queryBuilder);
    }

    /**
     * 嵌套查询, 内嵌文档查询
     */
//    @Test
//    public void testNestedQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.nestedQuery("location", QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchQuery("location.lat", 0.962590433140581))
//                        .must(QueryBuilders.rangeQuery("location.lon").lt(36.0000).gt(0.000)))
//                        .scoreMode("total");
//
//    }

    /**
     * 测试索引查询
     */
    @Test
    public void testIndicesQueryBuilder () {
        QueryBuilder queryBuilder = QueryBuilders.indicesQuery(
                QueryBuilders.termQuery("id", "1001"), "books", "Books")
                .noMatchQuery(QueryBuilders.termQuery("id", "1002"));

    }



    /**
     * 查询遍历抽取
     * @param queryBuilder
     */
    private void searchFunction(QueryBuilder queryBuilder) {
        SearchResponse response = es.getClient()
                .prepareSearch("health")
                .setTypes("logs")
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
            for (SearchHit hit : response.getHits()) {
                for (Map.Entry<String, Object> next : hit.getSource().entrySet()) {
                    System.out.println(next.getKey() + ": " + next.getValue());
                    if (response.getHits().totalHits == 0) {
                        break;
                    }
                }
            }
//            break;
//        }
        testResponse(response);
    }

    /**
     * 对response结果的分析
     * @param response
     */
    public void testResponse(SearchResponse response) {
        // 命中的记录数
        long totalHits = response.getHits().getHits().length;

        for (SearchHit searchHit : response.getHits()) {
            // 打分
            float score = searchHit.getScore();
            // 文章id
            int id = Integer.parseInt(searchHit.getSource().get("id").toString());
            // title
//            String title = searchHit.getSource().get("type").toString();
            // 内容
//            String content = searchHit.getSource().get("doseage").toString();
//            // 文章更新时间
//            String updatetime = searchHit.getSource().get("releaseDate").toString();

            System.out.println(score+"::"+id);
        }
        System.out.println(totalHits);
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


    @Test
    public void analy() {

        String text = "适用于敏感葡萄球菌、链球菌属和肺炎链球菌所致的轻、中度感染，如咽炎、扁桃体炎、鼻窦炎、中耳炎、牙周炎、急性支气管炎、慢性支气管炎急性发作、肺炎、非淋菌性尿道炎、皮肤软组织感染，亦可用于隐孢子虫病、或作为治疗妊娠期妇女弓形体病的选用药物。";
        StringReader sr = new StringReader(text);
        IKSegmenter ik = new IKSegmenter(sr, true);
        Lexeme lex = null;
        while (true) {
            try {
                if ((lex = ik.next()) == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert lex != null;
            System.out.print(lex.getLexemeText() + ",");
        }
    }
}

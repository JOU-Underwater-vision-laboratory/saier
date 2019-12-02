//package com.hhit.anali.util;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.IntPoint;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.search.BooleanClause;
//import org.apache.lucene.search.BooleanClause.Occur;
//import org.apache.lucene.search.BooleanQuery;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.FSDirectory;
//import org.junit.Test;
//
///**
// * 测试lucene多种查询功能
// * @author Administrator
// *
// */
//public class SearchIndex {
//
//	//词项查询,所有查询中最基础的,给定的查询条件中封装了
//	//一个分词结果中的词项
//	@Test
//	public void termQuery() throws IOException{
//		//第一步,指定索引目录
//		Path path = Paths.get("./index01");
//		FSDirectory dir = FSDirectory.open(path);
//		//第二步,生成查询的对象,基于输入流完成的
//		DirectoryReader reader = DirectoryReader.open(dir);
//		IndexSearcher search=new IndexSearcher(reader);
//		//第三步,实现词项查询,创建一个TermQuery查询对象,指定查询某个域的某一
//		//词语
//		Term term=new Term("content","习近平");
//		Query query=new TermQuery(term);
//		//第四步,获取查询结果集,遍历循环,获取doc中域的数据
//		TopDocs docs = search.search(query, 10);//利用query,获取10条数据
//		ScoreDoc[] scoreDocs = docs.scoreDocs;//对doc存储在索引时,
//		//是直接存储,但是查询的条件不同,不同doc数据的匹配不一样的,体现在评分
//		for (ScoreDoc scoreDoc : scoreDocs) {
//			//获取doc对象
//			Document doc=search.doc(scoreDoc.doc);
//			System.out.println("评分"+scoreDoc.score);
//			//挨个打印从索引中获取的数据
//			System.out.println("category:"+doc.get("category"));
//			System.out.println("content:"+doc.get("content"));
//			System.out.println("publisher:"+doc.get("publisher"));
//		}
//	}
//	//多域查询,利用不同域的词项来进行查询
//	//MultiFieldQuery,实现多个不同的域名
//	@Test
//	public void multiFieldQuery() throws Exception{
//		Path path = Paths.get("./index01");
//		FSDirectory dir = FSDirectory.open(path);
//		DirectoryReader reader = DirectoryReader.open(dir);
//		IndexSearcher search=new IndexSearcher(reader);
//
//		//执行域的范围,String[]
//		String[] fields={"content","menu"};
//		//定义一个转化器,字符串查询条件,需要先进行分词计算,得到多个词项
//		//只要词项中有任意一个分词结果对应2个域有匹配,查询就定位到了doc
//		Analyzer analyzer=new IKAnalyzer();
//		MultiFieldQueryParser parser=
//				new MultiFieldQueryParser(fields,analyzer);
//		//生成查询query对象
//		Query query=parser.parse("一轮中美");
//		//"一轮中美"分词计算 "一","一轮","中美" 查询结果对应不同的域,结果的集合
//		//是或的关系,任意一个分词结果有域的数据,就将doc返回,匹配分词个数不一样
//		//评分不同
//
//		TopDocs docs = search.search(query, 10);
//		ScoreDoc[] scoreDocs = docs.scoreDocs;
//		for (ScoreDoc scoreDoc : scoreDocs) {
//			//获取doc对象
//			Document doc=search.doc(scoreDoc.doc);
//			System.out.println("评分"+scoreDoc.score);
//			//挨个打印从索引中获取的数据
//			System.out.println("category:"+doc.get("category"));
//			System.out.println("content:"+doc.get("content"));
//			System.out.println("publisher:"+doc.get("publisher"));
//			System.out.println("menu:"+doc.get("menu"));
//			System.out.println("name:"+doc.get("name"));
//			System.out.println("cost:"+doc.get("cost"));
//		}
//	}
//
//	//布尔查询,多个query对象可以形成布尔查询query的条件
//	@Test
//	public void booleanQuery() throws Exception{
//		Path path = Paths.get("./index01");
//		FSDirectory dir = FSDirectory.open(path);
//		DirectoryReader reader = DirectoryReader.open(dir);
//		IndexSearcher search=new IndexSearcher(reader);
//
//		//定义2个query条件(TermQuery)
//		Query query1=new TermQuery(new Term("content","中美"));
//		Query query2=new TermQuery(new Term("name","西部"));
//		//MUST,MUST_NOT,SHOULD,FILTER
//		//MUST,这个条件封装的query对象必须在查询结果中包含
//		//MUST_NOT,query对象的查询结果必须不在document中包含
//		//SHOULD 同一批条件中如有有MUST存在,should就不起作用了;
//		//FILTER 和MUST一模一样,但是查询时不进行评分计算,所有评分都一致
//		BooleanClause bc1=new BooleanClause(query1, Occur.FILTER);
//		BooleanClause bc2=new BooleanClause(query2,Occur.FILTER);
//		//使用2个条件生成query对象
//		Query query=new BooleanQuery.
//				Builder().add(bc1).add(bc2).build();
//
//		TopDocs docs = search.search(query, 10);
//		ScoreDoc[] scoreDocs = docs.scoreDocs;
//		for (ScoreDoc scoreDoc : scoreDocs) {
//			//获取doc对象
//			Document doc=search.doc(scoreDoc.doc);
//			System.out.println("评分"+scoreDoc.score);
//			//挨个打印从索引中获取的数据
//			System.out.println("category:"+doc.get("category"));
//			System.out.println("content:"+doc.get("content"));
//			System.out.println("publisher:"+doc.get("publisher"));
//			System.out.println("menu:"+doc.get("menu"));
//			System.out.println("name:"+doc.get("name"));
//			System.out.println("cost:"+doc.get("cost"));
//		}
//	}
//	//范围查询,只针对具有数字特性的域
//	@Test
//	public void rangeQuery() throws Exception{
//		Path path = Paths.get("./index01");
//		FSDirectory dir = FSDirectory.open(path);
//		DirectoryReader reader = DirectoryReader.open(dir);
//		IndexSearcher search=new IndexSearcher(reader);
//		//范围查询的数字特性是什么类型的,就用哪种类型的Point获取条件
//		Query query=IntPoint.newRangeQuery("cost", 50, 100);
//
//		TopDocs docs = search.search(query, 10);
//		ScoreDoc[] scoreDocs = docs.scoreDocs;
//		for (ScoreDoc scoreDoc : scoreDocs) {
//			//获取doc对象
//			Document doc=search.doc(scoreDoc.doc);
//			System.out.println("评分"+scoreDoc.score);
//			//挨个打印从索引中获取的数据
//			System.out.println("category:"+doc.get("category"));
//			System.out.println("content:"+doc.get("content"));
//			System.out.println("publisher:"+doc.get("publisher"));
//			System.out.println("menu:"+doc.get("menu"));
//			System.out.println("name:"+doc.get("name"));
//			System.out.println("cost:"+doc.get("cost"));
//		}
//
//	}
//
//}
//
//
//
//
//
//
//
//
//
//

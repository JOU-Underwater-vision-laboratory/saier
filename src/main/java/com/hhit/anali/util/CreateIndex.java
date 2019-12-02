//package com.hhit.anali.util;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field.Store;
//import org.apache.lucene.document.IntPoint;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.junit.Test;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// *利用luceneapi实现存储在windows系统的
// *索引文件
// */
//public class CreateIndex {
//
//	@Test
//	public void createIndex() throws Exception{
//		//第一步,指定一个文件地址
//		Path path = Paths.get("./index01");
//		//由path 生成lucene中的目录对象directory
//		Directory dir = FSDirectory.open(path);
//		//第二步,生成输出流配置对象,并且指定分词器对象IK
//		Analyzer analyzer=new IKAnalyzer();
//		IndexWriterConfig config=
//				new IndexWriterConfig(analyzer);
//		//设置一些配置内容,设置一个创建索引的逻辑
//		//OpenMode.Create 创建时覆盖已有的文件
//		//OpenMode.Append 创建时追加数据到已有的文件
//		//OpenMode.Create_or_Append 有就追加,没有就创建
//		config.setOpenMode(OpenMode.CREATE);
//		//第三步创建一个Writer输出对象
//		IndexWriter writer=new IndexWriter(dir,config);
//		//第四步 收集数据,封装document对象
//		//做2个document,一个存新闻网页信息,一个存储饿了吗店铺信息
//		Document doc1=new Document();
//		Document doc2=new Document();
//		//手动向对象doc中添加数据,
//		doc1.add(new StringField("category","国内新闻",Store.YES));
//		doc1.add(new StringField("publisher","新华网",Store.YES));
//		doc1.add(new TextField("content","国家主席习近平15日在人民大"
//				+ "会堂会见来华进行新一轮中美经贸高级别磋商的"
//				+ "美国贸易代表莱特希泽和财政部长姆努钦。",Store.NO));
//
//		doc2.add(new TextField("name","西部马华",Store.YES));
//		doc2.add(new TextField("content", "中美",Store.YES));
//		doc2.add(new TextField("menu","牛肉拉面,烤肉串,一",Store.YES));
//		doc2.add(new IntPoint("cost", 85));
//		doc2.add(new StringField("cost","85",Store.YES));
//		//第五步 将document输出到索引文件(对document中的数据
//		//进行倒排索引的创建)
//		writer.addDocument(doc1);
//		writer.addDocument(doc2);
//		writer.commit();//对doc中的各种域,根据域类型进行分词计算
//	}
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
//
//
//
//
//
//
//
//

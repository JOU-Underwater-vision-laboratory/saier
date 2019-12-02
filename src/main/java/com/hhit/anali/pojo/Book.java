package com.hhit.anali.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/* indexName 可以看作数据库， type 可以看作数据表*/

/**
 *  String indexName();//索引库的名称，个人建议以项目的名称命名
 * 	String type() default "";//类型，个人建议以实体的名称命名
 * 	short shards() default 5;//默认分区数
 * 	short replicas() default 1;//每个分区默认的备份数
 * 	String refreshInterval() default "1s";//刷新间隔
 * 	String indexStoreType() default "fs";//索引文件存储类型
 */
@Document(indexName = "jun", type = "books")
public class Book {

    @Id
    private String id;
    @Field(analyzer="ik",searchAnalyzer="ik")
    private String title;
    @Field(analyzer="ik",searchAnalyzer="ik")
    private String author;
    @Field(analyzer="ik",searchAnalyzer="ik")
    private String releaseDate;

    public Book() {
    }

    public Book(String id, String title, String author, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}

//package com.outdoors.hobbies.services;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queries.mlt.MoreLikeThis;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopScoreDocCollector;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.store.RAMDirectory;
//import org.apache.lucene.util.Version;
//
///**
// * Hello world!
// *
// */
//public class Test {
//	public static void main(String[] args) throws IOException, ParseException {
//		// 0. Specify the analyzer for tokenizing text.
//		// The same analyzer should be used for indexing and searching
//		StandardAnalyzer analyzer = new StandardAnalyzer();
//		// 1. create the index
//		// Path path = Paths.get("/train")
//		File directory = new File("./train");
//
//		File indexDir = new File("./luceneIndex");
//		Directory dir = FSDirectory.open(indexDir);
//
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//
//		if (indexDir.listFiles().length == 0) {
//			IndexWriter w = new IndexWriter(dir, config);
//			for (File file : directory.listFiles()) {
//				System.out.println(file.getPath());
//				List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
//				String subject = lines.get(1).replaceAll("Subject:", "").replaceAll("Re:", "");
//				String content = "";
//				for (int i = 2; i < lines.size(); i++) {
//					content = content + '\n' + lines.get(i);
//				}
//				addDoc(w, file.getName(), subject, content);
//			}
//			w.close();
//		}
//		// 2. query
//		// String querystr = "brother";
//
//		// Query q = new QueryParser("content", analyzer).parse(querystr);
//		// 3. search
//		int hitsPerPage = 10;
//		IndexReader reader = DirectoryReader.open(dir);
//		// System.out.println(reader.document(1).getField("content"));
//		IndexSearcher searcher = new IndexSearcher(reader);
//
//		MoreLikeThis mlt = new MoreLikeThis(reader);
//		mlt.setAnalyzer(analyzer);
//		mlt.setFieldNames(new String[] { "content" });
//		Query q = mlt.like(1);
//
//		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
//		searcher.search(q, collector);
//		ScoreDoc[] hits = collector.topDocs().scoreDocs;
//
//		// 4. display results
//		System.out.println("Found " + hits.length + " hits.");
//		for (int i = 0; i < hits.length; ++i) {
//			int docId = hits[i].doc;
//			Document d = searcher.doc(docId);
//			System.out.println((i + 1) + ". " + "\t" + d.get("title"));
//		}
//
//		// System.out.println(mlt.retrieveInterestingTerms(10));
//		// reader can only be closed when there
//		// is no need to access the documents any more.
//		reader.close();
//	}
//
//	private static void createUserProfile() {
//
//	}
//
//	private static void addDoc(IndexWriter w, String title, String subject, String content) throws IOException {
//		Document doc = new Document();
//		// use a string field for isbn because we don't want it tokenized
//		doc.add(new StringField("title", title, Field.Store.YES));
//		doc.add(new TextField("subject", subject, Field.Store.YES));
//		doc.add(new TextField("content", content, Field.Store.YES));
//		System.out.println(content);
//		w.addDocument(doc);
//	}
//}

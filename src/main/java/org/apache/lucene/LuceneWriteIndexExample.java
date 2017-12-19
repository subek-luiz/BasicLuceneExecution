package org.apache.lucene;


import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LuceneWriteIndexExample {

    public static final String Index_Dir = "c:/temp/lucene6index";

    public static void main(String[] args) throws Exception {
        IndexWriter writer = createwriter();
        List<Document> documents = new ArrayList<>();

        Document document1 = createdocument(1, "eden", "hazard", "edenhazard.com");
        documents.add(document1);
        Document document2 = createdocument(2, "alvaro", "morata", "alrata.com");
        documents.add(document2);
        Document document3 = createdocument(3, "cesc", "fabregas", "fab.com");
        documents.add(document3);
        //commit
        //Lets clean everything first
        writer.deleteAll();

        writer.addDocuments(documents);
        writer.commit();
        writer.close();


    }

    private static IndexWriter createwriter() throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(Index_Dir));
        IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    private static Document createdocument(Integer  id, String firstName, String lastName, String website) {
        Document document = new Document();
        document.add(new StringField("id", id.toString(), Field.Store.YES));
        document.add(new StringField("firstName", firstName, Field.Store.YES));
        document.add(new StringField("lastName", lastName, Field.Store.YES));
        document.add(new StringField("website", website, Field.Store.YES));
        return document;
    }


}

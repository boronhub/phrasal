package edu.stanford.nlp.mt.decoder.feat.sparse;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;

import edu.stanford.nlp.mt.base.IOTools;
import edu.stanford.nlp.util.Generics;
import edu.stanford.nlp.util.Pair;

/**
 * Convenience functions for the sparse feature templates.
 * 
 * @author Spence Green
 *
 */
public final class SparseFeatureUtils {

  private SparseFeatureUtils() {}
  
  /**
   * Load a genre file.
   * 
   * @param filename
   * @return
   */
  public static Map<Integer,Pair<String,Integer>> loadGenreFile(String filename) {
    Map<Integer,Pair<String,Integer>> genreMap = Generics.newHashMap();
    try {
      LineNumberReader reader = IOTools.getReaderFromFile(filename);
      String genreFile = reader.readLine();
      String genreMapping = reader.readLine();
      reader.close();
      
      Map<String,Pair<String,Integer>> genreToPair = Generics.newHashMap();
      String[] pairs = genreMapping.split(",");
      for (String pair: pairs) {
        String[] fields = pair.split(":");
        if (fields.length != 2) throw new RuntimeException("Invalid genre specification: " + genreMapping);
        String genre = fields[0];
        int featureIndex = Integer.valueOf(fields[1]);
        genreToPair.put(genre, new Pair<String,Integer>(genre,featureIndex));
      }
      
      LineNumberReader sourceIdReader = IOTools.getReaderFromFile(genreFile);
      for (String genre; (genre = sourceIdReader.readLine()) != null; ) {
        int lineId = sourceIdReader.getLineNumber()-1;
        genreMap.put(lineId, genreToPair.get(genre.trim()));
      }
      sourceIdReader.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return genreMap;
  }
}

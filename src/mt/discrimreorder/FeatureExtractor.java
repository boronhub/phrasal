package mt.discrimreorder;

import java.util.*;

interface FeatureExtractor {
  public List<String> extractFeatures(AlignmentMatrix matrix, TrainingExample example);
}
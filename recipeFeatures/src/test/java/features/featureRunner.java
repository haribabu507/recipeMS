package features;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class featureRunner {

    @Test
    public void testFeatures() {
        Results results = Runner.path("classpath:features").tags("@test").parallel(5);
        //return Karate.run("classpath:features/retrieveAllRecipes.feature");
        Assertions.assertTrue(results.getFailCount()==0, results.getErrorMessages());
    }
}

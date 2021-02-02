package com.shifvision.spree;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //dryRun = true,
        tags = { "@Debug" },
        //tags = { "@smoke" },         /* smoke test only */
        //tags = { "@Aceptance,@Functional" }, /* debug or smoke test */
        //tags = { "@Smoke","@Functional" },  /* debug and smoke test */

        //monochrome = true,

        features = "src/test/resources/features/",

        // features = "src/test/resources/features/SpreeAddToCart.feature",

        //features = {"src/test/resources/features/SpreeLogin.feature",
        //            "src/test/resources/features/SpreeLogin2.feature"},

        glue = {"com.shiftvision.spree.steps"},

        plugin={
                //"pretty:target/cucumber-test-report/cucumber-pretty.txt",
                "junit:target/cucumber-report/test-report.xml",
                "json:target/cucumber-report/cucumber-report.json",
                "html:target/cucumber-report/plain"
        }
)

public class BDDRunner {
}

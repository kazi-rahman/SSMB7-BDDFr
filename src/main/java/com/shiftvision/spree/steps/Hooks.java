package com.shiftvision.spree.steps;

import com.shiftvision.spree.pages.*;
import com.shiftvision.spree.utils.DriverFactory;
import com.shiftvision.spree.utils.SpecializedScreenRecorder;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.monte.screenrecorder.ScreenRecorder;
import org.monte.media.math.Rational;
import org.monte.media.Format;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class Hooks extends StepBase{

    private ScreenRecorder screenRecorder;
    private String executionFeature;
    private String executingScenario;

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) throws Exception {
        logger.debug("Starting setup ..." + scenario.getName());
        objectRepo.load(System.getProperty("user.dir") + "/src/main/resources/SpreeObjectRepo.properties");

        String scenarioId = scenario.getId();
        String featureName = scenarioId.substring(scenarioId.lastIndexOf("/") + 1, scenarioId.lastIndexOf("."));

        this.executionFeature = featureName;
        this.executingScenario = scenario.getName();

        //startRecording();

        driver = DriverFactory.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        logger.debug("Closing ..." + scenario.getName());
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }

        DriverFactory.getInstance().removeDriver();
        PageFactory.getInstance().reSet();

        //Call the stop method of ScreenRecorder to end the recording
        //stopRecording();
    }

    public void startRecording() throws Exception
    {
        String useDir = System.getProperty("user.dir");
        File file = new File(useDir + "\\selenium-cucumber-videos\\" + this.executionFeature.replaceAll("[^a-zA-Z0-9.-]", "_"));
        String fileNameStartWith  = this.executingScenario.replaceAll("[^a-zA-Z0-9.-]", "_");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, fileNameStartWith);
        this.screenRecorder.start();

    }

    public void stopRecording() throws Exception
    {
        this.screenRecorder.stop();
    }
}

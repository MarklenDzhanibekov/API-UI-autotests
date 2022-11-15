package ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:scenarios/AdminPanelActions.feature"},
        glue = {"ui.bddSteps"})

    public class RunCucumberTest
    {

    }


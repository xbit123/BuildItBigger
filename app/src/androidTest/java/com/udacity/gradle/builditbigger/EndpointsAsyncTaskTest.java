package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void doInBackground() throws Exception {

        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.DataReceivedCallback() {
                @Override
                public void onDataReceived(String data) {
                }
            });
            endpointsAsyncTask.execute();

            String response = endpointsAsyncTask.get(10, TimeUnit.SECONDS);

            assertNotNull("Failure - string is null", response);
            assertFalse("Failure - string is empty", response.isEmpty());
        } catch (Exception e) {
            fail("Failure - exception " + e.toString());
        }
    }
}
package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.DataReceivedCallback() {
            @Override
            public void onDataReceived(Exception error, String data) {
                if (error != null) {
                    fail("Failure - exception " + error.toString());
                } else {
                    assertNotNull("Failure - string is null", data);
                    assertFalse("Failure - string is empty", data.isEmpty());
                }
                latch.countDown();
            }
        });
        endpointsAsyncTask.execute();
        latch.await(40, TimeUnit.SECONDS);
    }
}